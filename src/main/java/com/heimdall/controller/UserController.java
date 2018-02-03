package com.heimdall.controller;

import com.heimdall.dao.UserModel;
import com.heimdall.defines.AccountType;
import com.heimdall.defines.Constants;
import com.heimdall.model.ErrorStatus;
import com.heimdall.repository.UserRepository;
import com.heimdall.utils.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserRepository repository;
    private final JWTGenerator jwtGenerator;

    @Autowired
    public UserController(UserRepository repository, JWTGenerator jwtGenerator) {
        this.repository = repository;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity registerNewUser(@RequestParam String username,
                                          @RequestParam String email,
                                          @RequestParam String password) {
        UserModel userModel = repository.findCustomByEmailOrUserName(username, email);
        if (userModel != null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorStatus("Username or Email has existed"));
        }

        userModel = new UserModel();
        userModel.setUserName(username);
        userModel.setPassword(password);
        userModel.setAccountType(AccountType.HEIMDALL.name());
        userModel.setEmail(email);
        userModel = repository.insert(userModel);

        String JWT = jwtGenerator.generateToken(username, userModel.getId());
        return ResponseEntity.ok().header(Constants.HeaderKey.AUTHORIZATION, JWT).build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) {
        UserModel user = repository.findCustomByLoginNameAndPassword(username, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorStatus("Wrong username/password"));
        }
        return ResponseEntity.ok().header(Constants.HeaderKey.AUTHORIZATION, jwtGenerator.generateToken(username, user.getId())).build();
    }

    @GetMapping("/profile")
    public ResponseEntity getUserProfile(@RequestAttribute String userId) {
        UserModel user = repository.findOne(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
