package src.model;

import src.model.enums.ShowStatus;
import src.model.enums.TypeMovies;

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
     * @param movieId  ID of the movie
     * @param title    title of the movie
     * @param status   status of the movie
     * @param synopsis synopsis of the movie
     * @param director director of the movie
     * @param cast     cast list of the movie
     * @param type     type of the movie
     * @param price    price of the movie
     */
    public BlockbusterMovie(String movieId, String title, ShowStatus status, String synopsis, String director,
            String[] cast, TypeMovies type, double price) {
        super(movieId, title, status, synopsis, director, cast, type);
        setPrice(price);
    }

    /**
     * set the price of the blockbuster movie
     * 
     * @param price price of the blockbuster movie
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * get the price of the blockbuster movie
     * 
     * @return the price of the blockbuster movie
     */
    public double getPrice() {
        return price;
    }
}
