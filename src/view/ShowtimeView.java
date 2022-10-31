package src.view;

import java.util.ArrayList;

import src.controller.BookingManager;
import src.controller.ShowtimeManager;
import src.helper.Helper;
import src.model.Cineplex;
import src.model.Movie;
import src.model.Showtime;

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
     * Overrided contructor for the ShowtimeView
     */
    public ShowtimeView(String path, boolean isStaff) {
        super();
        this.path = path;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > Showtime");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Create Showtime");
        System.out.println("(2) Remove Showtime");
        System.out.println("(3) Update Showtime");
        System.out.println("(4) List Showtimes");
        System.out.println("(5) Exit");
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        do {
            printMenu();
            choice = Helper.readInt(1, 5);
            switch (choice) {
                case 1:
                    Helper.clearScreen();
                    printRoute(path + " > Create Showtime");
                    handleCreateShowtime();
                    Helper.pressAnyKeyToContinue();
                    break;
                case 2:
                    Helper.clearScreen();
                    printRoute(path + " > Remove Showtime");
                    if (ShowtimeManager.removeShowtime()) {
                        System.out.println("\nRemoved showtime successfully!");
                    } else {
                        System.out.println("\nFailed to remove showtime!");
                    }
                    Helper.pressAnyKeyToContinue();
                    break;
                case 3:
                    Helper.clearScreen();
                    printRoute(path + " > Update Showtime");
                    if (ShowtimeManager.updateShowtime()) {
                        System.out.println("Showtime successfully updated!");
                    } else {
                        System.out.println("\nFailed to update showtime!");
                    }
                    Helper.pressAnyKeyToContinue();
                    break;
                case 4:
                    Helper.clearScreen();
                    printRoute(this.path + " > Showtime > Showtime Listing");
                    ShowtimeManager.displayAllShowtime();
                    Helper.pressAnyKeyToContinue();
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } while (choice != 5);
    }

    /**
     * Overrided View App - from movie view (MovieGoer)
     */
    public void viewApp(String path, Movie movie) {
        Helper.clearScreen();
        printRoute(path + " > " + movie.getTitle());
        ShowtimeManager.handleShowtimeSelection(movie);
        return;
    }

    /**
     * Overrided View App - from cineplex view (MovieGoer)
     */
    public void viewApp(Cineplex cineplex) {
        Helper.clearScreen();
        printRoute(path + " > " + cineplex.getLocation());
        ShowtimeManager.handleShowtimeSelection(cineplex);
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

}
