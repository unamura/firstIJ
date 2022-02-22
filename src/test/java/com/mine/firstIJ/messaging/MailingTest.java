package com.mine.firstIJ.messaging;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailingTest {
    @Autowired
    private GmailService gmailService;

    @Test
    public void sendEmailTest() {
        String to = "manuel.murari@gmail.com";
        String subject = "Test mail 1";
        String text = "Ciao, sono Spring Boot.";
        gmailService.sendSimpleMessage(to, subject, text);
    }
}
