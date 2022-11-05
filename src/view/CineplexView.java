package src.view;

import src.controller.CineplexManager;
import src.database.Database;
import src.helper.Helper;

/**
 * Viewing interface for Cineplex
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-22
 */
public class CineplexView extends MainView {
    /**
     * Default contructor for the CineplexView
     * 
     * @param path     of entry for CineplexView
     * @param isStaff  boolean value if the current user is staff
     * @param username of current user
     * 
     */
    public CineplexView() {
        super();
    }

    /**
     * View Menu of the CineplexView
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(Database.path);
        if (Database.isStaff) {
            System.out.println("Which would you like to do ?");
            System.out.println("(1) Add Cineplex");
            System.out.println("(2) Remove Cineplex");
            System.out.println("(3) Exit");
        } else {
            if (CineplexManager.getTotalNumOfCineplex() == 0) {
                System.out.println("We don't have any Cineplex at this time");
                System.out.println("(1) Exit");
            } else {
                int numOfCineplex = CineplexManager.getTotalNumOfCineplex();
                CineplexManager.displayExistingCineplex();
                System.out.println("(" + (numOfCineplex + 1) + ") Exit");
                System.out.println("Which location would you like to choose? ");
            }
        }
    }

    /**
     * View App
     */
    public void viewApp() {
        int numOfCineplex = CineplexManager.getTotalNumOfCineplex();
        int choice = -1;
        String toRestore = Database.path;
        if (Database.isStaff) {
            do {
                this.printMenu();
                numOfCineplex = CineplexManager.getTotalNumOfCineplex();
                choice = Helper.readInt(1, 3);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        printRoute(Database.path + " > Add New Cineplex");
                        handleAddCineplex();
                        break;
                    case 2:
                        Helper.clearScreen();
                        printRoute(Database.path + " > Remove Cineplex");
                        handleRemoveCineplex();
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
                if (choice != 3) {
                    System.out.println();
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 3);
        }

        else {
            do {
                this.printMenu();
                choice = Helper.readInt(1, numOfCineplex + 1);
                if (choice != numOfCineplex + 1) {
                    System.out.println(
                            "\n" + CineplexManager.getCineplexList().get(choice - 1).getLocation() + " selected");
                    Helper.pressAnyKeyToContinue();
                    String selectedCineplexId = CineplexManager.getCineplexList().get(choice - 1).getCineplexId();
                    Database.path = Database.path + " > "
                            + CineplexManager.getCineplexById(selectedCineplexId).getLocationStr();
                    CineplexAppView.showtimeView.viewApp(selectedCineplexId, "cineplex");
                    Database.path = toRestore;
                }
            } while (choice != (numOfCineplex + 1));
        }
    }

    /**
     * Handles addition of {@link Cineplex}
     */
    private void handleAddCineplex() {
        int opt = -1;
        opt = CineplexManager.promptLocation();
        if (opt == -1) {
            return;
        }
        CineplexManager.addCineplex(opt);
    }

    /**
     * Handles the removal of {@link Cineplex}.
     */
    private void handleRemoveCineplex() {
        int opt = -1;
        if (CineplexManager.getTotalNumOfCineplex() == 0) {
            System.out.println("No cineplex found!");
        } else {
            System.out.println("Which cineplex do you want to remove ?");
            CineplexManager.displayExistingCineplex();
            System.out.println("(" + (CineplexManager.getTotalNumOfCineplex() + 1) + ") Exit");
            opt = Helper.readInt(1, CineplexManager.getTotalNumOfCineplex() + 1);
            if (opt != CineplexManager.getTotalNumOfCineplex() + 1) {
                CineplexManager.removeCineplex(opt);
            }
        }
    }
}
