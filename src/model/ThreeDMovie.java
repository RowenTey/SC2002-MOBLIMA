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
     * @param movieId  of movie
     * @param title    of movie
     * @param status   of movie
     * @param synopsis of movie
     * @param director of movie
     * @param cast     of movie
     * @param price    of 3D movie
     */
    public ThreeDMovie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast,
            double price) {
        super(movieId, title, status, synopsis, director, cast);
        setPrice(price);
        setType(MoviesType.THREE_D);
    }

    /**
     * Sets the base price of the 3D movie
     * 
     * @param price of movie
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the price of the 3D movie
     * 
     * @return the base price of 3D movie
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
