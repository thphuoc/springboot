package com.heimdall.dao;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "configs")
public class Configs {
    private long tokenExpireDuration;
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getTokenExpireDuration() {
        return tokenExpireDuration;
    }

    public void setTokenExpireDuration(long tokenExpireDuration) {
        this.tokenExpireDuration = tokenExpireDuration;
    }
}
