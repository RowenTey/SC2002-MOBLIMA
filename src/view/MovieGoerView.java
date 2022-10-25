package view;

import controller.BookingManager;
import helper.Helper;

/**
 * Viewing interface for MovieGoer
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */

public class MovieGoerView extends MainView {
    /**
     * Path of entry for showtime view
     */
    private String path;

    /**
     * Default contructor for the CineplexAppView
     */
    public MovieGoerView() {
        super();
    }

    /**
     * Default contructor for the CineplexAppView
     */
    public MovieGoerView(String path) {
        super();
        this.path = path;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > MovieGoer");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Search or List Cineplexes");
        System.out.println("(2) Search or List Showtimes");
        System.out.println("(3) Search or List Movies");
        System.out.println("(4) View Booking History");
        System.out.println("(5) Exit");
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 5);
            switch (choice) {
                case 1:
                    CineplexView cineplexView = new CineplexView(this.path + " > MovieGoer", false);
                    cineplexView.viewApp();
                    continue;
                case 2:
                    ShowtimeView showtimeView = new ShowtimeView(this.path + " > MovieGoer", false);
                    showtimeView.viewApp();
                    break;
                case 3:
                    MovieView movieView = new MovieView(this.path + " > MovieGoer", false);
                    movieView.viewApp();
                    break;
                case 4:
                    System.out.println("View Booking History");
                    Helper.clearScreen();
                    printRoute(this.path + " > MovieGoer > View Booking History");
                    String transactionId = BookingManager.promptTransactionId();
                    BookingManager.findBooking(transactionId);
                    break;
                case 5:
                    break;
                default:
                    break;
            }
            if (choice != 5) {
                System.out.println();
                Helper.pressAnyKeyToContinue();
            }
        } while (choice != 5);
    }
}
