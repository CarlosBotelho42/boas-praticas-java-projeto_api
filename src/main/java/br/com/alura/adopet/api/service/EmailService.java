package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.util.ConfiguracaoEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final ConfiguracaoEmailSender emailSender;

    public EmailService(ConfiguracaoEmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void enviarEmail(String to, String subjec, String message){

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@email.com.br");
        email.setTo(to);
        email.setSubject(subjec);
        email.setText(message);
        emailSender.send(email);
    }

}
