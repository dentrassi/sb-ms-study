package co.vujade.moviecatalogservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        
    	Movie movie = restTemplate.getForObject(
    								BASE_URL + 
    								movieId + 
    								"?api_key=" +  
    								apiKey, 
    								Movie.class);
        return movie;

    }


}