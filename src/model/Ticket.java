package src.model;

import java.io.Serializable;

import src.model.enums.MoviesType;

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
     * Movie title corresponding to ticket bought
     */
    private String movieTitle;

    /**
     * Price of ticket
     */
    private double price;

    /**
     * {@link Seat} of ticket
     */
    private Seat seat;

    /**
     * {@link Cinema} of ticket
     */
    private Cinema cinema;

    /**
     * Status of the ticket (paid/ready for payment)
     */
    private boolean isPaid;

    /**
     * {@link MoviesType} of ticket
     */
    private MoviesType movieType;

    /**
     * Constructor of Ticket
     *
     * @param price      of the ticket
     * @param seat       of ticket
     * @param cinema     of ticket
     * @param movieTitle corresponding to ticket bought
     * @param movieType  of ticket
     */
    public Ticket(double price, Seat seat, Cinema cinema, String movieTitle, MoviesType movieType) {
        setPrice(price);
        setSeat(seat);
        setCinema(cinema);
        setMovieTitle(movieTitle);
        setIsPaid(false);
        setMovieType(movieType);
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
     * Gets the title of the movie of ticket bought
     * 
     * @return movie title corresponding to ticket bought
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * Sets the movie title of ticket bought
     * 
     * @param movieTitle corresponding to ticket bought
     */
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    /**
     * Gets the {@link Seat} of ticket
     *
     * @return seat of ticket
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Sets the {@link Seat} of ticket
     *
     * @param seat of ticket
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /**
     * Gets the {@link Cinema} of ticket
     *
     * @return cinema of ticket
     */
    public Cinema getCinema() {
        return cinema;
    }

    /**
     * Sets the {@link Cinema} of ticket
     *
     * @param cinema of ticket
     */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
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

    /**
     * Get the {@link MoviesType} of ticket
     * 
     * @return movie type of ticket
     */
    public String getMovieType() {
        return movieType.getLabel();
    }

    /**
     * Sets the {@link MoviesType} of ticket
     * 
     * @param movieType of ticket
     */
    public void setMovieType(MoviesType movieType) {
        this.movieType = movieType;
    }

}
