package view;

import java.util.ArrayList;

import helper.Helper;
import controller.MovieManager;
import model.Review;
import model.Movie;

/**
 * Viewing interface for Reviews
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */

public class ReviewView extends MainView {
    /**
     * Path of entry for ReviewView
     */
    private String path;

    private Movie movie;

    /**
     * Default contructor for the CineplexAppView
     */
    public ReviewView(Movie movie, String path) {
        super();
        this.movie = movie;
        this.path = path;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > Review > " + this.movie.getTitle());
        System.out.println("What would you like to do ?");
        System.out.println("(1) View past reviews");
        System.out.println("(2) Make a review");
        System.out.println("(3) Exit");
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 3);
            switch (choice) {
                case 1:
                    System.out.println("Reviews:");
                    MovieManager.displayReviews(this.movie);
                    break;
                case 2:
                    System.out.println("Enter your rating (1 - 5 [best]):");
                    double rating = Helper.readDouble(1, 5);
                    System.out.println("Enter your review in words:");
                    String review = Helper.readString();
                    MovieManager.addReview(this.movie, rating, review);
                    break;
                case 3:
                    break;
                default:
                    break;
            }
            if (choice != 3) {
                Helper.pressAnyKeyToContinue();
            }
        } while (choice != 3);
    }

}
