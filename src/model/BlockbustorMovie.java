package model;

import model.enums.ShowStatus;

public class BlockbustorMovie extends Movie{

    private double price;

    public BlockbustorMovie(String movieId, String title, ShowStatus status, String synopsis, String director, String[] cast, int ticketSales, int overallRating, Review[] reviews, String type, double price)
    {
        super(movieId, title, status, synopsis, director, cast, ticketSales, overallRating, reviews, type);
        this.price = price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getPrice()
    {
        return this.price;
    }

}
