package com.mine.firstIJ.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JwtService {
    @Value("${user-token.secret}")
    private String secret;

    public String generateToken() {
        try {
            // Header Claims
            Algorithm algorithmHS = Algorithm.HMAC256(secret);
            Date actualDate = new Date();

            return JWT.create()
                    .withIssuer("iss")
                    .withIssuedAt(actualDate)
                    .withSubject("subj")
                    .sign(algorithmHS);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
