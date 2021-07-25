package com.tts.h2Explore;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tts.h2Explore.domain.Customer;
import com.tts.h2Explore.repository.CustomerRepository;



@SpringBootApplication
public class H2ExploreApplication {
	private static final Logger log = LoggerFactory.getLogger(H2ExploreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(H2ExploreApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			repository.save(new Customer("Michael", "Smith"));
			repository.save(new Customer("Aaron", "Moon"));
			repository.save(new Customer("Kim", "Lassiter"));
			repository.save(new Customer("Joan", "Daniels"));
			repository.save(new Customer("Eric", "Patterson"));

			// read all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// read an individual customer by ID
			Optional<Customer> value = repository.findById(1L);
				if (value.isPresent()) {
					log.info("Customer found with findById(1L):");
					log.info("--------------------------------");
					log.info(value.get().toString());
					log.info("");
				}

			// read customers by last name
			log.info("Customer found with findByLastName('Patterson'):");
			log.info("--------------------------------------------");
			List<Customer> customers = repository.findByLastName("Patterson");
					for (Customer customer : customers) 
					{
						log.info(customer.toString());
				
					}
			
				log.info("");
		};
	}
}

