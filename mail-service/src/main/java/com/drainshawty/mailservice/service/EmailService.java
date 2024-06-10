package com.drainshawty.mailservice.service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
@RequiredArgsConstructor
public class EmailService {
    @NonFinal @Value("${spring.mail.username}") String from;
    JavaMailSender sender;
    public void send(String to, String subject, String body) {
        this.sender.send(msg -> {
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setFrom(from);
            msg.setSubject(subject);
            msg.setText(body);
        });
    }
}
