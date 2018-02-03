package com.heimdall.defines;

public interface Constants {
    long DURATION_TOKEN_EXPIRED_IN_SECONDS = 3600;

    interface HeaderKey {
        String AUTHORIZATION = "Authorization";
        String USERNAME = "username";
        String USER_ID = "userId";
        String APP_NAME = "appName";
        String APP_ID = "appId";
    }
}
