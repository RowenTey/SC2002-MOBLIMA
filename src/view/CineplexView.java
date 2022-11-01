package src.view;

import src.controller.CineplexManager;
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
     * Path of entry for cineplex view
     */
    private String path;

    /**
     * boolean {@code true} if current user is staff, {@code false} otherwise
     */
    private boolean isStaff;

    /**
     * Default contructor for the CineplexView
     * 
     * @param path path of entry for CineplexView
     * @param isStaff boolean value if the current user is staff
     * 
     */
    public CineplexView(String path, boolean isStaff) {
        super();
        this.path = path;
        this.isStaff = isStaff;
    }

    /**
     * View Menu of the CineplexView
     */
    public void printMenu() {
        if (this.isStaff) {
            Helper.clearScreen();
            printRoute(this.path + " > Cineplex");
            System.out.println("Which would you like to do ?");
            System.out.println("(1) Add Cineplex");
            System.out.println("(2) Remove Cineplex");
            System.out.println("(3) Exit");
        } else {
            Helper.clearScreen();
            printRoute(this.path + " > Cineplex");
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
        int opt = -1;
        if (this.isStaff) {
            do {
                this.printMenu();
                numOfCineplex = CineplexManager.getTotalNumOfCineplex();
                choice = Helper.readInt(1, 3);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        printRoute(this.path + " > Cineplex > Add New Cineplex");
                        opt = CineplexManager.promptLocation();
                        if (opt == -1) {
                            break;
                        }
                        CineplexManager.addCineplex(opt);
                        break;
                    case 2:
                        Helper.clearScreen();
                        printRoute(this.path + " > Cineplex > Remove Cineplex");
                        CineplexManager.removeCineplex();
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
                    ShowtimeView showtimeView = new ShowtimeView(this.path + " > Showtimes");
                    showtimeView.viewApp(CineplexManager.getCineplexList().get(choice - 1));
                }
            } while (choice != (numOfCineplex + 1));
        }
    }
}
