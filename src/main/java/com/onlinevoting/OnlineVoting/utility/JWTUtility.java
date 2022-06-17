package com.onlinevoting.OnlineVoting.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

@Component
public class JWTUtility {
    
    Algorithm algorithm = null;
    JWTVerifier verifier = null;

    @Value("${jwt.secret}")
    private String secret;
    
    private final static long ACCESS_TOKEN_TIME_LIMIT = 30; // 30 min
    private final static long REFRESH_TOKEN_TIME_LIMIT = 60 * 24 * 30; // 30 days

    @PostConstruct
    void init() {
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm)
                .withIssuer("/login","/token/refresh")
                .build();
    }

    public JWTVerifier getJWTVerifier() {
        return verifier;
    }

    public String createAccessToken(String subject, String issuer, List<String> claims) {
        return JWT.create()
            .withSubject(subject)
            .withExpiresAt(new Date(System.currentTimeMillis() + (ACCESS_TOKEN_TIME_LIMIT * 60 * 1000)))
            .withIssuer(issuer)
            .withClaim("roles", claims)
            .sign(algorithm);
    }

    public String createRefreshToken(String subject, String issuer) {
        return JWT.create()
            .withSubject(subject)
            .withExpiresAt(new Date(System.currentTimeMillis() + (REFRESH_TOKEN_TIME_LIMIT * 60 * 1000)))
            .withIssuer(issuer)
            .sign(algorithm);
    }

}
