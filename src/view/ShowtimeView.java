package view;

import java.util.ArrayList;

import controller.CineplexManager;
import controller.MovieManager;
import controller.ShowtimeManager;
import helper.Helper;
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
            System.out.println("(1) Create showtime");
            System.out.println("(2) List showtimes");
            System.out.println("(3) Exit");
        } else {
            System.out.println("(1) List current showtimes");
            System.out.println("(2) Exit");
        }
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        if (this.isStaff) {
            do {
                printMenu();
                choice = Helper.readInt(1, 3);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
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

        else {
            do {
                printMenu();
                choice = Helper.readInt(1, 2);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        printRoute(this.path + " > Showtime > Showtime Listing");
                        ShowtimeManager.printAllShowtime();
                        break;
                    default:
                        break;
                }
                if (choice != 2) {
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 2);
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

    private boolean handleCreateShowtime() {
        return ShowtimeManager.onCreateShowtime();
    }

    private void handleShowtimeSelection(Movie movie) {
        ArrayList<Showtime> movieShowtimes = ShowtimeManager.getMovieShowtime(movie);
        ShowtimeManager.displayShowtime(movieShowtimes);
        String showtimeId = ShowtimeManager.selectShowtime(movieShowtimes);
        ShowtimeManager.promptSeatSelection(showtimeId);
    }
}
