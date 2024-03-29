package ru.abakumova.appealsapp.services;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendTextEmail(String to, String subject, String text);

    void sendEmailWithAttachment();

    void sendHTMLEmail();
}
