package src.view;

import src.controller.StaffManager;
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
        if (!this.handleLogin()) {
            return;
        }
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 5);
            switch (choice) {
                case 1:
                    CineplexView cineplexView = new CineplexView(this.path + " > Staff", true);
                    cineplexView.viewApp();
                    continue;
                case 2:
                    MovieView movieView = new MovieView(this.path + " > Staff", true);
                    movieView.viewApp();
                    continue;
                case 3:
                    ShowtimeView showtimeView = new ShowtimeView(this.path + " > Staff");
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

    /**
     * Action function to handle staff login
     * 
     * @return boolean {@code true} if staff credentials was valid, {@code false}
     *         otherwise
     */
    private boolean handleLogin() {
        Helper.clearScreen();
        printRoute(this.path + " > Staff Login");
        System.out.println("Please enter your staff username");
        String username = Helper.readString();
        System.out.println();
        System.out.println("Please enter your staff password");
        String password = Helper.readString();
        System.out.println();
        if (StaffManager.validateStaff(username, password)) {
            System.out.println("Login successfully! Welcome " + username + " to the MOBLIMA system.\n");
            Helper.pressAnyKeyToContinue();
            return true;
        } else {
            System.out.println("Invalid staff!");
            Helper.pressAnyKeyToContinue();
            return false;
        }
    }

}
