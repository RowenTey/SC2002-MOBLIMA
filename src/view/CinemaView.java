package view;

import helper.Helper;

/**
 * Viewing interface for Cinema
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */
public class CinemaView extends MainView {
    /**
     * Default contructor for the CineplexAppView
     */
    public CinemaView() {
        super();
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute("... > Cinema View");
        System.out.println("What would you like to check ?");
        System.out.println("(1) Is this a platinum Cinema");
        System.out.println("(2) What type of movies does this cinema show ?");
        System.out.println("(3) Where is this cinema ?");
        System.out.println("(4) Exit");
    };

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 4);
            switch (choice) {
                case 1:
                    System.out.println("This is a platinum cinema");
                    // TODO (CinemaManager.isPlatinum(()))
                    break;
                case 2:
                    System.out.println("This cinema only shows 3D movies");
                    // TODO (CinemaManager.is3D())
                    break;
                case 3:
                    System.out.println("This cinema is located at JP");
                    // TODO (CinemaManager.getLocation()?)
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        } while (choice != 4);
    };

}
