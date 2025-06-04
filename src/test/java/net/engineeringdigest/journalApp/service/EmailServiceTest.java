package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Test
    public void testSendMail(){
        emailService.sendEmail("rajatrana2309@gmail.com","YheLe","Sending the mail for checking ");
    }
}