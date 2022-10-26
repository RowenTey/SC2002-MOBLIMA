package model;

import java.io.Serializable;

/**
 * The class that stores a booking
 * 
 * @author Ace Ang
 * @version 1.0
 * @since 2022-10-20
 */

public class Booking implements Serializable {
    /**
     * For Java Serializable.
     */
    private static final long serialVersionUID = 9L;

    /**
     * transaction ID for booking
     */
    private String transactionId;

    /**
     * ticket for booking
     */
    private Ticket ticket;

    /**
     * info of the customer of the booking
     */
    private MovieGoer movieGoer;

    /**
     * seat number
     */
    private String position;

    /**
     * Constructor of Booking
     *
     */
    public Booking(String transactionId, Ticket ticket, MovieGoer movieGoer, String position) {
        setTransactionId(transactionId);
        setTicket(ticket);
        setMovieGoer(movieGoer);
        setPosition(position);
    }

    /**
     * Gets the transaction ID of the booking
     *
     * @return the transaction ID of the booking
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction ID of the booking
     *
     * @param transactionId transaction ID of the booking
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the ticket of the booking
     *
     * @return the ticket of the booking
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * Sets the ticket of the booking
     *
     * @param ticket ticket of the booking
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /**
     * Gets the name of the booking
     *
     * @return the name of the booking
     */
    public MovieGoer getMovieGoer() {
        return movieGoer;
    }

    public void setMovieGoer(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}