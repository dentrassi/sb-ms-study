package co.vujade.moviereviewservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import co.vujade.moviereviewservice.models.Movie;
import co.vujade.moviereviewservice.models.Review;
import co.vujade.moviereviewservice.models.UserData;
import co.vujade.moviereviewservice.models.UserReview;

@RestController
@RequestMapping("/movie-review")
public class MovieReviewResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String REVIEWER_BASE_URL = "http://reviewer-service/api/reviewers/";
	private final String REVIEWDATA_BASE_URL = "http://review-data-service/reviews/user/";
	private final String MOVIE_BASE_URL = "http://movie-catalog-service/movies/";
	
	@RequestMapping("/user/{userId}")
	public UserReview getUserReviews(@PathVariable("userId") Integer userId) {
		
		//fetch reviewer info based on userid
		UserData reviewer = restTemplate.getForObject(REVIEWER_BASE_URL+userId, UserData.class);
		
		//fetch review based on userid (gets one for now)
		Review review = restTemplate.getForObject(REVIEWDATA_BASE_URL+userId, Review.class);
		
		//fetch movie info based on movieID
		Movie movie = restTemplate.getForObject(MOVIE_BASE_URL+review.getMovieId(), Movie.class);
		
		return buildUserReview(reviewer,review,movie);
	}

	private UserReview buildUserReview(UserData reviewer, Review review, Movie movie) {
		
		UserReview userReview = new UserReview();
		
		userReview.setReviewerId(reviewer.getData().getId());
		userReview.setName(new StringBuilder()		
								.append(reviewer.getData().getFirstName())
								.append(" ")
								.append(reviewer.getData().getLastName())
								.toString());
		userReview.setEmail(reviewer.getData().getEmail());
		
		userReview.setMovieId(movie.getId());
		userReview.setTitle(movie.getTitle());
		userReview.setOverview(movie.getOverview());
		
		userReview.setRating(review.getRating());
		userReview.setReview(review.getReview());
		
		
		return userReview;
	}
	

}
