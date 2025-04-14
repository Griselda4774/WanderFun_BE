package com.wanderfun.domainlayer.model.auths;

public class RefreshToken {
    private String token;
    private Long AccountId;

    public RefreshToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getAccountId() {
        return AccountId;
    }

    public void setAccountId(Long accountId) {
        AccountId = accountId;
    }
}
