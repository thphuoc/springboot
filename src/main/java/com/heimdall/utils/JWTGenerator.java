package com.heimdall.utils;

import com.heimdall.dao.Configs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGenerator {
    private final Configs configs;

    @Autowired
    public JWTGenerator(Configs configs) {
        this.configs = configs;
    }

    public String generateToken(String username, String userId) {
        return Jwts.builder()
                .setSubject(username)
                .setId(userId)
                .setExpiration(new Date(System.currentTimeMillis() + (configs.getTokenExpireDuration() * 1000)))
                .signWith(SignatureAlgorithm.HS512, configs.getSecretKey())
                .compact();
    }
}
