package model;

/**
 * The class that customer review
 * 
 * @author Ace Ang
 * @version 1.0
 * @since 2022-10-20
 */

public class Review {

    /**
     * review ID for review
     */
    private int reviewId;

    /**
     * string that contains the actual review
     */
    private String review;

    /**
     * rating for the review
     */
    private double rating;

    /**
     * Gets the ID of the booking
     *
     * @return the ID of the booking
     */
    public int getReviewId() {
        return reviewId;
    }

    /**
     * Sets the ID of the booking
     *
     * @param reviewId ID of the booking
     */
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * Gets the review
     *
     * @return the review
     */
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
