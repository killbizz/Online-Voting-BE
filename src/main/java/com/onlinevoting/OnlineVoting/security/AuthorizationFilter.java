package com.onlinevoting.OnlineVoting.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinevoting.OnlineVoting.utility.JWTUtility;
import com.onlinevoting.OnlineVoting.utility.SpringContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    JWTUtility jwtUtility;

    AuthorizationFilter() {
        this.jwtUtility = SpringContext.getBean(JWTUtility.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/token/refresh")) {
        } else {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (token != null && token.startsWith("Bearer ")) {
                String jws = token.substring("Bearer ".length());
                try {
                    DecodedJWT jwt = jwtUtility.getJWTVerifier().verify(jws);
                    String userName = jwt.getSubject();
                    String[] arr = jwt.getClaim("roles").asArray(String.class);
                    var grantedAuthorityList = Stream.of(arr)
                            .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        UsernamePasswordAuthenticationToken authenticationToken = new
                                UsernamePasswordAuthenticationToken(userName, null, grantedAuthorityList);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                } catch (JWTVerificationException exception) {
                    //Invalid signature/claims
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    new ObjectMapper().writeValue(response.getOutputStream(), exception.getMessage());
                    log.error("Token invalid");
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}