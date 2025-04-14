//package com.wanderfun.infrastructurelayer.service;
//
//import com.wanderfun.applicationlayer.mapper.ObjectMapper;
//import com.wanderfun.domainlayer.model.tokens.RefreshToken;
//import com.wanderfun.domainlayer.repository.RefreshTokenRepository;
//import com.wanderfun.applicationlayer.service.RefreshTokenService;
//import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RefreshTokenServiceImpl extends BaseServiceImpl<RefreshToken> implements RefreshTokenService {
//    private final RefreshTokenRepository refreshTokenRepository;
//
//    @Autowired
//    public RefreshTokenServiceImpl(ObjectMapper objectMapper, RefreshTokenRepository refreshTokenRepository) {
//        super(refreshTokenRepository, objectMapper, RefreshToken.class);
//        this.refreshTokenRepository = refreshTokenRepository;
//    }
//
//    @Override
//    @Transactional
//    public RefreshToken findByEmail(String email) {
//        return refreshTokenRepository.findByEmail(email)
//                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", RefreshToken.class.getSimpleName())));
//    }
//
//    @Override
//    @Transactional
//    public void deleteByEmail(String email) {
//        RefreshToken existingRefreshtoken = refreshTokenRepository.findByEmail(email).orElse(null);
//        if (existingRefreshtoken != null) {
//            refreshTokenRepository.deleteByEmail(email);
//        }
//    }
//}
