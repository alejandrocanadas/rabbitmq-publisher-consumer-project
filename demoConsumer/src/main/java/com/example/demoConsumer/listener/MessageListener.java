package com.example.demoConsumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class MessageListener {
    
    @RabbitListener(queues = "cola.estado")
    public void recibirMensaje(String message) {
        System.out.println(" Mensaje recibido desde la cola: " + message);
    }
}
