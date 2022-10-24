package model;

import model.enums.ShowStatus;
import model.enums.TypeMovies;

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
     * @param price price of 2D movie
     */
    public TwoDMovie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast,
            TypeMovies type, double price) {
        super(movieId, title, status, synopsis, director, cast, type);
        this.price = price;
    }

    /**
     * set the price of the 2D movie
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * get the price of the 2D movie
     */
    public double getPrice() {
        return this.price;
    }
}
