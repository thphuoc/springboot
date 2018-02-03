package com.heimdall.security;

import com.heimdall.defines.Constants;
import com.heimdall.dao.Configs;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static java.util.Collections.emptyList;

public class JWTAuthenticationFilter extends GenericFilterBean {

    private Configs configs;

    public JWTAuthenticationFilter(Configs configs) {
        this.configs = configs;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserAuthentication authentication = getAuthentication((HttpServletRequest) servletRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(servletRequest, servletResponse);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private UserAuthentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(Constants.HeaderKey.AUTHORIZATION);
        if (token != null) {
            // parse the token.
            Claims claims = Jwts.parser()
                    .setSigningKey(configs.getSecretKey())
                    .parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            request.setAttribute(Constants.HeaderKey.USER_ID, claims.getId());
            return new UserAuthentication(username, claims.getId(), claims.getExpiration());
        }
        return null;
    }
}