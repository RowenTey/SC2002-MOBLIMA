package view;

import helper.Helper;

/**
 * Viewing interface for Cineplex
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-19
 */
public class CineplexAppView extends MainView {
    /**
     * Default contructor for the CineplexAppView
     */
    public CineplexAppView() {
        super();
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute("Cineplex App View");
        System.out.println("Are you admin or MovieGoer ?");
        System.out.println("(1) Admin");
        System.out.println("(2) MovieGoer");
        System.out.println("(0) Terminate Program");
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        do {
            printMenu();
            choice = Helper.readInt(0, 2);
            switch (choice) {
                case 2:
                    MovieGoerView movieGoerView = new MovieGoerView();
                    movieGoerView.viewApp();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        } while (choice != 0);
    }

}
