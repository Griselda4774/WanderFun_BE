package com.project2.wanderfun.infrastructure.util;

import com.project2.wanderfun.application.util.JwtUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtilImpl implements JwtUtil{
    private static final Logger log = LoggerFactory.getLogger(JwtUtilImpl.class);
    private final String JWT_SECRET = "wanderfun_secret_key_which_should_be_long_enough";
    private final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    private static final long ACCESS_EXPIRATION_TIME = 30 * 60 * 1000;
    private static final long REFRESH_EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000;

    @Override
    public String generateAccessToken(String email, String role) {
        Date now = new Date();
        Date expiryDate = new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String generateRefreshToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String getEmailFromAccessToken(String accessToken) {
        return extractClaim(accessToken, Claims::getSubject);
    }

    @Override
    public String getRoleFromAccessToken(String accessToken) {
        return (String) extractClaim(accessToken, claims -> claims.get("role", String.class));
    }

    @Override
    public String getEmailFromRefreshToken(String refreshToken) {
        return extractClaim(refreshToken, Claims::getSubject);
    }

    @Override
    public boolean validateAccessToken(String accessToken) {
        return validateToken(accessToken);
    }

    @Override
    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken);
    }

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
