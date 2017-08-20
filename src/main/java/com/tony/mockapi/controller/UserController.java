package com.tony.mockapi.controller;

import com.tony.mockapi.dao.User;
import com.tony.mockapi.exception.UserHasExistedException;
import com.tony.mockapi.exception.LoginException;
import com.tony.mockapi.model.RegisterUserRequest;
import com.tony.mockapi.model.UserLoginRequest;
import com.tony.mockapi.repository.UserRepository;
import com.tony.mockapi.utils.Md5Encoder;
import com.tony.mockapi.utils.TokenGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Api(tags = "User Login Api")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(path = "/login", method = POST)
    public User login(@RequestBody UserLoginRequest request) throws Exception {
        String passwordEncoded = Md5Encoder.encode(request.getPassword());
        User user = userRepository.findCustomByLoginNameAndPassword(request.getLoginName(), passwordEncoded);
        if (user == null) {
            throw new LoginException();
        }
        user.setToken(TokenGenerator.generateToken());
        user.setPassword(passwordEncoded);
        userRepository.save(user);
        return user;
    }

    @RequestMapping(path = "/register", method = POST)
    public User registerAccount(@RequestBody RegisterUserRequest request) throws Exception {
        User user = userRepository.findCustomByEmailOrUserName(request.getEmail(), request.getUserName());
        if (user != null) {
            throw new UserHasExistedException();
        }
        user = new User();
        user.setEmail(request.getEmail());
        String passwordEncoded = Md5Encoder.encode(request.getPassword());
        user.setPassword(passwordEncoded);
        user.setToken(TokenGenerator.generateToken());
        user.setUserName(request.getUserName());
        userRepository.save(user);
        return user;
    }
}
