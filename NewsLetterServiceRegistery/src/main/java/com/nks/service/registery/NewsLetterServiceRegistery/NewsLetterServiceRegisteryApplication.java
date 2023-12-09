package com.nks.service.registery.NewsLetterServiceRegistery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NewsLetterServiceRegisteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsLetterServiceRegisteryApplication.class, args);
	}

}
