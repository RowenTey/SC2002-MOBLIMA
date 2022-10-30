package model;

import java.io.Serializable;

import model.enums.TypeMovies;

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
     * ticket {@link Cinema}
     */
    private Cinema cinema;

    /**
     * status of the ticket (paid/ready fopr payment)
     */
    private boolean isPaid;

    /**
     * movie type
     */
    private TypeMovies movieType;

    /**
     * Constructor of Ticket
     *
     */
    public Ticket(double price, Seat seat, Cinema cinema, String movieTitle, TypeMovies movieType) {
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
     * Gets the ticket cinema
     *
     * @return ticket {@link Cinema}
     */
    public Cinema getCinema() {
        return this.cinema;
    }

    /**
     * Sets the ticket {@link Cinema}
     *
     * @param cinema ticket {@link Cinema}
     */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * get the status of ticket
     */
    public boolean getIsPaid(){
        return this.isPaid;
    }

    /**
     * sets the status of the ticket
     */
    public void setIsPaid(boolean status){
        this.isPaid = status;
    }
    /**
     * get the type of movie
     */
    public String getMovieType(){
        return this.movieType.getLabel();
    }

    /**
     * sets the status of the ticket
     */
    public void setMovieType(TypeMovies movieType){
        this.movieType = movieType;
    }

}
