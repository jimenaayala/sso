package com.sso.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:whatsapp.properties")
public class WhatsAppConfig {
    // Esta clase solo se utiliza para cargar el archivo de propiedades whatsapp.properties
}
