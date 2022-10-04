package com.flights;

import com.flights.bean.User;
import com.flights.dto.AirportDto;
import com.flights.service.AirportService;
import com.flights.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EnableSwagger2
public class FlightsApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlightsApplication.class, args);

	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.flights")).build();
	}

	//http://localhost:8080/swagger-ui/index.html



	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService, AirportService airportService){
		return args->
		{
			userService.addUser(new User("admin","boss","123","1234567890","boss@mail.com" ));
			userService.addUser(new User("customer","sid","123","1234567890","sid@mail.com" ));
			airportService.addAirport(new AirportDto("BOM","Chhatrapati Shivaji Maharaj International Airport","Mumbai"));
			airportService.addAirport(new AirportDto("MAA","Madras Airport","Chennai"));
			airportService.addAirport(new AirportDto("DEL","Indira Gandhi International Airport","Delhi"));
			airportService.addAirport(new AirportDto("GOI","Goa International Airport","Goa"));
			airportService.addAirport(new AirportDto("CCU","Netaji Subhash Chandra Bose International","Kolkata"));
		};
	}
}

