package com.cursospring.baratierisale.config;

import com.cursospring.baratierisale.entities.CardPayment;
import com.cursospring.baratierisale.entities.PaymentBoleto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(CardPayment.class);
                objectMapper.registerSubtypes(PaymentBoleto.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}
