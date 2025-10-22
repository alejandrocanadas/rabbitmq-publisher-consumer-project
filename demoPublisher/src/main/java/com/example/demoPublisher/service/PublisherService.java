package com.example.demoPublisher.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String estadoJSON){
        rabbitTemplate.convertAndSend("cola.estado", estadoJSON);
        System.out.println("Mensaje enviado: " + estadoJSON);
    }

}
