package src.model;

import src.model.enums.ShowStatus;
import src.model.enums.MoviesType;

/**
 * The class that initialises the blockbuster movie
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-24
 */
public class BlockbusterMovie extends Movie {
    /**
     * the base price of the blockbustor movie
     */
    private double price;

    /**
     * Constructor of blockbuster movie
     * 
     * @param movieId  of the movie
     * @param title    of the movie
     * @param status   of the movie
     * @param synopsis of the movie
     * @param director of the movie
     * @param cast     of the movie
     * @param type     of the movie
     * @param price    of the movie
     */
    public BlockbusterMovie(String movieId, String title, ShowStatus status, String synopsis, String director,
            String[] cast, MoviesType type, double price) {
        super(movieId, title, status, synopsis, director, cast, type);
        setPrice(price);
    }

    /**
     * Sets the base price of the movie
     * 
     * @param price of the movie
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the base price of the movie
     * 
     * @return base price of the movie
     */
    public double getPrice() {
        return price;
    }
}
