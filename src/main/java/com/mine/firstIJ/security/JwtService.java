package com.mine.firstIJ.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class JwtService {
    @Value("${user-token.secret}")
    private String secret;

    public String generateToken(String password) {
        try {
            if (!isValidPassword(password)) {
                throw new IllegalArgumentException("Password is not valid: ");
            }
            // Header Claims
            Algorithm algorithmHS = Algorithm.HMAC256(secret);
            Date actualDate = new Date();

            return JWT.create()
                    .withIssuer("iss")
                    .withIssuedAt(actualDate)
                    .withSubject("subj")
                    .withClaim("password", password)
                    .sign(algorithmHS);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
