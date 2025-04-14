//package com.wanderfun.infrastructurelayer.repository;
//
//import com.wanderfun.applicationlayer.mapper.ObjectMapper;
//import com.wanderfun.domainlayer.model.tokens.RefreshToken;
//import com.wanderfun.domainlayer.repository.RefreshTokenRepository;
//import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaRefreshTokenRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public class RefreshTokenRepositoryImpl extends BaseRepositoryImpl<RefreshToken, RefreshTokenEntity, Long> implements RefreshTokenRepository {
//    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;
//
//    @Autowired
//    public RefreshTokenRepositoryImpl(JpaRefreshTokenRepository jpaRefreshTokenRepository, ObjectMapper objectMapper) {
//        super(jpaRefreshTokenRepository, objectMapper, RefreshToken.class, RefreshTokenEntity.class);
//        this.jpaRefreshTokenRepository = jpaRefreshTokenRepository;
//    }
//
//    @Override
//    public Optional<RefreshToken> findByEmail(String email) {
//        return jpaRefreshTokenRepository.findByEmail(email)
//                .map(entity -> objectMapper.map(entity, RefreshToken.class));
//    }
//
//    @Override
//    public void deleteByEmail(String email) {
//        jpaRefreshTokenRepository.deleteByEmail(email);
//    }
//}
