package view;

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
     * Default contructor for the CineplexAppView
     */
    public MovieGoerView() {
        super();
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute("... > MovieGoer View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Search or list Cineplexes");
        System.out.println("(2) Search or list Showtimes");
        System.out.println("(3) Search or list Movies");
        System.out.println("(4) View booking history");
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
                    CineplexView cineplexView = new CineplexView();
                    cineplexView.viewApp();
                    break;
                case 2:
                    ShowtimeView showtimeView = new ShowtimeView();
                    showtimeView.viewApp();
                    break;
                case 3:
                    //TODO (MoviesView)
                    break;
                case 4:
                    System.out.println("View Booking History");
                    // TODO (Booking View)
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } while (choice != 5);
    }
}
