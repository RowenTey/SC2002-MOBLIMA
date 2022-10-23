package model;

import java.io.Serializable;

import model.enums.ShowStatus;

/**
 * The class that stores a movie
 *
 * @author Ace Ang
 * @version 1.0
 * @since 2022-10-20
 */

public class Movie implements Serializable, Comparable<Movie> {
    /**
     * For java serializable
     */
    protected static final long serialVersionUID = 6L;

    /**
     * movie ID of movie
     */
    private String movieId;

    /**
     * title of movie
     */
    private String title;

    /**
     * {@link ShowStatus} of movie
     */
    private ShowStatus status;

    /**
     * synopsis of movie
     */
    private String synopsis;

    /**
     * director of movie
     */
    private String director;

    /**
     * cast of movie
     */
    private String[] cast;

    /**
     * total ticket sales of movie
     */
    private int ticketSales;

    /**
     * overall rating of movie based on average of ratings
     */
    private int overallRating;

    /**
     * {@link Review[]} for movie
     */
    private Review[] reviews;

    /**
     * type of movie
     */
    private String type;

    /**
     * Constructor of Movie
     * 
     */

    public Movie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast, int ticketSales, int overallRating, Review[] reviews, String type) {
        this.movieId = movieId;
        this.title = title;
        this.status = status;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.ticketSales = ticketSales;
        this.overallRating = overallRating;
        this.reviews = reviews;
        this.type = type;
    }

    /**
     * Gets the ID of the movie
     *
     * @return the ID of the movie
     */
    public String getMovieId() {
        return movieId;
    }

    /**
     * Sets the ID of the movie
     *
     * @param movieId ID of the movie
     */
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    /**
     * Gets the title of the movie
     *
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the movie
     *
     * @param title title of the movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the status of the movie
     *
     * @return the status of the movie
     */
    public ShowStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the movie
     *
     * @param status status of the movie
     */
    public void setStatus(ShowStatus status) {
        this.status = status;
    }

    /**
     * Gets the synopsis of the movie
     *
     * @return the synopsis of the movie
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Sets the synopsis of the movie
     *
     * @param synopsis synopsis of the movie
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Gets the director of the movie
     *
     * @return the director of the movie
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets the director of the movie
     *
     * @param director director of the movie
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Gets the cast of the movie
     *
     * @return array of the cast of the movie
     */
    public String[] getCast() {
        return cast;
    }

    /**
     * Sets the cast of the movie
     *
     * @param cast cast of the movie
     */
    public void setCast(String[] cast) {
        this.cast = cast;
    }

    /**
     * Gets the total ticket sales of the movie
     *
     * @return total ticket sales of the movie
     */
    public int getTicketSales() {
        return ticketSales;
    }

    /**
     * Sets the total ticket sales of the movie
     *
     * @param ticketSales total ticket sales of the movie
     */
    public void setTicketSales(int ticketSales) {
        this.ticketSales = ticketSales;
    }

    /**
     * Gets the overall rating of the movie
     *
     * @return overall rating of the movie
     */
    public int getOverallRating() {
        for (int i = 0; i < reviews.length; i++) {
            this.overallRating += reviews[i].getRating();
        }
        this.overallRating /= reviews.length;
        return overallRating;
    }

    /**
     * Sets the overall rating of the movie
     *
     * @param overallRating overall rating of the movie
     */
    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    /**
     * Gets the review of movie
     *
     * @return {@link Review[]} that is assigned to this movie
     */
    public Review[] getReviews() {
        return reviews;
    }

    /**
     * Sets the {@link Review[]} of movie
     *
     * @param reviews {@link Review[]} of movie
     */
    public void setReviews(Review[] reviews) {
        this.reviews = reviews;
    }

    /**
     * Gets the type of the movie
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the movie
     *
     * @param type type for the movie
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Movie movie) {
        if (this == movie) {
            return 0;
        }
        // int thisOrderId = Integer.parseInt(this.getOrderId().substring(1));
        // int thatOrderId = Integer.parseInt(order.getOrderId().substring(1));

        // return thisOrderId - thatOrderId;
        return 1;
    }
}
