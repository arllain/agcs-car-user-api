package com.arllain.agcscaruserapi;

import com.arllain.agcscaruserapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgcsCarUserApiApplication {

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AgcsCarUserApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
