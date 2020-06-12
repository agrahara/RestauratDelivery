package com.restaurant.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableAsync
@ComponentScan("com.restaurant.online")
public class RestaurantApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestaurantApplication.class, args);
	}

}
