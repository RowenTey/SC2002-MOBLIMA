package src.view;

import src.controller.UserManager;
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
     * Path of entry for StaffView
     */
    public String path;

    /**
     * Overrided contructor for the StaffView
     * 
     * @param path of entry for StaffView
     */
    public StaffView(String path) {
        super();
        this.path = path;
    }

    /**
     * View menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > Staff");
        System.out.println("What would you like to do?");
        System.out.println("(1) Manage Cineplex");
        System.out.println("(2) Manage Movies");
        System.out.println("(3) Manage Showtimes");
        System.out.println("(4) Configure System Settings");
        System.out.println("(5) Exit");
    }

    /**
     * View app
     */
    public void viewApp() {
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 5);
            switch (choice) {
                case 1:
                    CineplexView cineplexView = new CineplexView(this.path + " > Staff", true, "");
                    cineplexView.viewApp();
                    continue;
                case 2:
                    MovieView movieView = new MovieView(this.path + " > Staff", true, "");
                    movieView.viewApp();
                    continue;
                case 3:
                    ShowtimeView showtimeView = new ShowtimeView(this.path + " > Staff", "");
                    showtimeView.viewApp();
                    continue;
                case 4:
                    DatabaseView databaseView = new DatabaseView();
                    databaseView.viewApp();
                    continue;
                case 5:
                    break;
                default:
                    break;
            }
        } while (choice != 5);
    }
}
