package src.view;

import src.database.Database;
import src.helper.Helper;

/**
 * StaffView provides the view to access staff actions
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-20
 */
public class StaffView extends MainView {
    /**
     * Default contructor for the StaffView
     */
    public StaffView() {
        super();
    }

    /**
     * View menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(Database.path);
        System.out.println("What would you like to do?");
        System.out.println("(1) Manage Cineplex");
        System.out.println("(2) Manage Movies");
        System.out.println("(3) Manage Showtimes");
        System.out.println("(4) Configure Database & System Settings");
        System.out.println("(5) Exit");
    }

    /**
     * View app
     */
    public void viewApp() {
        int choice = -1;
        String toRestore = Database.path;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 5);
            switch (choice) {
                case 1:
                    Database.path = Database.path + " > Manage Cineplex";
                    CineplexAppView.cineplexView.viewApp();
                    Database.path = toRestore;
                    continue;
                case 2:
                    Database.path = Database.path + " > Manage Movies";
                    CineplexAppView.movieView.viewApp();
                    Database.path = toRestore;
                    continue;
                case 3:
                    Database.path = Database.path + " > Manage Showtimes";
                    CineplexAppView.showtimeView.viewApp();
                    Database.path = toRestore;
                    continue;
                case 4:
                    CineplexAppView.databaseView.viewApp();
                    continue;
                case 5:
                    break;
                default:
                    break;
            }
        } while (choice != 5);
    }
}
