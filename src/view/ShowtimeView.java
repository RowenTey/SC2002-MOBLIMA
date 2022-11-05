package src.view;

import src.controller.ShowtimeManager;
import src.database.Database;
import src.helper.Helper;

/**
 * ShowtimeView provides the view to manage and select showtime
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */
public class ShowtimeView extends MainView {
    /**
     * Default contructor for the ShowtimeView
     */
    public ShowtimeView() {
        super();
    }

    /**
     * View Menu of ShowtimeView
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(Database.path);
        System.out.println("What would you like to do ?");
        System.out.println("(1) Create Showtime");
        System.out.println("(2) Remove Showtime");
        System.out.println("(3) Update Showtime");
        System.out.println("(4) List Showtimes");
        System.out.println("(5) Exit");
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        do {
            printMenu();
            choice = Helper.readInt(1, 5);
            switch (choice) {
                case 1:
                    Helper.clearScreen();
                    printRoute(Database.path + " > Create Showtime");
                    handleCreateShowtime();
                    Helper.pressAnyKeyToContinue();
                    break;
                case 2:
                    Helper.clearScreen();
                    printRoute(Database.path + " > Remove Showtime");
                    if (ShowtimeManager.removeShowtime()) {
                        System.out.println("\nRemoved showtime successfully!");
                    } else {
                        System.out.println("\nFailed to remove showtime!");
                    }
                    Helper.pressAnyKeyToContinue();
                    break;
                case 3:
                    Helper.clearScreen();
                    printRoute(Database.path + " > Update Showtime");
                    if (ShowtimeManager.updateShowtime()) {
                        System.out.println("Showtime successfully updated!");
                    } else {
                        System.out.println("\nFailed to update showtime!");
                    }
                    Helper.pressAnyKeyToContinue();
                    break;
                case 4:
                    Helper.clearScreen();
                    printRoute(Database.path + " > Showtime Listing");
                    ShowtimeManager.displayAllShowtime();
                    Helper.pressAnyKeyToContinue();
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } while (choice != 5);
    }

    /**
     * Overrided View App - from movie view / cineplex view (MovieGoer)
     * 
     * @param objId of movie/cineplex to select showtime from
     * @param from  path method was called from
     */
    protected void viewApp(String objId, String from) {
        Helper.clearScreen();
        printRoute(Database.path);
        ShowtimeManager.onShowtimeSelection(objId, from);
        return;
    }

    /**
     * Action function to handle creating a showtime
     */
    private void handleCreateShowtime() {
        if (ShowtimeManager.onCreateShowtime()) {
            System.out.println("\nShowtime created successfully!");
        } else {
            System.out.println("\nShowtime created unsuccessfully!");
        }
    }

}
