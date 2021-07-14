package com.onlinevoting.OnlineVoting.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinevoting.OnlineVoting.dto.ErrorResponse;
import com.onlinevoting.OnlineVoting.lib.JWT;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.*;

@Component
public class JwtFilter extends OncePerRequestFilter {

    Collection<String> excludeUrlPatterns = new ArrayList<>();
    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void destroy() { }    

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException,
        ServletException {
        // capturing the response from the controller
        CustomResponseWrapper capturingResponseWrapper = new CustomResponseWrapper(response);
        filterChain.doFilter(request, capturingResponseWrapper);
        // not filter GET requests
        if(request.getMethod() != "GET"){
            try {
                String jwt = this.resolveToken(request);
                JWT.decodeJWT(jwt);
                // if the JWT validation is ok continue with the chain
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                // else modify the response
                String exception = "Security exception: " + e.getMessage();
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setCode(401);
                errorResponse.setMessage(exception);

                byte[] responseToSend = restResponseBytes(errorResponse);
                ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
                ((HttpServletResponse) response).setStatus(401);
                response.setContentLength(responseToSend.length);
                response.getOutputStream().write(responseToSend);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String jwt = bearerToken.substring(7, bearerToken.length());
            return jwt;
        }
        return null;
    }

    // serialize the modified response in order to send to the client
    private byte[] restResponseBytes(ErrorResponse eErrorResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
        return serialized.getBytes();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        excludeUrlPatterns.add("/login/**");
        excludeUrlPatterns.add("/sign-up/**");
        return excludeUrlPatterns.stream()
            .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }

}
