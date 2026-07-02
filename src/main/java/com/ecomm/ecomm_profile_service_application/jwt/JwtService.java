package com.ecomm.ecomm_profile_service_application.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.List;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public void validateAccessToken(String token) {
        getParser().parseSignedClaims(token);
    }

    public Long extractUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    public String extractEmail(String token) {
        return getClaims(token).get("email", String.class);
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        return (List<String>) getClaims(token).get("roles");
    }

    private JwtParser getParser() {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build();
    }

    private Claims getClaims(String token) {
        return getParser()
                .parseSignedClaims(token)
                .getPayload();
    }
}