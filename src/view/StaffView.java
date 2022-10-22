package view;

import helper.Helper;

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
        System.out.println("(1) Login");
        System.out.println("(2) Manage movies");
        System.out.println("(3) Manage showtimes");
        System.out.println("(4) Configure system settings");
        System.out.println("(5) Exit");
    }

    public void viewApp() {
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 5);
            switch (choice) {
                case 1:
                    // StaffManager.login()

                    break;
                case 2:
                    // StaffManager.editMovieListings()
                    MovieView movieView = new MovieView(this.path + " > Staff");
                    movieView.viewApp();
                    break;
                case 3:
                    // StaffManager.editMovieShowtimes()
                    ShowtimeView showtimeView = new ShowtimeView(this.path + " > Staff");
                    showtimeView.viewApp();
                    break;
                case 4:
                    // StaffManager.editSystemSetting()

                    break;
                case 5:
                    break;

                default:
                    break;
            }
        } while (choice != 5);
    }

}
