package com.example.company.guestservice.application.config;

import com.example.company.guestservice.application.conversion.GuestDtoToGuestH2;
import com.example.company.guestservice.application.conversion.GuestH2ToGuestDto;
import com.example.company.guestservice.application.conversion.GuestToGuestDto;
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
        service.addConverter(new GuestToGuestDto());
        service.addConverter(new GuestH2ToGuestDto());
        service.addConverter(new GuestDtoToGuestH2());
        return service;
    }

}
