package com.heimdall.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heimdall.defines.Constants;
import com.heimdall.dao.Configs;
import com.heimdall.model.ErrorStatus;
import com.heimdall.utils.JWTGenerator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

/**
 * Created by nhs3108 on 29/03/2017.
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private Configs configs;
    private ObjectMapper objectMapper;
    private final JWTGenerator jwtGenerator;

    public JWTLoginFilter(String url, AuthenticationManager authManager, Configs configs, ObjectMapper mapper, JWTGenerator jwtGenerator) {
        super(new AntPathRequestMatcher(url));
        this.configs = configs;
        this.objectMapper = mapper;
        this.jwtGenerator = jwtGenerator;
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getParameter("username"),
                        request.getParameter("password"),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String JWT = Jwts.builder()
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + (configs.getTokenExpireDuration() * 1000)))
                .signWith(SignatureAlgorithm.HS512, configs.getSecretKey())
                .compact();
        response.addHeader(Constants.HeaderKey.AUTHORIZATION, JWT);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        objectMapper.writeValue(response.getWriter(), new ErrorStatus(failed.getMessage()));
    }
}
