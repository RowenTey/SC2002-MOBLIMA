package view;

import helper.Helper;

/**
 * Viewing interface for Cineplex
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */

public class MovieView extends MainView {
    /**
     * Path of entry for showtime view
     */
    public String path;

    /**
     * Default contructor for the MovieView
     */
    public MovieView() {
        super();
    }

    /**
     * Overrided contructor for the MovieView
     */
    public MovieView(String path) {
        super();
        this.path = path;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute("... > Movies View");
        System.out.println("List of movies (both \"NOW SHOWING\" and \"COMING SOON\")");
        // TODO: use for loop to list down the movies
        System.out.println("(1) One Piece Film Red");
        System.out.println("(2) Black Adam");
        System.out.println("(3) Fall");
        System.out.println("(4) Thor: Love and Thunder");
        System.out.println("(5) Exit");
        System.out.println("Which movie do you want to choose?");
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 5);
            switch (choice) {
                case 1:
                    System.out.println("One Piece Film Red");
                    // TODO: MovieManager.showDetails() -> Synopsis, director, cast, overall rating,
                    // showtimes
                    // TODO: Prompt for BookView() or ReviewView()
                    ReviewView reviewView = new ReviewView("One Piece Film Red");
                    reviewView.viewApp();
                    break;
                case 2:
                    System.out.println("Black Adam");
                    // TODO: MovieManager.showDetails() -> Synopsis, director, cast, overall rating,
                    // showtimes
                    break;
                case 3:
                    System.out.println("Fall");
                    // TODO: MovieManager.showDetails() -> Synopsis, director, cast, overall rating,
                    // showtimes
                    break;
                case 4:
                    System.out.println("Thor: Love and Thunder");
                    // TODO: MovieManager.showDetails() -> Synopsis, director, cast, overall rating,
                    // showtimes
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } while (choice != 5);
    }

}
