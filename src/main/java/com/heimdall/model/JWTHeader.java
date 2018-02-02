package com.heimdall.model;

import lombok.Data;

@Data
public class JWTHeader {
    private long issueAt;
    private long expiredAt;

    public boolean isExpired() {
        return System.currentTimeMillis() < expiredAt;
    }
}
