package co.vujade.reviewdataservice.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import co.vujade.reviewdataservice.models.Review;
import co.vujade.reviewdataservice.models.ReviewData;
import co.vujade.reviewdataservice.models.SubjectType;

@RestController
@RequestMapping("/reviews")
public class ReviewDataResource {
	
	@Autowired
	List <Review> reviewList;
	
	@RequestMapping("/user/{userid}")
	public ReviewData getReviewByUser(@PathVariable("userid") Integer userId) {
		
		return findReviews(userId, SubjectType.REVIEWER);
		
	}
	
	/*
	 * private ReviewData findReviewByUserId(Integer userId, List <Review> reviews)
	 * {
	 * 
	 * Iterator<Review> iter = reviews.iterator();
	 * 
	 * while (iter.hasNext()) { Review review = iter.next(); if
	 * (review.getReviewerId().equals(userId)) { return review; } }
	 * 
	 * return null; }
	 */
	
	private ReviewData findReviews(final Integer subjectId, SubjectType subjectType) {
		
		ReviewData reviewData = new ReviewData();
		
		reviewData.setSubjectId(subjectId);
		reviewData.setSubjectType(subjectType);
		
		List<Review> reviews = null;
		
		Predicate<Review> filter = (subjectType.equals(SubjectType.MOVIE)) ? 
										review -> review.getMovieId().equals(subjectId) :
											review -> review.getReviewerId().equals(subjectId);
											
		reviews = FluentIterable.from(reviewList).filter(filter).toList();
											
		reviewData.setReviews(reviews);
		
		return reviewData;

	}	
	
	@RequestMapping("/movie/{movieid}")
	public ReviewData getReviewByMovie(@PathVariable("movieid") Integer movieId) {
		
		return findReviews(movieId, SubjectType.MOVIE);
		
	}
	
	/*
	 * private ReviewData findReviewByMovieId(Integer movieId, List <Review>
	 * reviews) {
	 * 
	 * Iterator<Review> iter = reviews.iterator();
	 * 
	 * while (iter.hasNext()) { Review review = iter.next(); if
	 * (review.getMovieId().equals(movieId)) { return review; } }
	 * 
	 * return null; }
	 */

}
