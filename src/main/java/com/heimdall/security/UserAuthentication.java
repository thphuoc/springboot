package com.heimdall.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {
    private String username;
    private String userId;

    public UserAuthentication(String username, String userId, Date exp) {
        super(username, null, (exp.getTime() >= System.currentTimeMillis()) ? Collections.emptyList() : null);
        this.username = username;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
