package com.onlinevoting.OnlineVoting.security;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinevoting.OnlineVoting.dto.Credential;
import com.onlinevoting.OnlineVoting.model.AppUser;
import com.onlinevoting.OnlineVoting.service.UserService;
import com.onlinevoting.OnlineVoting.utility.JWTUtility;
import com.onlinevoting.OnlineVoting.utility.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtility jwtUtility;
    private UserService userService;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.jwtUtility = SpringContext.getBean(JWTUtility.class);
        this.userService = SpringContext.getBean(UserService.class);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("Trying authentication");
        try {
            Credential loginReq = new ObjectMapper().readValue(request.getInputStream(), Credential.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        log.info("Successful authentication");
        User user = (User) authResult.getPrincipal();
        try {

            String accessToken = 
                jwtUtility.createAccessToken(
                    user.getUsername(), 
                    request.getRequestURI().toString(), 
                    user.getAuthorities()
                            .stream().map(grantedAuthority -> grantedAuthority.getAuthority())
                            .collect(Collectors.toList()));

            String refreshToken = jwtUtility.createRefreshToken(user.getUsername(), request.getRequestURI().toString());
            AppUser appUser = userService.getUser(user.getUsername());

            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("access_token", accessToken);
            tokenMap.put("refresh_token", refreshToken);
            tokenMap.put("user_id", appUser.getId());
            tokenMap.put("user_name", appUser.getUsername());
            //tokenMap.put("user_email", appUser.getEmail());
            
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), tokenMap);

        } catch (JWTCreationException ex) {
            log.error( ex.getMessage() + " - " + ex.getCause() + " - " + ex.getStackTrace());
            new ObjectMapper().writeValue(response.getOutputStream(), ex.getMessage());
        }
    }
}