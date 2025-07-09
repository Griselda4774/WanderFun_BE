package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.auths.*;
import com.wanderfun.applicationlayer.exception.ObjectAlreadyExistException;
import com.wanderfun.applicationlayer.exception.ObjectInvalidException;
import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.exception.WrongEmailOrPasswordException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.auths.MailOtpService;
import com.wanderfun.applicationlayer.service.auths.RefreshTokenService;
import com.wanderfun.applicationlayer.service.auths.AccountService;
import com.wanderfun.applicationlayer.service.users.UserService;
import com.wanderfun.applicationlayer.usecase.AuthUsecase;
import com.wanderfun.applicationlayer.util.JwtUtil;
import com.wanderfun.applicationlayer.util.MailUtil;
import com.wanderfun.domainlayer.model.auths.Account;
import com.wanderfun.domainlayer.model.auths.MailOtp;
import com.wanderfun.domainlayer.model.auths.RefreshToken;
import com.wanderfun.domainlayer.model.users.User;
import com.wanderfun.domainlayer.model.users.UserRole;
import com.wanderfun.infrastructurelayer.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AuthUsecaseImpl implements AuthUsecase {
    private final AccountService accountService;
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final MailOtpService mailOtpService;
    private final MailUtil mailUtil;

    @Autowired
    public AuthUsecaseImpl(AccountService accountService,
                           ObjectMapper objectMapper,
                           AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder,
                           RefreshTokenService refreshTokenService,
                           UserService userService,
                           MailOtpService mailOtpService, MailUtil mailUtil) {
        this.accountService = accountService;
        this.objectMapper = objectMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
        this.mailOtpService = mailOtpService;
        this.mailUtil = mailUtil;
    }

    @Override
    public boolean register(RegisterDto registerDto) throws ObjectAlreadyExistException {
        Account account = objectMapper.map(registerDto, Account.class);
        checkExistingAccount(account);
        account.setRole(UserRole.USER);
        initUser(account, registerDto);
        return true;
    }

    @Override
    public boolean registerAdmin(RegisterDto registerDto) throws ObjectAlreadyExistException {
        Account account = objectMapper.map(registerDto, Account.class);
        checkExistingAccount(account);
        account.setRole(UserRole.ADMIN);
        initUser(account, registerDto);
        return true;
    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) throws WrongEmailOrPasswordException {
        try {
            LoginResponseDto loginResponseDto = new LoginResponseDto();

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Account account = userDetails.getAccount();
            if (!account.isVerified()) {
                loginResponseDto.setVerified(false);
                return loginResponseDto;
            } else {
                loginResponseDto.setVerified(true);
            }

            String accessToken = jwtUtil.generateAccessToken(account.getId(), account.getEmail(), account.getRole().name());
            String refreshToken = jwtUtil.generateRefreshToken(account.getId());

            RefreshToken refreshTokenModel = new RefreshToken();
            refreshTokenModel.setAccountId(account.getId());
            refreshTokenModel.setToken(refreshToken);
            refreshTokenService.deleteByAccountId(account.getId());
            refreshTokenService.create(refreshTokenModel);

            loginResponseDto.setId(account.getId());
            loginResponseDto.setUserId(userService.findByAccountId(account.getId()).getId());
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
        refreshTokenService.updateByAccountId(accountId, refreshTokenModel);

        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setAccessToken(accessToken);
        tokenResponseDto.setRefreshToken(refreshTokenModel.getToken());
        return tokenResponseDto;
    }

    @Override
    public boolean sendOtp(String email) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(1);
        MailOtp mailOtp = new MailOtp();
        mailOtp.setEmail(email);
        mailOtp.setOtp(otp);
        mailOtp.setExpirationTime(expiration);
        mailOtpService.create(mailOtp);
        mailUtil.sendOtp(email, otp);
        return true;
    }

    @Override
    public boolean verifyOtp(MailOtpDto mailOtpDto) {
        MailOtp mailOtp = mailOtpService.findByEmailAndOtp(mailOtpDto.getEmail(), mailOtpDto.getOtp());
        if (mailOtp != null && mailOtp.getExpirationTime().isAfter(LocalDateTime.now()) && !mailOtp.isUsed()) {
            mailOtp.setUsed(true);
            mailOtpService.updateById(mailOtp.getId(), mailOtp);
            try {
                Account account = accountService.findByEmail(mailOtp.getEmail());
                account.setVerified(true);
                accountService.updateById(account.getId(), account);
            } catch (ObjectNotFoundException ignored) {
            }
            return true;
        } else {
            throw new ObjectInvalidException("OTP is invalid or expired!");
        }
    }

    @Override
    public boolean forgotPassword(ForgotPasswordDto forgotPasswordDto) {
        Account account = accountService.findByEmail(forgotPasswordDto.getEmail());

        MailOtp mailOtp = mailOtpService.findByEmailAndOtp(forgotPasswordDto.getEmail(), forgotPasswordDto.getOtp());
        if (mailOtp == null || !mailOtp.isUsed()) {
            throw new ObjectInvalidException("OTP is invalid!");
        }

        account.setPassword(passwordEncoder.encode(forgotPasswordDto.getNewPassword()));
        accountService.updateById(account.getId(), account);
        return true;
    }

    @Override
    public boolean changePassword(String accessToken, ChangePasswordDto changePasswordDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtUtil.getEmailFromToken(accessToken),
                            changePasswordDto.getOldPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Account account = userDetails.getAccount();

            account.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
            accountService.updateById(account.getId(), account);

            return true;

        } catch (Exception e) {
            throw new WrongEmailOrPasswordException("Email or password is not correct!");
        }
    }

    private void checkExistingAccount(Account account) throws ObjectAlreadyExistException {
        Account existingAccount = null;
        try {
            existingAccount = accountService.findByEmail(account.getEmail());
        } catch (Exception ignored) {
        }

        if (existingAccount != null) {
            throw new ObjectAlreadyExistException("Email already used!");
        }
    }

    private void initUser(Account account, RegisterDto registerDto) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Account currentAccount = accountService.create(account);
        User user = new User();
        user.setAccountId(currentAccount.getId());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        userService.create(user);
    }
}
