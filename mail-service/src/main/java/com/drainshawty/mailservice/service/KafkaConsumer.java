package com.drainshawty.mailservice.service;

import com.drainshawty.mailservice.data.EmailData;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaConsumer {

    EmailService emailService;

    @KafkaListener(topics = "mailer", groupId = "mailer-consumer", containerFactory = "emailListener")
    public void listen(EmailData data) {
        try {
            emailService.send(data.getReceiver(), data.getTopic(), data.getMessage());
        } catch (MailSendException e) { System.out.println("[ERROR] " + e.getLocalizedMessage()); }
    }
}
