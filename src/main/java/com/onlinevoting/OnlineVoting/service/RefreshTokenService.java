package com.onlinevoting.OnlineVoting.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import lombok.extern.slf4j.Slf4j;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.onlinevoting.OnlineVoting.model.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinevoting.OnlineVoting.utility.JWTUtility;

@Slf4j
@Service
public class RefreshTokenService {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserService userService;
    
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null && token.startsWith("Bearer ")) {
            String jws = token.substring("Bearer ".length());
            try {
                DecodedJWT jwt = jwtUtility.getJWTVerifier().verify(jws);
                String email = jwt.getSubject();
                AppUser appUser = userService.getUser(email);
                if (appUser == null) {
                    log.error("Fake token");
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Fake token");
                }
                String accessToken = 
                    jwtUtility.createAccessToken(
                        appUser.getEmail(), 
                        request.getRequestURI().toString(),
                        appUser.getRoles()
                            .stream().map(grantedAuthority -> grantedAuthority.getName())
                            .collect(Collectors.toList())
                    );

                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put("access_token", accessToken);
                tokenMap.put("refresh_token", jws);
                tokenMap.put("user_id", appUser.getId());
                tokenMap.put("user_name", appUser.getUsername());
                //tokenMap.put("user_email", appUser.getEmail());
                
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                setResponseBody(response, tokenMap);
            } catch (JWTVerificationException exception) {
                //Invalid signature/claims
                response.setStatus(HttpStatus.FORBIDDEN.value());
                setResponseBody(response, exception.getMessage());
                log.error(exception.getMessage());
            }
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            setResponseBody(response, "No Token to refresh");
            log.error("No Token to refresh");
        }
    }

    private void setResponseBody(HttpServletResponse response, Object body) {
        try {
            new ObjectMapper().writeValue(response.getOutputStream(), body);
        } catch(IOException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

}
