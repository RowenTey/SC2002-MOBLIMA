package model;

/**
 * The class that stores customer review
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
     * Gets the review for the movie
     *
     * @return the review
     */
    public String getReview() {
        return review;
    }

    /**
     * Sets the review for the movie
     *
     * @param review review for the movie
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Gets the rating for the movie
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets the rating for the movie
     *
     * @param rating review for the movie
     */
    public void setRating(double rating) {
        this.rating = rating;
    }
}
