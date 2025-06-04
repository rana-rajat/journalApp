package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body){
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            javaMailSender.send(mail);
        }catch (Exception e){
            log.error("Exception while sending the mail {}", String.valueOf(e));
        }
    }
}
