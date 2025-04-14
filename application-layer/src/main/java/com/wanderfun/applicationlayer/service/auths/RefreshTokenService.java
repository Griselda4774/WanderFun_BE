package com.wanderfun.applicationlayer.service.auths;

import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.domainlayer.model.auths.RefreshToken;

public interface RefreshTokenService extends BaseService<RefreshToken> {
    RefreshToken findByAccountId(Long accountId);
    void updateByAccountId(Long accountId, RefreshToken refreshToken);
    void deleteByAccountId(Long accountId);
}
