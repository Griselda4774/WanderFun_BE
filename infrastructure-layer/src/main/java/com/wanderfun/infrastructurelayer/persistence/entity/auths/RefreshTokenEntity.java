package com.wanderfun.infrastructurelayer.persistence.entity.auths;

import jakarta.persistence.*;

@Entity
@Table(name = "refresh_tokens")
public class RefreshTokenEntity {

    @Id
    @Column(name = "account_id")
    private Long accountId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Column(name = "token", nullable = false, unique = true, length = 512)
    private String token;

    public RefreshTokenEntity() {}

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
