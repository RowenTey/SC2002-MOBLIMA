package src.model;

import java.io.Serializable;
import java.util.ArrayList;

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
     * Showtime of ticket
     */
    private Showtime showtime;

    /**
     * Price of ticket
     */
    private double price;

    /**
     * lis of {@link Seat} of ticket
     */
    private ArrayList<Seat> seat;

    /**
     * Status of the ticket (paid/ready for payment)
     */
    private boolean isPaid;


    /**
     * Constructor of Ticket
     *
     * @param showtime of the ticket
     * @param price      of the ticket
     * @param seat       of ticket
     */
    public Ticket(Showtime showtime,double price, ArrayList<Seat> seat) {
        setShowtime(showtime);
        setPrice(price);
        setSeat(seat);
        setIsPaid(false);
    }

    /**
     * Gets showtime of the ticket
     * @return {@link Showtime} of the ticket
     */
    public Showtime getShowtime(){
        return this.showtime;
    }

    /**
     * Sets the showtime of ticket
     * 
     * @param showtime of the ticket
     */
    public void setShowtime(Showtime showtime){
        this.showtime = showtime;
    }

    /**
     * Gets the price of the ticket
     *
     * @return price of the ticket
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the ticket
     *
     * @param price of the ticket
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the list of {@link Seat} of ticket
     *
     * @return list of seat of ticket
     */
    public ArrayList<Seat> getSeat() {
        return seat;
    }

    /**
     * Sets the {@link Seat} of ticket
     *
     * @param seat list of seat of ticket
     */
    public void setSeat(ArrayList<Seat> seat) {
        this.seat = seat;
    }

    /**
     * Get the status of ticket
     * 
     * @return boolean {@code true} if ticket was paid, {@code false}
     *         otherwise
     */
    public boolean getIsPaid() {
        return isPaid;
    }

    /**
     * Sets the status of the ticket
     * 
     * @param status of ticket
     */
    public void setIsPaid(boolean status) {
        this.isPaid = status;
    }
}
