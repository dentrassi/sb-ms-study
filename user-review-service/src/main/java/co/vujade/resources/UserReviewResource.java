package co.vujade.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.math.Stats;

import co.vujade.userreviewservice.models.Movie;
import co.vujade.userreviewservice.models.MovieReview;
import co.vujade.userreviewservice.models.MovieReviews;
import co.vujade.userreviewservice.models.Review;
import co.vujade.userreviewservice.models.ReviewData;
import co.vujade.userreviewservice.models.UserData;
import co.vujade.userreviewservice.models.UserReview;
import co.vujade.userreviewservice.models.UserReviews;

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

	//get all reviews by given user
	@RequestMapping("/user/{userId}")
	public UserReviews getUserReviews(@PathVariable("userId") Integer userId) {
		
		//fetch reviewer info based on userid
		UserData reviewer = fetchUserInfo(userId);
		
		//fetch review based on userid
		ReviewData reviewData = restTemplate.getForObject(REVIEWDATA_BASE_URL+PATH_USER+userId, ReviewData.class);
		
		//fetch movie info based on movieID
		//Movie movie = restTemplate.getForObject(MOVIE_BASE_URL+review.getMovieId(), Movie.class);
		
		//build user reviews object
		UserReviews userReviews = new UserReviews();
		
		//set user data
		userReviews.setReviewerId(reviewer.getData().getId());
		userReviews.setName(new StringBuilder()		
				.append(reviewer.getData().getFirstName())
				.append(" ")
				.append(reviewer.getData().getLastName())
				.toString());
		userReviews.setEmail(reviewer.getData().getEmail());
		
		//set count from review
		userReviews.setTotalReviews(reviewData.getReviews().size());
		
		//set reviews .. get movie info for each review
		userReviews.setMovieReviews(buildMovieReviews(reviewData.getReviews()));
		
		return userReviews;
	}

	private UserData fetchUserInfo(Integer userId) {
		UserData reviewer = restTemplate.getForObject(REVIEWER_BASE_URL+userId, UserData.class);
		return reviewer;
	}

	private List<MovieReview> buildMovieReviews(List<Review> list) {
		
		List <MovieReview> movieReviewList = new ArrayList<MovieReview>();

		for (Review review: list) {
			
			MovieReview mReview = new MovieReview();
			
			mReview.setRating(review.getRating());
			mReview.setReview(review.getReview());
			
			mReview.setMovie(fetchMovieInfo(review.getMovieId()));
			
			movieReviewList.add(mReview);
		}
		
		return movieReviewList;
	}

	/*
	 * private UserReview buildUserReview(UserData reviewer, Review review, Movie
	 * movie) {
	 * 
	 * UserReview userReview = new UserReview();
	 * 
	 * userReview.setReviewerId(reviewer.getData().getId()); userReview.setName(new
	 * StringBuilder() .append(reviewer.getData().getFirstName()) .append(" ")
	 * .append(reviewer.getData().getLastName()) .toString());
	 * userReview.setEmail(reviewer.getData().getEmail());
	 * 
	 * userReview.setMovieId(movie.getId()); userReview.setTitle(movie.getTitle());
	 * userReview.setOverview(movie.getOverview());
	 * 
	 * userReview.setRating(review.getRating());
	 * userReview.setReview(review.getReview());
	 * 
	 * 
	 * return userReview; }
	 */

	@RequestMapping("/movie/{movieId}")
	public MovieReviews getMovieReviews(@PathVariable("movieId") Integer movieId) {
		
		//fetch movie info based on movieID
		Movie movie = fetchMovieInfo(movieId);

		//fetch review based on movieId
		ReviewData reviewData = restTemplate.getForObject(REVIEWDATA_BASE_URL+PATH_MOVIE+movieId, ReviewData.class);

		//fetch reviewer info based on userid .. commenting since now has to be fetched for each reviewer
		//UserData reviewer = restTemplate.getForObject(REVIEWER_BASE_URL+review.getReviewerId(), UserData.class);
		
		//build MovieReviews
		MovieReviews movieReviews = new MovieReviews();
		
		movieReviews.setMovieId(movie.getId());
		movieReviews.setTitle(movie.getTitle());
		movieReviews.setOverview(movie.getOverview());
		movieReviews.setImdb_id(movie.getImdb_id());
		
		movieReviews.setAverageRating(calculateAverage(reviewData.getReviews()));
		
		movieReviews.setUserReviews(buildUserReviews(reviewData.getReviews()));
		
		return movieReviews;
	}

	private List<UserReview> buildUserReviews(List<Review> list) {
		List <UserReview> userReviewList = new ArrayList<UserReview>();

		for (Review review: list) {
			
			UserReview uReview = new UserReview();
			
			uReview.setRating(review.getRating());
			uReview.setReview(review.getReview());
			uReview.setMovieId(review.getMovieId());
			
			uReview.setReviewerId(review.getReviewerId());
			UserData reviewer = fetchUserInfo(review.getReviewerId());
			
			uReview.setEmail(reviewer.getData().getEmail());
			uReview.setName(new StringBuilder()
											.append(reviewer.getData().getFirstName())
											.append(" ")
											.append(reviewer.getData().getLastName())
											.toString());
			
			userReviewList.add(uReview);
		}
		
		return userReviewList;
	}

	private Double calculateAverage(List<Review> list) {

		List <Integer> ratings = new ArrayList<Integer>();
		
		for (Review review : list) {
			ratings.add(review.getRating());
		}
		
		return Stats.meanOf(ratings);
		
	}

	private Movie fetchMovieInfo(Integer movieId) {
		Movie movie = restTemplate.getForObject(MOVIE_BASE_URL + movieId, Movie.class);
		return movie;
	}
	

}
