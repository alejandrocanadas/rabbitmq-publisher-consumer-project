package com.example.demoPublisher.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.name}")
    private String nombreCola;

    public PublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    public void send(String nombre, String correo, String estado) {
        String mensaje = "Nombre: " + nombre + ", Correo: " + correo + ", Estado: " + estado;
        rabbitTemplate.convertAndSend(nombreCola, mensaje);
        System.out.println("Mensaje enviado: " + mensaje);
    }

}
