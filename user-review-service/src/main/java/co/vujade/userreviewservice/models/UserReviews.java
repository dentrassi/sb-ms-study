package co.vujade.userreviewservice.models;

import java.util.List;

public class UserReviews {
	
	private Integer reviewerId;
	private String email;
	private String name;
	
	private Integer totalReviews;
	
	private List<MovieReview> movieReviews;

	public Integer getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(Integer reviewerId) {
		this.reviewerId = reviewerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(Integer totalReviews) {
		this.totalReviews = totalReviews;
	}

	public List<MovieReview> getMovieReviews() {
		return movieReviews;
	}

	public void setMovieReviews(List<MovieReview> movieReviews) {
		this.movieReviews = movieReviews;
	}

}
