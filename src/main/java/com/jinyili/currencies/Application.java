package com.jinyili.currencies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class })
@RestController
@ControllerAdvice
@EnableCaching

public class  Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
