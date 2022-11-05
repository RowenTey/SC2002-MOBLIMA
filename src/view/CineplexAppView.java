package src.view;

import src.controller.*;
import src.database.Database;
import src.helper.Helper;

/**
 * Viewing interface for MOBLIMA user
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-19
 */
public class CineplexAppView extends MainView {
    protected static StaffView staffView;
    protected static MovieGoerView movieGoerView;
    protected static DatabaseView databaseView;
    protected static MovieView movieView;
    protected static CineplexView cineplexView;
    protected static ShowtimeView showtimeView;
    protected static ReviewView reviewView;

    /**
     * Default contructor for the CineplexAppView
     */
    public CineplexAppView() {
        super();
        staffView = new StaffView();
        movieGoerView = new MovieGoerView();
        databaseView = new DatabaseView();
        movieView = new MovieView();
        cineplexView = new CineplexView();
        showtimeView = new ShowtimeView();
        reviewView = new ReviewView();
        new ShowtimeManager();
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute("Cineplex App");
        System.out.println("What would you like to do today?");
        System.out.println("(1) Login");
        System.out.println("(2) Continue as Guest");
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
                    this.handleLogin();
                    Database.path = "Cineplex App";
                    break;
                case 2:
                    Database.path = "Cineplex App > MovieGoer";
                    movieGoerView.viewApp();
                    Database.path = "Cineplex App";
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

    /**
     * Action function to handle user login direct to sign up
     * 
     * @return int {@code 2} if staff credentials was valid, {@code 1} if staff
     *         credentials was valid, {@code 0} if user
     *         was directed to signup, {@code -1}
     *         otherwise
     */
    private void handleLogin() {
        Helper.clearScreen();
        printRoute("Cineplex App > Login");

        System.out.println("Please enter your username: (Enter 'register' to sign up instead!)");
        String username = Helper.readString();
        if (username.equals("register")) {
            handleSignUp();
            return;
        }

        System.out.println("\nPlease enter your password: ");
        String password = Helper.readString();
        System.out.println();

        String key = username.substring(0, 1);
        if (key.equals("$")) {
            if (UserManager.validateUser(username, password)) {
                Database.isStaff = true;
                System.out.println(
                        "Login successfully! Welcome staff " + username.substring(1) + " to the MOBLIMA system.\n");
                Helper.pressAnyKeyToContinue();
                Database.path = "Cineplex App > Staff";
                staffView.viewApp();
            } else {
                System.out.println("Invalid user!");
                Helper.pressAnyKeyToContinue();
            }
        } else {
            if (UserManager.validateUser(username, password)) {
                Database.isStaff = false;
                Database.username = username;
                System.out.println("Login successfully! Welcome member " + username + " to the MOBLIMA system.\n");
                Helper.pressAnyKeyToContinue();
                Database.path = "Cineplex App > " + username;
                movieGoerView.viewApp();
            } else {
                System.out.println("Invalid user!");
                Helper.pressAnyKeyToContinue();
            }
        }

    }

    /**
     * Action function to handle user sign up
     * 
     * @return boolean {@code true} if user credentials was valid, {@code false}
     *         otherwise
     */
    private void handleSignUp() {
        Helper.clearScreen();
        printRoute("Cineplex App > Signup");
        UserManager.onSignUp();
        Helper.pressAnyKeyToContinue();
    }
}
