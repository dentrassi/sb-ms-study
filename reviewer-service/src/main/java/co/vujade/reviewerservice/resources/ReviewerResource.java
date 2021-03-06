package co.vujade.reviewerservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import co.vujade.reviewerservice.models.Data;
import co.vujade.reviewerservice.models.UserData;

@RestController
@RequestMapping("/api")
public class ReviewerResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String REQRES_BASE_URL = "https://reqres.in/api/";
	
	@RequestMapping( value="/reviewers") 
	public ResponseEntity<String> getUsers(){
	 
		return getUsersByPage("1");
	}
	
	@RequestMapping( value="/reviewers", params="page") 
	public ResponseEntity<String> getUsersByPage(@RequestParam("page") String page){
	 
		return restTemplate.getForEntity(
				REQRES_BASE_URL +
				"users?page=" +
				page,
				String.class);
	}
	
	@RequestMapping("/reviewers/{reviewerId}") public UserData getUser(@PathVariable("reviewerId") String id) {
	
		  UserData reviewer;
		try {
			reviewer = restTemplate.getForObject( 
					  							REQRES_BASE_URL + "users/" +
					  							id, 
					  							UserData.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			return emptyUser(id);
		}
	  
	  
		  return reviewer; 
	  }

	private UserData emptyUser(String id) {
		
		UserData user = new UserData();
		Data d = new Data();
		
		d.setId(Integer.valueOf(id));
		d.setFirstName("Not");
		d.setLastName("Found");
		d.setEmail("usernotfound@reqres.in");
		
		user.setData(d);
		
		return user;
	}
}
