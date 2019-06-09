package co.vujade.moviecatalogservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import co.vujade.moviecatalogservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;
    
    private final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        
    	Movie movie;
		try {
			movie = restTemplate.getForObject(
										BASE_URL + 
										movieId + 
										"?api_key=" +  
										apiKey, 
										Movie.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			return emptyMovie(movieId);
		}
        return movie;

    }

	private Movie emptyMovie(String movieId) {
		// TODO Auto-generated method stub
		
		Movie movie = new Movie();
		
		movie.setId(movieId);
		movie.setTitle("Movie matching this ID is Not Found in MovieDB");
		movie.setOverview("Movie matching this ID is Not Found in MovieDB");
		movie.setImdb_id("Movie matching this ID is Not Found in MovieDB");
		
		return movie;
	}


}
