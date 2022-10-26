package view;

import java.util.ArrayList;

import controller.MovieManager;
import helper.Helper;
import model.Movie;
import model.enums.ShowStatus;
import model.enums.TypeMovies;

/**
 * Viewing interface for Cineplex
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */

public class MovieView extends MainView {
    /**
     * path
     */
    private String path;

    /**
     * Current user is staff
     */
    private boolean isStaff;

    /**
     * Default contructor for the MovieAppView
     */
    public MovieView() {
        super();
    }

    /**
     * Default contructor for the MovieAppView
     */
    public MovieView(String path, boolean isStaff) {
        super();
        this.path = path;
        this.isStaff = isStaff;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > Movie");
        System.out.println("What would you like to do ?");
        if (this.isStaff) {
            System.out.println("(1) Add Movie");
            System.out.println("(2) Update Movie");
            System.out.println("(3) Remove Movie");
            System.out.println("(4) List Top 5 Movies by Ticket Sales");
            System.out.println("(5) List Top 5 Movies by Overall Rating");
            System.out.println("(6) Exit");
        } else {
            System.out.println("(1) Book Movie");
            System.out.println("(2) View Past Movie Reviews");
            System.out.println("(3) List Top 5 Movies by Ticket Sales");
            System.out.println("(4) List Top 5 Movies by Overall Rating");
            System.out.println("(5) Exit");
        }
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice;
        if (this.isStaff) {
            do {
                this.printMenu();
                choice = Helper.readInt(1, 6);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Add Movie");
                        MovieManager.handleAddMovie();
                        break;
                    case 2:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Update Movie");
                        MovieManager.updateMovie();
                        break;
                    case 3:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Remove Movie");
                        MovieManager.removeMovie();
                        break;
                    case 4:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Ticket Sales");
                        MovieManager.printTop5ByTicketSales();
                        break;
                    case 5:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Overall Rating");
                        MovieManager.printTop5ByOverallRating();
                        break;
                    default:
                        break;
                }
                if (choice != 6) {
                    System.out.println();
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 6);
        }

        else {
            do {
                this.printMenu();
                choice = Helper.readInt(1, 5);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Book Movie");
                        MovieManager.handleBookMovie(this.path + " > Movie > Book Movie");
                        break;
                    case 2:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Movie Reviews");
                        MovieManager.displayExistingMovies();
                        MovieManager.handleViewPastMovieReviews(this.path + " > Movie");
                        break;
                    case 3:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Ticket Sales");
                        MovieManager.printTop5ByTicketSales();
                        break;
                    case 4:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Overall Rating");
                        MovieManager.printTop5ByOverallRating();
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
                if (choice != 5) {
                    System.out.println();
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 5);
        }
    }

}
