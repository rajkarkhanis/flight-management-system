package com.flights;

import com.flights.bean.User;
import com.flights.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FlightsApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlightsApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args->
		{
			userService.addUser(new User("admin","boss","123","1234567890","boss@mail.com" ));
			userService.addUser(new User("customer","sid","123","1234567890","sid@mail.com" ));
		};
	}
}
