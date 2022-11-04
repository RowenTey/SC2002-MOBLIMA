package src.view;

import src.controller.BookingManager;
import src.controller.SystemManager;
import src.helper.Helper;

/**
 * MovieGoerView provides the view to access movie goer actions.
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
     * Name of current user
     */
    private String username;

    /**
     * Overrided contructor for the MovieGoerView
     * 
     * @param path     of entry for MovieGoerView
     * @param username of current user
     */
    public MovieGoerView(String path, String username) {
        super();
        this.path = path;
        this.username = username;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > " + (this.username.equals("") ? "MovieGoer" : this.username));
        System.out.println("What would you like to do ?");
        System.out.println("(1) Search or List Cineplexes");
        System.out.println("(2) Search or List Movies");
        System.out.println("(3) List Ticket Prices");
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
                    CineplexView cineplexView = new CineplexView(
                            this.path + " > " + (this.username.equals("") ? "MovieGoer" : this.username), false,
                            this.username);
                    cineplexView.viewApp();
                    continue;
                case 2:
                    MovieView movieView = new MovieView(
                            this.path + " > " + (this.username.equals("") ? "MovieGoer" : this.username), false,
                            this.username);
                    movieView.viewApp();
                    continue;
                case 3:
                    Helper.clearScreen();
                    printRoute(this.path + " > " + (this.username.equals("") ? "MovieGoer" : this.username)
                            + " > View Ticket Prices");
                    SystemManager.displayTicketPrices();
                    break;
                case 4:
                    Helper.clearScreen();
                    printRoute(this.path + " > " + (this.username.equals("") ? "MovieGoer" : this.username)
                            + " > View Booking History");
                    handleCheckBooking(this.username);
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

    /**
     * Handles {@link Booking} checking
     * 
     * @param username of current user
     */
    private void handleCheckBooking(String username) {
        String email;
        if (username.equals("")) {
            System.out.print("Enter your email: ");
            email = Helper.readString();
        } else {
            email = BookingManager.getEmailByUsername(username);
        }
        BookingManager.findBooking(email);
    }
}
