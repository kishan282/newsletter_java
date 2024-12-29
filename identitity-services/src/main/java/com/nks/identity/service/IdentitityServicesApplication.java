package com.nks.identity.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class IdentitityServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentitityServicesApplication.class, args);
	}

}