package com.example.company.roomreservationservice.config;

import com.example.company.roomreservationservice.converter.RoomReservationToDto;
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
        service.addConverter(new RoomReservationToDto());
        return service;
    }

}
