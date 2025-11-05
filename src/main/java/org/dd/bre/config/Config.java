package org.dd.bre.config;

import org.dd.bre.Service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class Config {

    @Bean
    public ModelMapper moderMapper() {
        return new ModelMapper();
    }

}
