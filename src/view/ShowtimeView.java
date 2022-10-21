package view;

import java.util.Date;

import controller.ShowtimeManager;
import helper.Helper;
import model.Cinema;
import model.Movie;
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
     * Default contructor for the CineplexAppView
     */
    public ShowtimeView() {
        super();
    }

    /**
     * Default contructor for the CineplexAppView
     */
    public ShowtimeView(String path) {
        super();
        this.path = path;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute("... > Showtime View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) List current showtimes");
        System.out.println("(2) List upcoming showtimes");
        System.out.println("(3) Exit");
    }

    /**
     * View Menu for {@code Staff}
     */
    public void printMenuStaff() {
        Helper.clearScreen();
        printRoute("... > Showtime View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Create showtime");
        System.out.println("(2) List showtimes");
        System.out.println("(3) Exit");
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        do {
            this.printMenuStaff();
            choice = Helper.readInt(1, 3);
            switch (choice) {
                case 1:
                    // TODO (ShowtimeManager.getCurrentList())
                    ShowtimeManager.createShowtime(new Date(), new Movie("One Piece FILM RED", ShowStatus.NOW_SHOWING),
                            new Cinema(null, null, false, false));
                    ShowtimeManager.createShowtime(new Date(), new Movie("Black Adam", ShowStatus.NOW_SHOWING),
                            new Cinema(null, null, false, false));
                    break;
                case 2:
                    // TODO (ShowtimeManager.getUpcomingList())
                    ShowtimeManager.printShowtime();
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
