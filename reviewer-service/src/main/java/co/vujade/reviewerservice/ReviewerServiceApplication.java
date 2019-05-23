package co.vujade.reviewerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
 * get User Details from Reqres.in APIs. 
 * Assume each user is a reviewer. 
 * Each User reviews some movies.
 * This service maintains list of user-movie reviews (hardcoded)
 * 
 * */

@SpringBootApplication
public class ReviewerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewerServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
