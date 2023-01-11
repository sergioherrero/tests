package com.example.company.roomservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class RefreshRemoteApplicationEvent implements ApplicationListener<org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent> {

	@Value("${test.property}")
	private String testProperty;

	@Override
	public void onApplicationEvent(org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent event) {

		System.out.println("Test property from env: " + testProperty);
	}

}