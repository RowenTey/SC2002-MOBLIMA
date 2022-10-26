package model;

import java.io.Serializable;

/**
 * The class that stores the movie ticket
 *
 * @author Ace Ang
 * @version 1.0
 * @since 2022-10-20
 */

public class Ticket implements Serializable {
    /**
     * For java serializable
     */
    protected static final long serialVersionUID = 9L;

    /**
     * Movie title
     */
    private String movieTitle;

    /**
     * price of ticket
     */
    private double price;

    /**
     * ticket {@link Seat}
     */
    private Seat seat;

    /**
     * ticket {@link Cineplex}
     */
    private Cineplex cineplex;

    /**
     * Constructor of Ticket
     *
     */
    public Ticket(double price, Seat seat, Cineplex cineplex, String movieTitle) {
        setPrice(price);
        setSeat(seat);
        setCineplex(cineplex);
        setMovieTitle(movieTitle);
    }

    /**
     * Gets the price of the ticket
     *
     * @return the price of the booking
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the ticket
     *
     * @param price price of the ticket
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the title of the movie
     * 
     * @return MovieTitle
     */
    public String getMovieTitle() {
        return this.movieTitle;
    }

    /**
     * Sets the movie title
     * 
     * @param movieTitle
     */
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    /**
     * Gets the ticket seat
     *
     * @return ticket {@link Seat}
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Sets the ticket {@link Seat}
     *
     * @param seat ticket {@link Seat}
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /**
     * Gets the ticket cineplex
     *
     * @return ticket {@link Cineplex}
     */
    public Cineplex getCineplex() {
        return cineplex;
    }

    /**
     * Sets the ticket {@link Cineplex}
     *
     * @param cineplex ticket {@link Cineplex}
     */
    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }
}
