package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.AccountDto;
import com.project2.wanderfun.application.dto.LoginResponseDto;
import com.project2.wanderfun.application.dto.TokenResponseDto;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.util.JwtUtil;
import com.project2.wanderfun.domain.model.RefreshToken;
import com.project2.wanderfun.domain.model.enums.UserRole;
import com.project2.wanderfun.application.service.RefreshTokenService;
import com.project2.wanderfun.application.service.UserService;
import com.project2.wanderfun.domain.model.User;
import com.project2.wanderfun.infrastructure.security.CustomUserDetails;
import com.project2.wanderfun.presentation.exception.ObjectAlreadyExistException;
import com.project2.wanderfun.presentation.exception.ObjectInvalidException;
import com.project2.wanderfun.presentation.exception.WrongEmailOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUsecase {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthUsecase(UserService userService,
                       ObjectMapper objectMapper,
                       AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil,
                       PasswordEncoder passwordEncoder,
                       RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    public String register(AccountDto accountDto) throws ObjectAlreadyExistException {
        User user = objectMapper.map(accountDto, User.class);
        User existingUser = null;
        try {
            existingUser = userService.findByEmail(user.getEmail());
        } catch (Exception e) {
        }

        if (existingUser != null) {
            throw new ObjectAlreadyExistException(String.format("Email already used"));
        }

        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(user);
        return "Register successfully!";
    }

    public String registerAdmin(AccountDto accountDto) throws ObjectAlreadyExistException {
        User user = objectMapper.map(accountDto, User.class);
        User existingUser = null;
        try {
            existingUser = userService.findByEmail(user.getEmail());
        } catch (Exception e) {
        }

        if (existingUser != null) {
            throw new ObjectAlreadyExistException(String.format("Email already used"));
        }

        user.setRole(UserRole.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(user);
        return "Register admin successfully!";
    }

    public LoginResponseDto login(AccountDto accountDto) throws WrongEmailOrPasswordException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            accountDto.getEmail(),
                            accountDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser();
            String accessToken = jwtUtil.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
            String refreshToken = jwtUtil.generateRefreshToken(user.getId());

            RefreshToken refreshTokenModel = new RefreshToken();
            refreshTokenModel.setEmail(user.getEmail());
            refreshTokenModel.setToken(refreshToken);
            refreshTokenService.deleteByEmail(user.getEmail());
            refreshTokenService.create(refreshTokenModel);

            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setId(user.getId());
            loginResponseDto.setEmail(user.getEmail());
            loginResponseDto.setRole(user.getRole());
            loginResponseDto.setAccessToken(accessToken);
            loginResponseDto.setRefreshToken(refreshToken);

            return loginResponseDto;
        } catch (Exception e) {
            throw new WrongEmailOrPasswordException("Email or password is not correct!");
        }
    }

    public String logout(String refreshToken) {
        String email = jwtUtil.getEmailFromToken(refreshToken);
        refreshTokenService.deleteByEmail(email);
        return "Logout successfully!";
    }

    public TokenResponseDto refresh (String refreshToken) throws ObjectInvalidException {
        Long userId = Long.valueOf(jwtUtil.getIdFromToken(refreshToken));
        User user = userService.findById(userId);
        RefreshToken refreshTokenModel = refreshTokenService.findByEmail(user.getEmail());

        if (!jwtUtil.validateToken(refreshToken) || !refreshTokenModel.getToken().equals(refreshToken)) {
            throw new ObjectInvalidException(String.format("%s is invalid", RefreshToken.class.getSimpleName()));
        }

        String accessToken = jwtUtil.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
        String newRefreshToken = jwtUtil.generateRefreshToken(user.getId());
        refreshTokenModel.setToken(newRefreshToken);
        refreshTokenService.updateById(refreshTokenModel.getId(), refreshTokenModel);

        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setAccessToken(accessToken);
        tokenResponseDto.setRefreshToken(refreshTokenModel.getToken());
        return tokenResponseDto;
    }
}
