package src.view;

import src.controller.MovieManager;
import src.helper.Helper;
import src.model.enums.ShowStatus;

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
            System.out.println("(4) List \"NOW SHOWING\" Movies");
            System.out.println("(5) List \"PREVIEW\" Movies");
            System.out.println("(6) List \"COMING SOON\" Movies");
            System.out.println("(7) List Top 5 Movies by Ticket Sales");
            System.out.println("(8) List Top 5 Movies by Overall Rating");
            System.out.println("(9) Exit");
        } else {
            System.out.println("(1) Book Movie");
            System.out.println("(2) View Past Movie Reviews");
            System.out.println("(3) List \"NOW SHOWING\" Movies");
            System.out.println("(4) List \"PREVIEW\" Movies");
            System.out.println("(5) List \"COMING SOON\" Movies");
            System.out.println("(6) List Top 5 Movies by Ticket Sales");
            System.out.println("(7) List Top 5 Movies by Overall Rating");
            System.out.println("(8) Exit");
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
                choice = Helper.readInt(1, 9);
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
                        printRoute(this.path + " > Movie > NOW SHOWING");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.NOW_SHOWING);
                        break;
                    case 5:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > PREVIEW");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.PREVIEW);
                        break;
                    case 6:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > COMING SOON");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.COMING_SOON);
                        break;
                    case 7:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Ticket Sales");
                        MovieManager.printTop5ByTicketSales();
                        break;
                    case 8:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Overall Rating");
                        MovieManager.printTop5ByOverallRating();
                        break;
                    case 9:
                        break;
                    default:
                        break;
                }
                if (choice != 9) {
                    System.out.println();
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 9);
        }

        else {
            do {
                this.printMenu();
                choice = Helper.readInt(1, 8);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Book Movie");
                        handleBookMovie(this.path + " > Movie > Book Movie");
                        break;
                    case 2:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Movie Reviews");
                        MovieManager.displayExistingMovies();
                        MovieManager.handleViewPastMovieReviews(this.path + " > Movie");
                        break;
                    case 3:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > NOW SHOWING");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.NOW_SHOWING);
                        break;
                    case 4:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > PREVIEW");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.PREVIEW);
                        break;
                    case 5:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > COMING SOON");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.COMING_SOON);
                        break;
                    case 6:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Ticket Sales");
                        MovieManager.printTop5ByTicketSales();
                        break;
                    case 7:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Overall Rating");
                        MovieManager.printTop5ByOverallRating();
                        break;
                    case 8:
                        break;
                    default:
                        break;
                }
                if (choice != 8) {
                    System.out.println();
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 8);
        }
    }

    /**
     * @param path
     */
    private static void handleBookMovie(String path) {
        System.out.println("Which movie would you like to book?\n");
        if (MovieManager.displayListOfBookableMovies()) {
            ShowtimeView showtimeView = new ShowtimeView(path);
            showtimeView.viewApp(MovieManager.selectMovie());
        }
    }

}
