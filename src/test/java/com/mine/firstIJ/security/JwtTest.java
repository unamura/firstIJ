package com.mine.firstIJ.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtService jwtService;

    @Test
    public void passwordNotValidTest() {
        String passwordHeader = "myPass";
        String tokenGenerated = jwtService.generateToken(passwordHeader);
        assertNotNull(tokenGenerated);
    }

    @Test
    public void passwordValidTest() {
        String passwordHeader = "Password8!";
        String tokenGenerated = jwtService.generateToken(passwordHeader);
        assertNotNull(tokenGenerated);
    }
}
