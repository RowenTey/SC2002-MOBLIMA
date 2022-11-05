package src.view;

import src.controller.BookingManager;
import src.controller.SystemManager;
import src.database.Database;
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
     * Default contructor for the MovieGoerView
     */
    public MovieGoerView() {
        super();
    }

    /**
     * View Menu of MovieGoerView
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(Database.path);
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
        String toRestore = Database.path;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 5);
            switch (choice) {
                case 1:
                    Database.path = Database.path + " > Cineplex";
                    CineplexAppView.cineplexView.viewApp();
                    Database.path = toRestore;
                    continue;
                case 2:
                    Database.path = Database.path + " > Movie";
                    CineplexAppView.movieView.viewApp();
                    Database.path = toRestore;
                    continue;
                case 3:
                    Helper.clearScreen();
                    printRoute(Database.path + " > View Ticket Prices");
                    SystemManager.displayTicketPrices();
                    break;
                case 4:
                    Helper.clearScreen();
                    printRoute(Database.path + " > View Booking History");
                    handleCheckBooking(Database.username);
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
