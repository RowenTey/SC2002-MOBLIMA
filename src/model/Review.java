package src.model;

import java.io.Serializable;

/**
 * The class that stores movie goer's review
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
     * Content of review
     */
    private String review;

    /**
     * Rating of review 
     */
    private double rating;

    /**
     * Constructor of Review
     *
     * @param review for the movie
     * @param rating for the movie
     */
    public Review(String review, double rating) {
        setReview(review);
        setRating(rating);
    }

    /**
     * Sets the content of review 
     *
     * @param review content
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Sets the rating of review 
     *
     * @param rating of review 
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Gets the content of review
     *
     * @return content of review
     */
    public String getReview() {
        return review;
    }

    /**
     * Gets the rating of review
     *
     * @return rating of review
     */
    public double getRating() {
        return rating;
    }
}
