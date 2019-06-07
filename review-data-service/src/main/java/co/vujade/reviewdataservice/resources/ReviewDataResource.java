package co.vujade.reviewdataservice.resources;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.vujade.reviewdataservice.models.Review;

@RestController
@RequestMapping("/reviews")
public class ReviewDataResource {
	
	@Autowired
	List <Review> reviewList;
	
	@RequestMapping("/user/{userid}")
	public Review getReviewByUser(@PathVariable("userid") Integer userId) {
		
		return findReviewByUserId(userId, reviewList);
		
	}
	
	private Review findReviewByUserId(Integer userId, List <Review> reviews) {
		
		Iterator<Review> iter = reviews.iterator();
		
		while (iter.hasNext()) {
			Review review = iter.next();
			if (review.getReviewerId().equals(userId)) {
				return review;
			}
		}
		
		return null;
	}
	
	@RequestMapping("/movie/{movieid}")
	public Review getReviewByMovie(@PathVariable("movieid") Integer movieId) {
		
		return findReviewByMovieId(movieId, reviewList);
		
	}
	
	private Review findReviewByMovieId(Integer movieId, List <Review> reviews) {
		
		Iterator<Review> iter = reviews.iterator();
		
		while (iter.hasNext()) {
			Review review = iter.next();
			if (review.getMovieId().equals(movieId)) {
				return review;
			}
		}
		
		return null;
	}


}
