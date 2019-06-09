package co.vujade.userreviewservice.models;

import java.util.List;

public class MovieReviews {
	
	private String movieId;
	private String title;
	private String overview;
	private String imdb_id;
	
	private Double averageRating;
	
	private List<UserReview> userReviews;

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getImdb_id() {
		return imdb_id;
	}

	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public List<UserReview> getUserReviews() {
		return userReviews;
	}

	public void setUserReviews(List<UserReview> userReviews) {
		this.userReviews = userReviews;
	}

}
