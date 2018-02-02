package com.heimdall.model;

import lombok.Data;

@Data
public class Account {
    private String email;
    private String userName;
    private String password;
    private String accountType;
    private String accessToken;
}
