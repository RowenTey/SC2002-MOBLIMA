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
     * @param price    of the movie
     */
    public BlockbusterMovie(String movieId, String title, ShowStatus status, String synopsis, String director,
            String[] cast, double price) {
        super(movieId, title, status, synopsis, director, cast);
        setPrice(price);
        setType(MoviesType.BLOCKBUSTER);
    }

    /**
     * Sets the base price of the movie
     * 
     * @param price of the movie
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the base price of the movie
     * 
     * @return base price of the movie
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
