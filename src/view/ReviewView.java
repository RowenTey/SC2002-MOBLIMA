package view;

import helper.Helper;

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
        System.out.println("(1) See past reviews of this movie");
        System.out.println("(2) Leave a review");
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
