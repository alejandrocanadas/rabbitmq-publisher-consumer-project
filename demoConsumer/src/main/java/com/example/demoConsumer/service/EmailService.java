package com.example.demoConsumer.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demoConsumer.model.EmailDTO;

@Service
public class EmailService implements IEmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(EmailDTO emailDTO) throws MessagingException {
        try {
            // Crear el correo
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            // Remitente opcional
            if (emailDTO.getFrom() != null && !emailDTO.getFrom().isBlank()) {
                helper.setFrom(emailDTO.getFrom());
            }

            helper.setTo(emailDTO.getDestinatario());
            helper.setSubject(emailDTO.getAsunto());

            // Generar contenido HTML con Thymeleaf
            Context context = new Context();
            context.setVariable("mensaje", emailDTO.getMensaje());
            String htmlContent = templateEngine.process("email", context);

            helper.setText(htmlContent, true);

            // Enviar
            javaMailSender.send(mimeMessage);
            System.out.println("Email enviado correctamente a: " + emailDTO.getDestinatario());

        } catch (Exception e) {
            throw new MessagingException("Error al enviar correo: " + e.getMessage());
        }
    }

    
}
