package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tn.esprit.spring.control.UserController;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.UserService;

@SpringBootApplication
public class SpringBootDataJpaMvcJspApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaMvcJspApplication.class, args);
		
	
		
	}

}
