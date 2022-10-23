package view;

import java.util.ArrayList;

import controller.CineplexManager;
import helper.Helper;
import model.Cineplex;
import model.enums.Location;

/**
 * Viewing interface for Cineplex
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-22
 */
public class CineplexView extends MainView {
    /**
     * Path of entry for cineplex view
     */
    private String path;

    /**
     * Current user is staff
     */
    private boolean isStaff;

    /**
     * Default contructor for the CineplexView
     */
    public CineplexView() {
        super();
    }

    public CineplexView(String path, boolean isStaff) {
        super();
        this.path = path;
        this.isStaff = isStaff;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        if (!this.isStaff) {
            Helper.clearScreen();
            printRoute(this.path + " > Cineplex");
            CineplexManager.displayExistingCineplex();
            System.out.println("Which location would you like to choose? ");
        } else {
            Helper.clearScreen();
            printRoute(this.path + " > Cineplex");
            System.out.println("Which would you like to do ?");
            System.out.println("(1) Add Cineplex");
            System.out.println("(2) Remove Cineplex");
            System.out.println("(3) Exit");
        }
    }

    /**
     * View App
     */
    public void viewApp() {
        ArrayList<Cineplex> cineplex = CineplexManager.getCineplexList();
        int numOfCineplex = CineplexManager.getTotalNumOfCineplex();
        int choice = -1;
        if (this.isStaff) {
            do {
                this.printMenu();
                numOfCineplex = CineplexManager.getTotalNumOfCineplex();
                choice = Helper.readInt(1, 3);
                if (choice == 1) {
                    Helper.clearScreen();
                    printRoute(this.path + " > Cineplex > Add New Cineplex");
                    CineplexManager.addCineplex();
                } else if (choice == 2) {
                    Helper.clearScreen();
                    printRoute(this.path + " > Cineplex > Remove Cineplex");
                    CineplexManager.removeCineplex();
                } else {
                    break;
                }
                Helper.pressAnyKeyToContinue();
            } while (choice != 3);
            Helper.pressAnyKeyToContinue();
        }

        else {
            do {
                this.printMenu();
                choice = Helper.readInt(1, numOfCineplex + 1);
                if (choice == numOfCineplex + 1) {
                    break;
                } else {
                    System.out.println(cineplex.get(choice - 1).getLocation() + " selected");
                }
                if (choice != (numOfCineplex + 1)) {
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != (numOfCineplex + 1));
            Helper.pressAnyKeyToContinue();
        }
    }
}
