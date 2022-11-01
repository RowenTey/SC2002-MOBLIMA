package src.model;

import src.model.enums.ShowStatus;
import src.model.enums.MoviesType;

/**
 * The class that initialises the 3D movie
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-24
 */
public class ThreeDMovie extends Movie {
    /**
     * the base price of the 3D movie
     */
    private double price;

    /**
     * Constructor of 3D movie
     * 
     * @param movieId Id of movie
     * @param title title of movie
     * @param status {@link ShowStatus} of movie
     * @param synopsis synopsis of movie
     * @param director director of movie
     * @param cast cast of movie
     * @param type {@link MovieType} of movie
     * @param price base price of 3D movie
     */
    public ThreeDMovie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast,
            MoviesType type, double price) {
        super(movieId, title, status, synopsis, director, cast, type);
        setPrice(price);
    }

    /**
     * Sets the base price of the 3D movie
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the price of the 3D movie
     * 
     * @return the base price of 3D movie
     */
    public double getPrice() {
        return price;
    }
}
