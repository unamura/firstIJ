package com.mine.firstIJ.security;

import org.junit.jupiter.api.*;

import static com.kosprov.jargon2.api.Jargon2.Verifier;
import static com.kosprov.jargon2.api.Jargon2.jargon2Verifier;

public class PasswordHashTest {
    private PasswordHashing passwordHashing = new PasswordHashing();

    @Test
    public void generateHashPassword() {
        String myPassword = "primaApp";
        String passwordHash = passwordHashing.encodePassword(myPassword);
        Assertions.assertNotNull(passwordHash);
    }

    @Test
    public void verifyHashPasswordGenerated() {
        String myPassword = "primaApp";
        String passwordHash = passwordHashing.encodePassword(myPassword);
        Assertions.assertNotNull(passwordHash);
        // Just get a hold on the verifier. No special configuration needed
        Verifier verifier = jargon2Verifier();
        Boolean passwordsMatch = verifier.hash(passwordHash).password(myPassword.getBytes()).verifyEncoded();
        Assertions.assertTrue(passwordsMatch);
    }
}
