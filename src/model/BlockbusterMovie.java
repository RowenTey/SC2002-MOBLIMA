package model;

import model.enums.ShowStatus;
import model.enums.TypeMovies;

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
     * @param price price of the blockbuster movie
     */
    public BlockbusterMovie(String movieId, String title, ShowStatus status, String synopsis, String director,
            String[] cast, TypeMovies type, double price) {
        super(movieId, title, status, synopsis, director, cast, type);
        this.price = price;
    }

    /**
     * set the price of the blockbuster movie
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * get the price of the blockbuster movie
     */
    public double getPrice() {
        return this.price;
    }
}
