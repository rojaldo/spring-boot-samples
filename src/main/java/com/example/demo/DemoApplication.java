package com.example.demo;

import com.example.demo.customer.CustomerEntity;
import com.example.demo.customer.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new CustomerEntity("Jack", "Bauer", "uno@cualquiera.com"));
			repository.save(new CustomerEntity("Chloe", "O'Brian", "dos@cualquiera.com")); 
			repository.save(new CustomerEntity("Kim", "Bauer", "tres@cualquiera.com"));
			repository.save(new CustomerEntity("David", "Palmer", "cuatro@cualquiera.com"));
			repository.save(new CustomerEntity("Michelle", "Dessler", "cinco@cualquiera.com"));

			// fetch all customers
			for (CustomerEntity customer : repository.findAll()) {
				log.info(customer.toString());
			}
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
		};
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
