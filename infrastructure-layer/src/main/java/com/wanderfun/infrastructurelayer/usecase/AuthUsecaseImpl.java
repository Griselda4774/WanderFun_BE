package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.auths.LoginDto;
import com.wanderfun.applicationlayer.dto.auths.LoginResponseDto;
import com.wanderfun.applicationlayer.dto.auths.RegisterDto;
import com.wanderfun.applicationlayer.dto.auths.TokenResponseDto;
import com.wanderfun.applicationlayer.exception.ObjectAlreadyExistException;
import com.wanderfun.applicationlayer.exception.ObjectInvalidException;
import com.wanderfun.applicationlayer.exception.WrongEmailOrPasswordException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.auths.RefreshTokenService;
import com.wanderfun.applicationlayer.service.auths.AccountService;
import com.wanderfun.applicationlayer.usecase.AuthUsecase;
import com.wanderfun.applicationlayer.util.JwtUtil;
import com.wanderfun.domainlayer.model.auths.Account;
import com.wanderfun.domainlayer.model.auths.RefreshToken;
import com.wanderfun.domainlayer.model.users.UserRole;
import com.wanderfun.infrastructurelayer.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUsecaseImpl implements AuthUsecase {
    private final AccountService accountService;
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthUsecaseImpl(AccountService accountService,
                           ObjectMapper objectMapper,
                           AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder,
                           RefreshTokenService refreshTokenService) {
        this.accountService = accountService;
        this.objectMapper = objectMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public boolean register(RegisterDto registerDto) throws ObjectAlreadyExistException {
        Account account = objectMapper.map(registerDto, Account.class);
        Account existingAccount = null;
        try {
            existingAccount = accountService.findByEmail(account.getEmail());
        } catch (Exception e) {
        }

        if (existingAccount != null) {
            throw new ObjectAlreadyExistException(String.format("Email already used!"));
        }

        account.setRole(UserRole.USER);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountService.create(account);
        return true;
    }

    @Override
    public boolean registerAdmin(RegisterDto registerDto) throws ObjectAlreadyExistException {
        Account account = objectMapper.map(registerDto, Account.class);
        Account existingAccount = null;
        try {
            existingAccount = accountService.findByEmail(account.getEmail());
        } catch (Exception e) {
        }

        if (existingAccount != null) {
            throw new ObjectAlreadyExistException(String.format("Email already used!"));
        }

        account.setRole(UserRole.ADMIN);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountService.create(account);
        return true;
    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) throws WrongEmailOrPasswordException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Account account = userDetails.getAccount();
            String accessToken = jwtUtil.generateAccessToken(account.getId(), account.getEmail(), account.getRole().name());
            String refreshToken = jwtUtil.generateRefreshToken(account.getId());

            RefreshToken refreshTokenModel = new RefreshToken();
            refreshTokenModel.setAccountId(account.getId());
            refreshTokenModel.setToken(refreshToken);
            refreshTokenService.deleteByAccountId(account.getId());
            refreshTokenService.create(refreshTokenModel);

            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setId(account.getId());
            loginResponseDto.setEmail(account.getEmail());
            loginResponseDto.setRole(account.getRole());
            loginResponseDto.setAccessToken(accessToken);
            loginResponseDto.setRefreshToken(refreshToken);

            return loginResponseDto;
        } catch (Exception e) {
            throw new WrongEmailOrPasswordException("Email or password is not correct!");
        }
    }

    @Override
    public boolean logout(String accessToken) {
        Long accountId = jwtUtil.getIdFromToken(accessToken);
        refreshTokenService.deleteByAccountId(accountId);
        SecurityContextHolder.clearContext();
        return true;
    }

    @Override
    public TokenResponseDto refresh (String refreshToken) throws ObjectInvalidException {
        if (!jwtUtil.validateToken(refreshToken) || !jwtUtil.isRefreshToken(refreshToken)) {
            throw new ObjectInvalidException(String.format("%s is invalid", RefreshToken.class.getSimpleName()));
        }

        Long accountId = jwtUtil.getIdFromToken(refreshToken);
        Account account = accountService.findById(accountId);
        RefreshToken refreshTokenModel = refreshTokenService.findByAccountId(account.getId());

        if (!refreshTokenModel.getToken().equals(refreshToken)) {
            throw new ObjectInvalidException(String.format("%s is invalid", RefreshToken.class.getSimpleName()));
        }

        String accessToken = jwtUtil.generateAccessToken(account.getId(), account.getEmail(), account.getRole().name());
        String newRefreshToken = jwtUtil.generateRefreshToken(account.getId());
        refreshTokenModel.setToken(newRefreshToken);
        refreshTokenService.updateByAccountId(refreshTokenModel.getAccountId(), refreshTokenModel);

        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setAccessToken(accessToken);
        tokenResponseDto.setRefreshToken(refreshTokenModel.getToken());
        return tokenResponseDto;
    }
}
