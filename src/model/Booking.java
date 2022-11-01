package src.model;

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
     * Transaction ID for booking
     */
    private String transactionId;

    /**
     * {@link Ticket} for booking
     */
    private Ticket ticket;

    /**
     * {@link MovieGoer} of booking
     */
    private MovieGoer movieGoer;

    /**
     * Position of seat
     */
    private String position;

    /**
     * Constructor of Booking
     *
     * @param transactionId of the booking
     * @param ticket        of the booking
     * @param movieGoer     of the booking
     * @param position      of the booked seat
     */
    public Booking(String transactionId, Ticket ticket, MovieGoer movieGoer, String position) {
        setTransactionId(transactionId);
        setTicket(ticket);
        setMovieGoer(movieGoer);
        setPosition(position);
    }

    /**
     * Sets the transaction ID of the booking
     *
     * @param transactionId of the booking
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Sets the {@link Ticket} of the booking
     *
     * @param ticket of the booking
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /**
     * Sets the {@link MovieGoer} of the booking
     *
     * @param movieGoer of the booking
     */
    public void setMovieGoer(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
    }
    
    /**
     * Sets the position of the booked seat
     *
     * @param position of the booked seat
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Gets the transaction ID of the booking
     *
     * @return transaction ID of the booking
     */
    public String getTransactionId() {
        return this.transactionId;
    }

    /**
     * Gets the {@link Ticket} of the booking
     *
     * @return {@link Ticket} of the booking
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * Gets the {@link MovieGoer} of the booking
     *
     * @return information of the {@link MovieGoer}
     */
    public MovieGoer getMovieGoer() {
        return movieGoer;
    }

    /**
     * Gets the position of the booked seat
     *
     * @return position of the booked seat
     */
    public String getPosition() {
        return position;
    }
    
}