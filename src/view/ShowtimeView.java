package view;

import java.util.Date;

import controller.ShowtimeManager;
import helper.Helper;
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
     * Current user is staff
     */
    public boolean isStaff;

    /**
     * Default contructor for the ShowtimeView
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
        printRoute(this.path + " > Showtime View");
        System.out.println("What would you like to do ?");
        if (this.isStaff) {
            System.out.println("(1) Create showtime");
            System.out.println("(2) List showtimes");
            System.out.println("(3) Exit");
        } else {
            System.out.println("(1) List current showtimes");
            System.out.println("(2) List upcoming showtimes");
            System.out.println("(3) Exit");
        }
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
                    // TODO (ShowtimeManager.getCurrentList())

                    // Which movie would you like to create a showtime for?
                    // get movie list

                    // Enter the showtime for this movie
                    // date + time

                    // Enter the cinema for this movie
                    // get cinema list

                    // Date date = (Date) Helper.setDate(false)
                    Date date = new Date();
                    // ShowtimeManager.createShowtime(date, new Movie("One Piece FILM RED",
                    // ShowStatus.NOW_SHOWING),
                    // "AM1");
                    // ShowtimeManager.createShowtime(new Date(), new Movie("Black Adam",
                    // ShowStatus.NOW_SHOWING),
                    // "JE2");
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
                System.out.println();
                Helper.pressAnyKeyToContinue();
            }
        } while (choice != 3);
    }
}
