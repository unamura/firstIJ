package com.mine.firstIJ.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kosprov.jargon2.api.Jargon2.*;

public class PasswordHashing {

    public String encryptPassword(String userPassword) {
        if (!isValidString(userPassword)) {
            throw new IllegalArgumentException("Password deve essere lunga 8-20 caratteri, avere almeno una lettera " +
                    "maiuscola ed una minuscola, contenere almeno un carattere tra @#$%! ");
        }
        byte[] userPasswordBytes = userPassword.getBytes();

        // Configure the hasher
        Hasher argonHasher = jargon2Hasher().type(Type.ARGON2d); // Data-dependent hashing
        argonHasher.memoryCost(65536);  // 64MB memory cost
        argonHasher.timeCost(3);        // 3 passes through memory
        argonHasher.parallelism(4);     // use 4 lanes and 4 threads
        argonHasher.saltLength(16);     // 16 random bytes salt
        argonHasher.hashLength(16);    // 16 bytes output hash

        // Set the password and calculate the encoded hash
        return argonHasher.password(userPasswordBytes).encodedHash();
    }

    public Boolean verifyEncryptedPassword(String encryptedPassword, String insertedPassword) {
        Verifier verifier = jargon2Verifier();
        byte[] userPasswordBytes = new byte[]{};
        if (insertedPassword != null) {
            userPasswordBytes = insertedPassword.getBytes();
        }
        return verifier.hash(encryptedPassword).password(userPasswordBytes).verifyEncoded();
    }

    private Boolean isValidString(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
