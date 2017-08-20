package com.tony.mockapi.utils;

import java.util.UUID;

public class TokenGenerator {
    public static String generateToken() {
        return System.currentTimeMillis() + "-" + UUID.randomUUID();
    }
}
