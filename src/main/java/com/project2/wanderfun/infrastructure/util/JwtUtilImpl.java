package com.project2.wanderfun.infrastructure.util;

import com.project2.wanderfun.application.util.JwtUtil;
import com.project2.wanderfun.domain.model.enums.TokenType;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtilImpl implements JwtUtil{
    private static final Logger log = LoggerFactory.getLogger(JwtUtilImpl.class);
    private final String JWT_SECRET = "f228fedebbc31e1011102c8473bb5e674d6deff16d48b7cc9f3df02bb727251948ae8177f874eda0985339a855991ed5a5414fe6dbe752a37d560e320b3d2841";
    private final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    private static final long ACCESS_EXPIRATION_TIME = 30 * 60 * 1000;
    private static final long REFRESH_EXPIRATION_TIME = 2 * 24 * 60 * 60 * 1000;

    @Override
    public String generateAccessToken(Long id, String email, String role) {
        try {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + ACCESS_EXPIRATION_TIME);
            return Jwts.builder()
                    .subject(Long.toString(id))
                    .claim("email", email)
                    .claim("role", role)
                    .claim("type", TokenType.ACCESS_TOKEN.name())
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(key, SignatureAlgorithm.HS512)
                    .compact();
        } catch (Exception e) {
            log.error("Error generating access token: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String generateRefreshToken(Long id) {
        try {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + REFRESH_EXPIRATION_TIME);
            return Jwts.builder()
                    .subject(Long.toString(id))
                    .claim("type", TokenType.REFRESH_TOKEN.name())
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            log.error("Error generating refresh token: " + e.getMessage());
            return null;
        }
    }

    @Override
    public long getIdFromToken(String token) {
        return Long.parseLong(extractClaim(token, Claims::getSubject));
    }

    @Override
    public String getEmailFromToken(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    @Override
    public String getRoleFromToken(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    @Override
    public Date getExpirationDateFromToken(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    @Override
    public TokenType getTokenTypeFromToken(String token) {
        return TokenType.valueOf(extractClaim(token, claims -> claims.get("type", String.class)));
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    @Override
    public boolean isAccessToken(String token) {
        return getTokenTypeFromToken(token) == TokenType.ACCESS_TOKEN;
    }

    @Override
    public boolean isRefreshToken(String token) {
        return getTokenTypeFromToken(token) == TokenType.REFRESH_TOKEN;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
