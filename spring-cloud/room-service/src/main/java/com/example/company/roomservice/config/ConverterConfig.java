package com.example.company.roomservice.config;

import com.example.company.roomservice.converter.RoomToDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class ConverterConfig {
    @Bean
    @Primary
    public ConversionService conversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new RoomToDto());
        return service;
    }

}
