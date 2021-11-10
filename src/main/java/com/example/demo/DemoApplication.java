package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
		};
	}

	@Bean
	public CommandLineRunner demoApod(ApodRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Apod("All of These Space Images are Fake Except One",
					"Why would you want to fake a universe? For one reason -- to better understand our real universe. Many astronomical projects seeking to learn properties of our universe now start with a robotic telescope taking sequential images of the night sky. Next, sophisticated computer algorithms crunch these digital images to find stars and galaxies and measure their properties.  To calibrate these algorithms, it is useful to test them on fake images from a fake universe to see if the algorithms can correctly deduce purposely imprinted properties. The featured mosaic of fake images was created to specifically mimic the images that have appeared on NASA's Astronomy Picture of the Day (APOD).  Only one image of the 225 images is real -- can you find it? The accomplished deceptors have made available individual fake APOD images that can be displayed by accessing their ThisIsNotAnAPOD webpage or Twitter feed. More useful for calibrating and understanding our distant universe, however, are fake galaxies -- a sampling of which can be seen at their ThisIsNotAGalaxy webpage.    Astrophysicists: Browse 2,600+ codes in the Astrophysics Source Code Library",
					"https://apod.nasa.gov/apod/image/2111/AIapods01_Geach_960.jpg", "2021-11-09", "image"));

			// fetch all customers
			for (Apod customer : repository.findAll()) {
				log.info(customer.toString());
			}
		};
	}

}
