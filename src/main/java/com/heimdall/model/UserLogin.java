package com.heimdall.model;

import lombok.Data;

@Data
public class UserLogin {
    private String userName;
    private String email;
    private String password;
}
