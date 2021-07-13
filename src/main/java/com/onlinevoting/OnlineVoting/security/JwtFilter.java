package com.onlinevoting.OnlineVoting.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinevoting.OnlineVoting.dto.ErrorResponse;
import com.onlinevoting.OnlineVoting.lib.JWT;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import net.minidev.json.JSONObject;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
    final FilterChain filterChain) throws IOException,
        ServletException {
        try {
            String jwt = this.resolveToken(request);
            if (StringUtils.hasText(jwt)) {
                JWT.decodeJWT(jwt);
            }
        } catch (JwtException je) {
            String exception = "Security exception: " + je.getMessage();
            // response.setContentLength(exception.length());
            // response.getWriter().write(exception);
            // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // response.flushBuffer();
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(401);
            errorResponse.setMessage(exception);

            byte[] responseToSend = restResponseBytes(errorResponse);
            ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
            ((HttpServletResponse) response).setStatus(401);
            response.getOutputStream().write(responseToSend);
            return;
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

    private byte[] restResponseBytes(ErrorResponse eErrorResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
        return serialized.getBytes();
    }
}
