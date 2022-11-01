package src.model;

import src.model.enums.ShowStatus;
import src.model.enums.MoviesType;

/**
 * The class that initialises the 2D movie
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-24
 */
public class TwoDMovie extends Movie {

    /**
     * the base price of the 2D movie
     */
    private double price;

    /**
     * Constructor of 2D movie
     * 
     * @param movieId  ID of the movie
     * @param title    title of the movie
     * @param status   status of the movie
     * @param synopsis synopsis of the movie
     * @param director director of the movie
     * @param cast     cast list of the movie
     * @param type     type of the movie
     * @param price    base price of the movie
     */
    public TwoDMovie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast,
            MoviesType type, double price) {
        super(movieId, title, status, synopsis, director, cast, type);
        setPrice(price);
    }

    /**
     * Sets the base price of the movie
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the price of the movie
     * 
     * @return base price of movie
     */
    public double getPrice() {
        return price;
    }
}
