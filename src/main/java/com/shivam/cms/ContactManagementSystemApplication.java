package com.shivam.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ContactManagementSystemApplication {

	@Autowired
	Environment environment;
	public static void main(String[] args) {
		SpringApplication.run(ContactManagementSystemApplication.class, args);
	}
}
