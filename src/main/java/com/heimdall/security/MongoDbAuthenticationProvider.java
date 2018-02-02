package com.heimdall.security;

import com.heimdall.dao.UserModel;
import com.heimdall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.EMPTY_LIST;

@Service
public class MongoDbAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final UserRepository userRepository;

    @Autowired
    public MongoDbAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(principal, authentication.getCredentials(), null);
        result.setDetails(user);
        return result;
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        UserModel userModel = userRepository.findCustomByLoginNameAndPassword(username, usernamePasswordAuthenticationToken.getCredentials().toString());
        if (userModel == null) {
            throw new UsernameNotFoundException("Wrong username/password");
        }
        return new User(userModel.getUserName(), userModel.getPassword(), EMPTY_LIST);
    }
}
