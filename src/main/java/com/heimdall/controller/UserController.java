package com.heimdall.controller;

import com.heimdall.dao.Configs;
import com.heimdall.dao.UserModel;
import com.heimdall.defines.AccountType;
import com.heimdall.defines.Constants;
import com.heimdall.model.ErrorStatus;
import com.heimdall.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final UserRepository repository;
    private final Configs configs;

    @Autowired
    public UserController(UserRepository repository, Configs configs) {
        this.repository = repository;
        this.configs = configs;
    }

    @PostMapping("/register")
    public ResponseEntity registerNewUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        UserModel userModel = repository.findCustomByEmailOrUserName(username, email);
        if (userModel != null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorStatus("Username or Email has existed"));
        }

        userModel = new UserModel();
        userModel.setUserName(username);
        userModel.setPassword(password);
        userModel.setAccountType(AccountType.HEIMDALL.name());
        userModel.setEmail(email);
        repository.insert(userModel);

        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + (configs.getTokenExpireDuration() * 1000)))
                .signWith(SignatureAlgorithm.HS512, configs.getSecretKey())
                .compact();
        return ResponseEntity.ok().header(Constants.HeaderKey.AUTHORIZATION, JWT).build();
    }

    @GetMapping("/profile")
    public ResponseEntity getUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return ResponseEntity.ok(username);
    }
}
