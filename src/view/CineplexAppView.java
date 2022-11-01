package src.view;

import src.controller.*;
import src.helper.Helper;

/**
 * Viewing interface for MOBLIMA user
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-19
 */
public class CineplexAppView extends MainView {
    /**
     * Default contructor for the CineplexAppView
     */
    public CineplexAppView() {
        super();
        new MovieManager();
        new ShowtimeManager();
        new CineplexManager();
        new BookingManager();
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute("Cineplex App");
        System.out.println("Are you an Admin or a MovieGoer?");
        System.out.println("(1) Admin");
        System.out.println("(2) MovieGoer");
        System.out.println("(3) Terminate Program");
        System.out.println("(4) Reset Database");
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        do {
            printMenu();
            choice = Helper.readInt(1, 4);
            switch (choice) {
                case 1:
                    StaffView staffView = new StaffView("Cineplex App");
                    staffView.viewApp();
                    break;
                case 2:
                    MovieGoerView movieGoerView = new MovieGoerView("Cineplex App");
                    movieGoerView.viewApp();
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println();
                    printRoute("Cineplex App > Staff > Database > Reset Database");
                    if (DatabaseView.resetDatabase()) {
                        System.out.println("Database cleared");
                    }
                    break;
                default:
                    break;
            }
        } while (choice != 3);
    }

}
