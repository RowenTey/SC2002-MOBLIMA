package view;

import java.util.ArrayList;

import helper.Helper;
import controller.MovieManager;
import model.Review;

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

    private String movieName;

    /**
     * Default contructor for the CineplexAppView
     */
    public ReviewView(String movieName, String path) {
        super();
        this.movieName = movieName;
        this.path = path;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > " + this.movieName);
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
                    // TODO: MovieManager.getReviews();
                    ArrayList<Review> reviewsList = MovieManager.getReviews();
                    System.out.println("Reviews:");
                    for (int i=0; i<reviewsList.size(); i+=1){
                        System.out.println("\t" + Double.toString(reviewsList.get(i).getRating()) + ": " + reviewsList.get(i).getReview());
                    }
                    System.out.println();
                    break;
                case 2:
                    // TODO: MovieManager.addReview();
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
