package model;

import model.enums.ShowStatus;
import model.enums.TypeMovies;

public class ThreeDMovie extends Movie {

    private double price;

    public ThreeDMovie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast,
            TypeMovies type, double price) {
        super(movieId, title, status, synopsis, director, cast, type);
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}
