package src.model;

import java.io.Serializable;
import java.util.ArrayList;

import src.model.enums.ShowStatus;
import src.model.enums.MoviesType;

/**
 * The class that stores a movie
 *
 * @author Ace Ang
 * @version 1.0
 * @since 2022-10-20
 */

public abstract class Movie implements Serializable, Comparable<Movie> {
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
    private double overallRating;

    /**
     * {@link Review} for movie
     */
    private ArrayList<Review> reviews;

    /**
     * type of movie
     */
    private MoviesType type;

    /**
     * Constructor of Movie
     * 
     */
    public Movie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast,
            MoviesType type) {
        setMovieId(movieId);
        setTitle(title);
        setStatus(status);
        setSynopsis(synopsis);
        setDirector(director);
        setCast(cast);
        setType(type);
        setOverallRating(-1);
        setTicketSales(0);
        this.reviews = new ArrayList<Review>();
    }

    /**
     * Abstract method - Gets the price of the movie
     */
    public abstract double getPrice();

    /**
     * Abstract method - Sets the ID of the movie
     */
    public abstract void setPrice(double price);

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
    public double getOverallRating() {
        if (reviews.size() == 0) {
            return -1;
        }

        double dummy = 0;
        for (int i = 0; i < reviews.size(); i++) {
            dummy += reviews.get(i).getRating();
        }
        dummy /= reviews.size();
        setOverallRating(dummy);
        return dummy;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    /**
     * Gets the review of movie
     *
     * @return {@link Review[]} that is assigned to this movie
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * Sets the review of movie
     *
     * @param {@link Review[]} that is added to this movie
     */
    public void addReview(Review review) {
        reviews.add(review);
    }

    /**
     * Gets the type of the movie
     *
     * @return the type
     */
    public MoviesType getType() {
        return type;
    }

    /**
     * Sets the type of the movie
     *
     * @param type type for the movie
     */
    public void setType(MoviesType type) {
        this.type = type;
    }

    @Override
    public int compareTo(Movie movie) {
        if (this == movie) {
            return 0;
        }
        double thisOverallRating = this.getOverallRating();
        double thatOverallRating = movie.getOverallRating();

        return thisOverallRating > thatOverallRating ? 1 : -1;
    }

    public int compareToTicketSales(Movie movie) {
        if (this == movie) {
            return 0;
        }
        int thisTicketSales = this.getTicketSales();
        int thatTicketSales = movie.getTicketSales();

        return (thisTicketSales - thatTicketSales);
    }
}
