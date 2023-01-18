package com.example.company.roomreservationservice.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
@Getter
public class RefreshScopedService {

    @Value("${test.property}")
    private String testProperty;

}