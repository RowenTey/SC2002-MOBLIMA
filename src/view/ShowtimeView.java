package view;

import java.util.ArrayList;

import controller.CineplexManager;
import controller.MovieManager;
import controller.ShowtimeManager;
import helper.Helper;
import model.Cineplex;
import model.Movie;
import model.Showtime;

/**
 * Viewing interface for Showtime
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */
public class ShowtimeView extends MainView {
    /**
     * Path of entry for showtime view
     */
    public String path;

    /**
     * Current user is staff
     */
    public boolean isStaff;

    /**
     * Default contructor for the ShowtimeView
     */
    public ShowtimeView(String path, boolean isStaff) {
        super();
        this.path = path;
        this.isStaff = isStaff;
        new MovieManager();
        new ShowtimeManager();
        new CineplexManager();
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > Showtime");
        System.out.println("What would you like to do ?");
        if (this.isStaff) {
            System.out.println("(1) Create Showtime");
            System.out.println("(2) List Showtimes");
            System.out.println("(3) Exit");
        } else {
            System.out.println("(1) List \"NOW SHOWING\" Showtimes");
            System.out.println("(2) List \"PREVIEW\" Showtimes");
            System.out.println("(3) List \"COMING SOON\" Showtimes");
            System.out.println("(4) List \"END OF SHOWING\" Showtimes");
            System.out.println("(5) Exit");
        }
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;

        // staff
        if (this.isStaff) {
            do {
                printMenu();
                choice = Helper.readInt(1, 3);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        printRoute(path + " > Create Showtime");
                        if (handleCreateShowtime()) {
                            System.out.println("Showtime created successfully!");
                        } else {
                            System.out.println("Showtime created unsuccessfully!");
                        }
                        break;
                    case 2:
                        Helper.clearScreen();
                        printRoute(this.path + " > Showtime > Showtime Listing");
                        ShowtimeManager.printAllShowtime();
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

        // user
        else {
            do {
                printMenu();
                choice = Helper.readInt(1, 5);
                switch (choice) {
                    case 1:
                        // NOW_SHOWING
                        Helper.clearScreen();
                        printRoute(this.path + " > Showtime > Showtime Listing (NOW SHOWING)");
                        ShowtimeManager.printShowtimeBasedOnStatus(1);
                        break;
                    case 2:
                        // PREVIEW
                        Helper.clearScreen();
                        printRoute(this.path + " > Showtime > Showtime Listing (PREVIEW)");
                        ShowtimeManager.printShowtimeBasedOnStatus(2);
                        break;
                    case 3:
                        // COMING SOON
                        Helper.clearScreen();
                        printRoute(this.path + " > Showtime > Showtime Listing (COMING SOON)");
                        ShowtimeManager.printShowtimeBasedOnStatus(3);
                        break;
                    case 4:
                        // END OF SHOWING
                        Helper.clearScreen();
                        printRoute(this.path + " > Showtime > Showtime Listing (END OF SHOWING)");
                        ShowtimeManager.printShowtimeBasedOnStatus(4);
                        break;
                    case 5:
                        break;
                    default:
                        Helper.clearScreen();
                        printRoute(this.path + " > Showtime > Showtime Listing");
                        ShowtimeManager.printAllShowtime();
                        break;
                }
                if (choice != 5) {
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 5);
        }

    }

    /**
     * Overrided View App - from movie view (user)
     */
    public void viewApp(Movie movie) {
        Helper.clearScreen();
        printRoute(path + " > " + movie.getTitle());
        handleShowtimeSelection(movie);
        return;
    }

    /**
     * Overrided View App - from cineplex view (user)
     */
    public void viewApp(Cineplex cineplex) {
        Helper.clearScreen();
        printRoute(path + " > " + cineplex.getLocation());
        handleShowtimeSelection(cineplex);
        return;
    }

    /**
     * action function to handle creating a showtime
     */
    private boolean handleCreateShowtime() {
        return ShowtimeManager.onCreateShowtime();
    }

    /**
     * action function to handle showtime selection - from movie view
     */
    private void handleShowtimeSelection(Movie movie) {
        ArrayList<Showtime> movieShowtimes = ShowtimeManager.getMovieShowtime(movie);
        ShowtimeManager.displayShowtime(movieShowtimes, "movie");
        String showtimeId = ShowtimeManager.selectShowtime(movieShowtimes);
        ShowtimeManager.promptSeatSelection(showtimeId);
    }

    /**
     * action function to handle showtime selection - from cineplex view
     */
    private void handleShowtimeSelection(Cineplex cineplex) {
        ArrayList<Showtime> movieShowtimes = ShowtimeManager.getShowtimeByCineplex(cineplex);
        if (movieShowtimes.size() == 0) {
            System.out.println("No showtimes available for this cineplex...");
            Helper.pressAnyKeyToContinue();
        } else {
            ShowtimeManager.displayShowtime(movieShowtimes, "cineplex");
        }
        String showtimeId = ShowtimeManager.selectShowtime(movieShowtimes);
        ShowtimeManager.promptSeatSelection(showtimeId);
    }
}
