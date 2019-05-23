package co.vujade.moviereviewservice;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@SpringBootApplication
public class MovieReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieReviewServiceApplication.class, args);
	}
	
	@Bean
	//@LoadBalanced
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
