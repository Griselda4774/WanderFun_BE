package com.project2.wanderfun.application.usecase;

import com.project2.wanderfun.application.dto.AccountDto;
import com.project2.wanderfun.application.dto.LoginResponseDto;
import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.util.JwtUtil;
import com.project2.wanderfun.domain.model.RefreshToken;
import com.project2.wanderfun.domain.model.enums.TokenType;
import com.project2.wanderfun.domain.model.enums.UserRole;
import com.project2.wanderfun.domain.service.RefreshTokenService;
import com.project2.wanderfun.domain.service.UserService;
import com.project2.wanderfun.domain.model.User;
import com.project2.wanderfun.infrastructure.security.CustomUserDetails;
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

    public String register(AccountDto accountDto) {
        User user = objectMapper.map(accountDto, User.class);
        try {
            try {
                User existingUser = userService.findUserByEmail(user.getEmail());
                return "User already exists";
            } catch (Exception e) {
                user.setRole(UserRole.USER);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.createUser(user);
                return "Register successfully!";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String registerAdmin(AccountDto accountDto) {
        User user = objectMapper.map(accountDto, User.class);
        try {
            try {
                User existingUser = userService.findUserByEmail(user.getEmail());
                return "User already exists";
            } catch (Exception e) {
                user.setRole(UserRole.ADMIN);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.createUser(user);
                return "Register successfully!";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public LoginResponseDto login(AccountDto accountDto) {
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
        refreshTokenService.createRefreshToken(refreshTokenModel);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setId(user.getId());
        loginResponseDto.setEmail(user.getEmail());
        loginResponseDto.setRole(user.getRole());
        loginResponseDto.setAccessToken(accessToken);
        loginResponseDto.setRefreshToken(refreshToken);

        return loginResponseDto;
    }
}
