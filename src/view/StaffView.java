package view;

import java.util.ArrayList;

import controller.CineplexManager;
import controller.StaffManager;
import helper.Helper;
import model.Cineplex;

/**
 * Viewing interface for Showtime
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-20
 */
public class StaffView extends MainView {
    /**
     * Path of entry for showtime view
     */
    public String path;

    /**
     * Default contructor for the StaffView
     */
    public StaffView() {
        super();
    }

    /**
     * Overrided contructor for the StaffView
     */
    public StaffView(String path) {
        super();
        this.path = path;
    }

    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > Staff");
        System.out.println("What would you like to do?");
        System.out.println("(1) Manage movies");
        System.out.println("(2) Manage showtimes");
        System.out.println("(3) Configure system settings");
        System.out.println("(4) Add Cineplex");
        System.out.println("(5) Remove Cineplex");
        System.out.println("(6) Exit");
    }

    public void viewApp() {
        if (!this.printLoginUI()) {
            return;
        }
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 8);
            switch (choice) {
                case 1:
                    // StaffManager.editMovieListings()
                    MovieView movieView = new MovieView(this.path + " > Staff", true);
                    movieView.viewApp();
                    break;
                case 2:
                    // StaffManager.editMovieShowtimes()
                    ShowtimeView showtimeView = new ShowtimeView(this.path + " > Staff", true);
                    showtimeView.viewApp();
                    break;
                case 3:
                    DatabaseView databaseView = new DatabaseView();
                    databaseView.viewApp();
                    break;
                case 4:
                    Helper.clearScreen();
                    printRoute(this.path + " > Staff > Add New Cineplex");
                    ArrayList<Cineplex> cineplex = CineplexManager.getCineplexList();
                    int total = CineplexManager.getTotalNumOfCineplex();
                    if (total != 0) {
                        System.out.println("Existing Cineplexes in Singapore");
                        for (int i = 0; i < total; i++) {
                            System.out.println("(" + (i + 1) + ") " + cineplex.get(i).getLocation());
                        }
                    }
                    CineplexManager.addCineplex();
                    Helper.pressAnyKeyToContinue();
                    break;
                case 5:
                    Helper.clearScreen();
                    printRoute(this.path + " > Staff > Remove Cineplex");
                    CineplexManager.removeCineplex();
                    Helper.pressAnyKeyToContinue();
                    break;
                case 6:
                    break;
                default:
                    break;
            }
        } while (choice != 6);
    }

    public boolean printLoginUI() {
        Helper.clearScreen();
        printRoute(this.path + " > Staff Login");
        System.out.println("Please enter your staff username");
        String username = Helper.readString();
        System.out.println();
        System.out.println("Please enter your staff password");
        String password = Helper.readString();
        System.out.println();
        if (StaffManager.validateStaff(username, password)) {
            System.out.println("Login successfully!");
            Helper.pressAnyKeyToContinue();
            return true;
        } else {
            System.out.println("Invalid staff!");
            Helper.pressAnyKeyToContinue();
            return false;
        }
    }

}
