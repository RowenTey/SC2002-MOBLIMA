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
            ArrayList<Cineplex> cineplex = CineplexManager.getCineplexList();
            int total = CineplexManager.getTotalNumOfCineplex();
            System.out.println("We have " + total + " Cineplexes in Singapore");
            for (int i = 0; i < total; i++) {
                System.out.println("(" + (i + 1) + ") " + cineplex.get(i).getLocation());
            }
            System.out.println("(" + (total + 1) + ") Exit");
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
        int opt = -1;
        if (this.isStaff) {
            do {
                this.printMenu();
                choice = Helper.readInt(1, 3);
                if (choice == 1) {
                    Helper.clearScreen();
                    printRoute(this.path + " > Cineplex > Add New Cineplex");
                    if (numOfCineplex != 0) {
                        System.out.println("Existing Cineplexes in Singapore");
                        for (int i = 0; i < numOfCineplex; i++) {
                            System.out.println("(" + (i + 1) + ") " + cineplex.get(i).getLocation());
                        }
                        System.out.println();
                    }
                    System.out.println("Where do you want to add a new Cineplex ?");
                    for (int i = 0; i < Location.values().length; i++) {
                        System.out.println("(" + (i + 1) + ") " + Location.values()[i].getLabel());
                    }
                    System.out.println("(" + (Location.values().length + 1) + ") Exit");
                    opt = Helper.readInt(1, Location.values().length + 1);

                    if (opt == (Location.values().length + 1)) {
                    } else {
                        CineplexManager.addCineplex(opt);
                        numOfCineplex = CineplexManager.getTotalNumOfCineplex();
                    }
                } else if (choice == 2) {
                    Helper.clearScreen();
                    printRoute(this.path + " > Cineplex > Remove Cineplex");
                    if (numOfCineplex == 0) {
                        System.out.println("No cineplex found!");
                    } else {
                        System.out.println("Which cineplex do you want to remove ?");
                        for (int i = 0; i < numOfCineplex; i++) {
                            System.out.println(
                                    "(" + (i + 1) + ") " + CineplexManager.getCineplexList().get(i).getLocation());
                        }
                        System.out.println("(" + (numOfCineplex + 1) + ") Exit");
                        opt = Helper.readInt(1, numOfCineplex + 1);
                        if (opt == numOfCineplex + 1) {
                        } else {
                            Cineplex old = CineplexManager.getCineplexList().get(opt - 1);
                            CineplexManager.removeCineplex(old);
                            numOfCineplex = CineplexManager.getTotalNumOfCineplex();
                        }
                    }
                } else {
                    break;
                }
                Helper.pressAnyKeyToContinue();
            } while (choice != 3);
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
        }
    }
}
