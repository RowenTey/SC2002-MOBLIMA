package view;

import java.util.ArrayList;

import controller.CineplexManager;
import controller.MovieManager;
import controller.ShowtimeManager;
import helper.Helper;
import model.Cineplex;
import model.Movie;
import model.Showtime;
import model.enums.ShowStatus;

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
     * Default contructor for the MovieAppView
     */
    public ShowtimeView() {
        super();
    }

    /**
     * Overrided contructor for the ShowtimeView
     */
    public ShowtimeView(String path, boolean isStaff) {
        super();
        this.path = path;
        this.isStaff = isStaff;
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
            System.out.println("(1) List \"NOW SHOWING\" Movies");
            System.out.println("(2) List \"PREVIEW\" Movies");
            System.out.println("(3) List \"COMING SOON\" Movies");
            System.out.println("(4) Exit");
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
                        handleCreateShowtime();
                        Helper.pressAnyKeyToContinue();
                        break;
                    case 2:
                        Helper.clearScreen();
                        printRoute(this.path + " > Showtime > Showtime Listing");
                        ShowtimeManager.printAllShowtime();
                        Helper.pressAnyKeyToContinue();
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            } while (choice != 3);
        }

        // user
        else {
            do {
                printMenu();
                choice = Helper.readInt(1, 4);
                switch (choice) {
                    case 1:
                        // NOW_SHOWING
                        Helper.clearScreen();
                        printRoute(this.path + " > Showtime > Movies Listing (NOW SHOWING)");
                        ShowtimeManager.printShowtimeBasedOnStatus(ShowStatus.NOW_SHOWING);
                        break;
                    case 2:
                        // PREVIEW
                        Helper.clearScreen();
                        printRoute(this.path + " > Showtime > Movies Listing (PREVIEW)");
                        ShowtimeManager.printShowtimeBasedOnStatus(ShowStatus.PREVIEW);
                        break;
                    case 3:
                        // COMING SOON
                        Helper.clearScreen();
                        printRoute(this.path + " > Showtime > Movies Listing (COMING SOON)");
                        ShowtimeManager.printShowtimeBasedOnStatus(ShowStatus.COMING_SOON);
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }
                if (choice != 4) {
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 4);
        }

    }

    /**
     * Overrided View App - from movie view (user)
     */
    public void viewApp(String path, Movie movie) {
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
    private void handleCreateShowtime() {
        if (ShowtimeManager.onCreateShowtime()) {
            System.out.println("\nShowtime created successfully!");
        } else {
            System.out.println("\nShowtime created unsuccessfully!");
        }
    }

    /**
     * action function to handle showtime selection - from movie view
     */
    private void handleShowtimeSelection(Movie movie) {
        ArrayList<Showtime> movieShowtimes = ShowtimeManager.getMovieShowtime(movie);
        ShowtimeManager.displayShowtime(movieShowtimes, "movie");
        String showtimeId = ShowtimeManager.selectShowtime(movieShowtimes);
        ShowtimeManager.promptBooking(showtimeId);
    }

    /**
     * action function to handle showtime selection - from cineplex view
     */
    private void handleShowtimeSelection(Cineplex cineplex) {
        ArrayList<Showtime> movieShowtimes = ShowtimeManager.getShowtimeByCineplex(cineplex);
        if (movieShowtimes.size() == 0 || movieShowtimes == null) {
            System.out.println("No showtimes available for this cineplex...");
            Helper.pressAnyKeyToContinue();
        } else {
            ShowtimeManager.displayShowtime(movieShowtimes, "cineplex");
        }
        String showtimeId = ShowtimeManager.selectShowtime(movieShowtimes);
        ShowtimeManager.promptBooking(showtimeId);
    }
}
