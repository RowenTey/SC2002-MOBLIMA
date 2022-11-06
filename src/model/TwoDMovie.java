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
     * @param movieId  of the movie
     * @param title    of the movie
     * @param status   of the movie
     * @param synopsis of the movie
     * @param director of the movie
     * @param cast     of the movie
     * @param price    of the movie
     */
    public TwoDMovie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast,
            double price) {
        super(movieId, title, status, synopsis, director, cast);
        setPrice(price);
        setType(MoviesType.TWO_D);
    }

    /**
     * Sets the base price of the movie
     * 
     * @param price of movie
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the price of the movie
     * 
     * @return base price of movie
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Sets the {@link MoviesType} of the movie
     *
     * @param type of the movie
     */
    @Override
    public void setType(MoviesType type) {
        this.type = type;
    }

    /**
     * Gets the {@link MoviesType} of the movie
     *
     * @return {@link MoviesType} of the movie
     */
    @Override
    public MoviesType getType() {
        return type;
    }

}
