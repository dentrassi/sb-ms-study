package co.vujade.reviewdataservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import co.vujade.reviewdataservice.models.Review;

@SpringBootApplication
public class ReviewDataServiceApplication {
	
	private final String JSON_FILE = "/data/reviewdata.json";

	public static void main(String[] args) {
		SpringApplication.run(ReviewDataServiceApplication.class, args);
	}
	
	@Bean
	public List <Review> getReviewList(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			InputStream is = TypeReference.class.getResourceAsStream(JSON_FILE);
			List <Review> reviewList = mapper.readValue(
											is, 
											TypeFactory.defaultInstance().constructCollectionType(
													List.class, Review.class));
			return reviewList;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
