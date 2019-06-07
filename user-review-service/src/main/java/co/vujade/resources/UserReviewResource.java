package co.vujade.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import co.vujade.userreviewservice.models.Movie;
import co.vujade.userreviewservice.models.Review;
import co.vujade.userreviewservice.models.UserData;
import co.vujade.userreviewservice.models.UserReview;

@RestController
@RequestMapping("/user-review")
public class UserReviewResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String REVIEWER_BASE_URL = "http://reviewer-service/api/reviewers/";
	private final String REVIEWDATA_BASE_URL = "http://review-data-service/reviews/";
	private final String MOVIE_BASE_URL = "http://movie-catalog-service/movies/";
	private final String PATH_USER = "user/";
	private final String PATH_MOVIE = "movie/";

	@RequestMapping("/user/{userId}")
	public UserReview getUserReviews(@PathVariable("userId") Integer userId) {
		
		//fetch reviewer info based on userid
		UserData reviewer = restTemplate.getForObject(REVIEWER_BASE_URL+userId, UserData.class);
		
		//fetch review based on userid (gets one for now)
		Review review = restTemplate.getForObject(REVIEWDATA_BASE_URL+PATH_USER+userId, Review.class);
		
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

	@RequestMapping("/movie/{movieId}")
	public UserReview getMovieReviews(@PathVariable("movieId") Integer movieId) {
		
		//fetch movie info based on movieID
		Movie movie = restTemplate.getForObject(MOVIE_BASE_URL + movieId, Movie.class);

		//fetch review based on userid (gets one for now)
		Review review = restTemplate.getForObject(REVIEWDATA_BASE_URL+PATH_MOVIE+movieId, Review.class);

		//fetch reviewer info based on userid
		UserData reviewer = restTemplate.getForObject(REVIEWER_BASE_URL+review.getReviewerId(), UserData.class);
		
		
		
		return buildUserReview(reviewer,review,movie);
	}
	

}
