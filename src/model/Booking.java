package src.model;

import java.io.Serializable;
import java.util.ArrayList;

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
     * list of booked seat
     */
    private ArrayList<Seat> seat;

    /**
     * Constructor of Booking
     *
     * @param transactionId of the booking
     * @param ticket        of the booking
     * @param movieGoer     of the booking
     * @param seat of the booking
     */
    public Booking(String transactionId, Ticket ticket, MovieGoer movieGoer, ArrayList<Seat> seat) {
        setTransactionId(transactionId);
        setTicket(ticket);
        setMovieGoer(movieGoer);
        setSeat(seat);
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
     * Sets the {@link Seat} of the booking
     * @param seat of the booking
     */
    public void setSeat(ArrayList<Seat> seat){
        this.seat = seat;
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
     * Gets the list of {@link Seat} of the booking
     * 
     * @return list of seats of the booking
     */
    public ArrayList<Seat> getSeat(){
        return this.seat;
    }
    
}