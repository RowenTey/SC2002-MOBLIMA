package view;

import helper.Helper;

/**
 * Viewing interface for Showtime
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */

public class ShowtimeView extends MainView {
    /**
     * Default contructor for the CineplexAppView
     */
    public ShowtimeView() {
        super();
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute("... > Showtime View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Showtime Listing");
        System.out.println("(2) Upcoming showtimes");
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
                    // TODO (ShowtimeManager.getCurrentList())
                    break;
                case 2:
                    // TODO (ShowtimeManager.getUpcomingList())
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        } while (choice != 3);
    }
}
