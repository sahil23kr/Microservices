package com.TaskManagement1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TaskManagement1Application {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagement1Application.class, args);
	}

}
