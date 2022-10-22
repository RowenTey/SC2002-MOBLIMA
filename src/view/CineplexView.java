package view;


import controller.CineplexManager;
import helper.Helper;

/**
 * Viewing interface for Cineplex
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-19
 */

public class CineplexView extends MainView {
    /**
     * Path of entry for showtime view
     */
    private String path;

    /**
     * Default contructor for the CineplexAppView
     */
    public CineplexView() {
        super();
    }

    public CineplexView(String path) {
        super();
        this.path = path;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > Cineplex");
        System.out.println(CineplexManager.getCineplexList());
        System.out.println("We have 3 Cineplexes in Singapore");
        System.out.println("(1) JEM");
        System.out.println("(2) Causeway Point");
        System.out.println("(3) AMK Hub");
        System.out.println("(4) Exit");
        System.out.println("(5) Add Cineplex");
        System.out.println("(6) View Cineplex");
        System.out.println("Which location would you like to choose? ");
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 6);
            switch (choice) {
                case 1:
                    System.out.println("JEM selected...");
                    // TODO (showtimeView())
                    break;
                case 2:
                    System.out.println("Causeway Point selected...");
                    // TODO (showtimeView())
                    break;
                case 3:
                    System.out.println("AMK Hub selected...");
                    // TODO (showtimeView())
                    break;
                case 4:
                    break;
                case 5:
                    CineplexManager.addCineplex();
                    break;
                default:
                    break;
            }
        } while (choice != 4);
    }

}
