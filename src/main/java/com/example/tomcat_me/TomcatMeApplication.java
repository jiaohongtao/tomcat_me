package com.example.tomcat_me;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @ComponentScan("com.example.tomcat_me")
public class TomcatMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TomcatMeApplication.class, args);
	}

}
