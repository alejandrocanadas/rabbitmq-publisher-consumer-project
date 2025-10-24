package com.example.demoConsumer.service;

import org.springframework.messaging.MessagingException;

import com.example.demoConsumer.model.EmailDTO;

public interface IEmailService {
    void sendEmail(EmailDTO emailDTO) throws MessagingException;  
} 