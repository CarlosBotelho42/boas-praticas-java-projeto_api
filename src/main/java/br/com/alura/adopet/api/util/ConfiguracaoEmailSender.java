package br.com.alura.adopet.api.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class ConfiguracaoEmailSender {

    @Bean
    public SimpleMailMessage javaMailSender() {
        return new SimpleMailMessage();
    }

    public void send(SimpleMailMessage email) {
    }
}
