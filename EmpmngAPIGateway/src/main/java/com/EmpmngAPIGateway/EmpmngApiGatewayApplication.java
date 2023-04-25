package com.EmpmngAPIGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmpmngApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpmngApiGatewayApplication.class, args);
	}

}
