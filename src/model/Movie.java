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
     * Id of movie
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
     * list of {@link Review} of movie
     */
    private ArrayList<Review> reviews;

    /**
     * {@link MoviesType} of movie
     */
    protected MoviesType type;

    /**
     * Constructor of Movie
     * 
     * @param movieId  of movie
     * @param title    of movie
     * @param status   of movie
     * @param synopsis of movie
     * @param director of movie
     * @param cast     of movie
     * 
     */
    public Movie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast) {
        setMovieId(movieId);
        setTitle(title);
        setStatus(status);
        setSynopsis(synopsis);
        setDirector(director);
        setCast(cast);
        setOverallRating(-1);
        setTicketSales(0);
        this.reviews = new ArrayList<Review>();
    }

    /**
     * Abstract method - Gets the price of the movie
     * 
     * @return price of movie
     */
    public abstract double getPrice();

    /**
     * Abstract method - Sets the ID of the movie
     * 
     * @param price to be set
     */
    public abstract void setPrice(double price);

    /**
     * Sets the Id of the movie
     *
     * @param movieId of the movie
     */
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    /**
     * Sets the title of the movie
     *
     * @param title of the movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the {@link ShowStatus} of the movie
     *
     * @param status of the movie
     */
    public void setStatus(ShowStatus status) {
        this.status = status;
    }

    /**
     * Sets the synopsis of the movie
     *
     * @param synopsis of the movie
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Sets the director of the movie
     *
     * @param director of the movie
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Sets the cast of the movie
     *
     * @param cast of the movie
     */
    public void setCast(String[] cast) {
        this.cast = cast;
    }

    /**
     * Sets the total ticket sales of the movie
     *
     * @param ticketSales of the movie
     */
    public void setTicketSales(int ticketSales) {
        this.ticketSales = ticketSales;
    }

    /**
     * Sets the overall rating of the movie
     * 
     * @param overallRating of the movie
     */
    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    /**
     * Adds {@link Review} to the list of {@link Review} of the movie
     *
     * @param review that is added to this movie
     */
    public void addReview(Review review) {
        reviews.add(review);
    }

    /**
     * Sets the {@link MoviesType} of the movie
     *
     * @param type of the movie
     */
    public abstract void setType(MoviesType type);

    /**
     * Gets the Id of the movie
     *
     * @return Id of the movie
     */
    public String getMovieId() {
        return movieId;
    }

    /**
     * Gets the title of the movie
     *
     * @return title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the {@link ShowStatus} of the movie
     *
     * @return {@link ShowStatus} of the movie
     */
    public ShowStatus getStatus() {
        return status;
    }

    /**
     * Gets the synopsis of the movie
     *
     * @return synopsis of the movie
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Gets the director of the movie
     *
     * @return director of the movie
     */
    public String getDirector() {
        return director;
    }

    /**
     * Gets the cast of the movie
     *
     * @return cast of the movie
     */
    public String[] getCast() {
        return cast;
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

    /**
     * Gets the list of {@link Review} of movie
     *
     * @return list of {@link Review} of the movie
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * Gets the {@link MoviesType} of the movie
     *
     * @return {@link MoviesType} of the movie
     */
    public abstract MoviesType getType();

    /**
     * Compare the overall ratings between two movies
     * 
     * @param movie that is to be compared with
     * @return integer {@code 1} if the current movie's overall rating is higher,
     *         {@code -1} otherwise
     */
    @Override
    public int compareTo(Movie movie) {
        if (this == movie) {
            return 0;
        }

        int thisReviewSize = this.getReviews().size();
        int thatReviewSize = movie.getReviews().size();

        if (thisReviewSize <= 1 && thatReviewSize > 1) {
            return -1;
        } else if (!(thisReviewSize <= 1 && thatReviewSize > 1)) {
            return 1;
        }

        double thisOverallRating = this.getOverallRating();
        double thatOverallRating = movie.getOverallRating();

        return thisOverallRating > thatOverallRating ? 1 : -1;
    }

    /**
     * Compare movies with their ticket sales
     * 
     * @param movie that is to be compared with
     * @return the difference between the current and compared movie's ticket sales
     */
    public int compareToTicketSales(Movie movie) {
        if (this == movie) {
            return 0;
        }
        int thisTicketSales = this.getTicketSales();
        int thatTicketSales = movie.getTicketSales();

        return (thisTicketSales - thatTicketSales);
    }
}
