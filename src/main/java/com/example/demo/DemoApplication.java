package com.example.demo;

import com.example.demo.beers.BeerEntity;
import com.example.demo.beers.BeerRepository;
import com.example.demo.beers.BeersService;
import com.example.demo.customer.CustomerEntity;
import com.example.demo.customer.CustomerRepository;
import com.example.demo.trivial.TrivialEntity;
import com.example.demo.trivial.TrivialRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.SimpleXsdSchema;


@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner demo(CustomerRepository repository) {
	// 	return (args) -> {
	// 		// save a few customers
	// 		repository.save(new CustomerEntity("Jack", "Bauer", "uno@cualquiera.com"));
	// 		repository.save(new CustomerEntity("Chloe", "O'Brian", "dos@cualquiera.com")); 
	// 		repository.save(new CustomerEntity("Kim", "Bauer", "tres@cualquiera.com"));
	// 		repository.save(new CustomerEntity("David", "Palmer", "cuatro@cualquiera.com"));
	// 		repository.save(new CustomerEntity("Michelle", "Dessler", "cinco@cualquiera.com"));

	// 		// fetch all customers
	// 		for (CustomerEntity customer : repository.findAll()) {
	// 			log.info(customer.toString());
	// 		}
	// 		repository.findByLastName("Bauer").forEach(bauer -> {
	// 			log.info(bauer.toString());
	// 		});
	// 	};
	// }

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	// @Bean
	// public CommandLineRunner addBeers(BeerRepository beerRepository, BeersService beersService) {
	// 	return (args) -> {
	// 		for(BeerEntity b :beersService.getAllBeersFromApi() ) {
	// 			beerRepository.save(b);
	// 		}
					
	// 	};
	// }

	// @Bean
    // public CommandLineRunner demoTrivial(TrivialRepository repository) {
    //     return (args) -> {
    //         TrivialEntity p1 = new TrivialEntity();
    //         p1.setCategory("General Knowledge");
    //         p1.setType("multiple");
    //         p1.setDifficulty("medium");
    //         p1.setQuestion("What does a milliner make and sell?");
    //         p1.setCorrect_answer("Hats");
    //         p1.setIncorrect_answers("Shoes;Belts;Shirts");
    //         TrivialEntity p2 = new TrivialEntity();
    //         p2.setCategory("Science: Computers");
    //         p2.setType("boolean");
    //         p2.setDifficulty("medium");
    //         p2.setQuestion("Android versions are named in alphabetical order.");
    //         p2.setCorrect_answer("True");
    //         p2.setIncorrect_answers("False");
    //         repository.save(p1);
    //         repository.save(p2);
    //     };
    // }

}
