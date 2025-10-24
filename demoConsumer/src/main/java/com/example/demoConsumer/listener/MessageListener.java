package com.example.demoConsumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demoConsumer.model.EmailDTO;
import com.example.demoConsumer.service.EmailService;


@Component
public class MessageListener {
    // Escucha la cola "cola.estado" automáticamente
    private final EmailService EmailService;

    public MessageListener(EmailService emailService) {
        this.EmailService = emailService;
    }

    @RabbitListener(queues = "cola.estado")
    public void recibirMensaje(String message) {
        System.out.println("Mensaje recibido desde la cola: " + message);

        String[] recibido = message.split(", ");

        
        String nombre = recibido[0].split(": ")[1];
        String correo = recibido[1].split(": ")[1];
        String estado = recibido[2].split(": ")[1];

        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Estado: " + estado); 

         String body = "Hola " + nombre + ",\n\n"
            + "Tu estado actual es: " + estado + ".\n"
            + "Si no fuiste tú o deseas más información, responde a este correo.\n\n"
            + "Saludos,\nEquipo de soporte.";

        EmailDTO emailDTO = new EmailDTO();
            emailDTO.setDestinatario(correo);
            emailDTO.setAsunto("Estado de tu operación: " + estado);
            emailDTO.setMensaje(body);

        EmailService.sendEmail(emailDTO);
        System.out.println("✉️ Email enviado a: " + correo);
    }
}
