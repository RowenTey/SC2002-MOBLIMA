package model;

import java.io.Serializable;

/**
 * The class that stores customer review
 * 
 * @author Ace Ang
 * @version 1.0
 * @since 2022-10-20
 */

public class Review implements Serializable {
    /*
     * For Java Serializable
     */
    private static final long serialVersionUID = 7L;

    /**
     * string that contains the actual review
     */
    private String review;

    /**
     * rating for the review
     */
    private double rating;

    /**
     * Constructor of Review
     *
     */
    public Review(String review, double rating) {
        setReview(review);
        setRating(rating);
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
