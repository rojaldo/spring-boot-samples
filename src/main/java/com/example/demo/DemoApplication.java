package com.example.demo;

import com.example.demo.apod.ApodEntity;
import com.example.demo.apod.ApodRepository;
import com.example.demo.customer.Customer;
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
			repository.save(new ApodEntity("All of These Space Images are Fake Except One",
					"Why would you want to fake a universe? For one reason -- to better understand our real universe. Many astronomical projects seeking to learn properties of our universe now start with a robotic telescope taking sequential images of the night sky. Next, sophisticated computer algorithms crunch these digital images to find stars and galaxies and measure their properties.  To calibrate these algorithms, it is useful to test them on fake images from a fake universe to see if the algorithms can correctly deduce purposely imprinted properties. The featured mosaic of fake images was created to specifically mimic the images that have appeared on NASA's Astronomy Picture of the Day (APOD).  Only one image of the 225 images is real -- can you find it? The accomplished deceptors have made available individual fake APOD images that can be displayed by accessing their ThisIsNotAnAPOD webpage or Twitter feed. More useful for calibrating and understanding our distant universe, however, are fake galaxies -- a sampling of which can be seen at their ThisIsNotAGalaxy webpage.    Astrophysicists: Browse 2,600+ codes in the Astrophysics Source Code Library",
					"2021-11-09", "image", "https://apod.nasa.gov/apod/image/2111/AIapods01_Geach_960.jpg"));

			repository.save(new ApodEntity("A Filament Leaps from the Sun",
					"Why, sometimes, does part of the Sun's atmosphere leap into space? The reason lies in changing magnetic fields that thread through the Sun's surface.  Regions of strong surface magnetism, known as active regions, are usually marked by dark sunspots.  Active regions can channel charged gas along arching or sweeping magnetic fields -- gas that sometimes falls back, sometimes escapes, and sometimes not only escapes but impacts our Earth.  The featured one-hour time-lapse video -- taken with a small telescope in France -- captured an eruptive filament that appeared to leap off the Sun late last month. The filament is huge: for comparison, the size of the Earth is shown on the upper left. Just after the filament lifted off, the Sun emitted a powerful X-class flare while the surface rumbled with a tremendous solar tsunami. A result was a cloud of charged particles that rushed into our Solar System but mostly missed our Earth -- this time. However, enough solar plasma did impact our Earth's magnetosphere to create a few faint auroras.",
					"2021-11-08", "video", "https://www.youtube.com/embed/7NykS2kv_k8?playlist=7NykS2kv_k8&loop=1;rel=0&autoplay=1"));

			for (ApodEntity customer : repository.findAll()) {
				log.info(customer.toString());
			}
		};
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
