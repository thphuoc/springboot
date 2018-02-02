package com.heimdall.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heimdall.dao.Configs;
import com.heimdall.utils.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Configs configs;
    private final MongoDbAuthenticationProvider authenProvider;
    private final ObjectMapper objectMapper;
    private final JWTGenerator jwtGenerator;

    @Autowired
    public WebSecurityConfig(Configs configs, MongoDbAuthenticationProvider mongoDbAuthenticationProvider, ObjectMapper objectMapper, JWTGenerator jwtGenerator) {
        this.configs = configs;
        this.authenProvider = mongoDbAuthenticationProvider;
        this.objectMapper = objectMapper;
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/login").permitAll()
                .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/user/login", authenticationManager(), configs, objectMapper, jwtGenerator), null)
                .addFilterBefore(new JWTAuthenticationFilter(configs), null);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authenProvider);
//    }
}
