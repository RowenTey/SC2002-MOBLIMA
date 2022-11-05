package src.controller;

import java.util.ArrayList;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Cinema;
import src.model.Cineplex;
import src.model.enums.Location;

/**
 * CineplexManager is a controller class that acts as a "middleman" between the
 * view
 * classes - CineplexAppView and CineplexView and the model class -
 * {@link Cineplex}
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-22
 */
public class CineplexManager {
    /**
     * Initialize {@link Database} with {@link Cineplex}
     */
    public static void initializeCineplex() {
        CineplexManager.addCineplex(1);
        CineplexManager.addCineplex(2);
        CineplexManager.addCineplex(3);
    }

    /**
     * Gets the array list of {@link Cineplex}.
     * 
     * @return list of existing {@link Cineplex}.
     */
    public static ArrayList<Cineplex> getCineplexList() {
        ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
        for (Cineplex cineplex : Database.CINEPLEX.values()) {
            cineplexList.add(cineplex);
        }
        return cineplexList;
    }

    /**
     * Retrives the {@link Cineplex} by it's ID
     * 
     * @param cineplexId of cineplex
     * @return cineplex object corresponding to that ID
     */
    public static Cineplex getCineplexById(String cineplexId) {
        if (Database.CINEPLEX.containsKey(cineplexId)) {
            return Database.CINEPLEX.get(cineplexId);
        }
        return null;
    }

    /**
     * Prints the complete details of the {@link Cineplex}.
     * 
     * @param cineplex to be printed.
     */
    protected static void printCineplexDetails(Cineplex cineplex) {
        System.out.println();
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println(String.format("%-20s: %s", "CineplexId", cineplex.getCineplexId()));
        System.out.println(String.format("%-20s: %s", "Location", cineplex.getLocationStr()));
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println();
    }

    /**
     * Gets the total number of existing {@link Cineplex}.
     * 
     * @return the total number of existing {@link Cineplex}.
     */
    public static int getTotalNumOfCineplex() {
        return CineplexManager.getCineplexList().size();
    }

    /**
     * Adds a new {@link Cineplex} to the {@link Database}
     * 
     * @param opt - the index of the location chosen from the {@link Location} enum
     *            class.
     */
    public static void addCineplex(int opt) {
        if (opt != (Location.values().length + 1)) {
            int cId = Helper.generateUniqueId(Database.CINEPLEX);
            String cineplexId = String.format("C%04d", cId);
            Cineplex newCineplex = new Cineplex(cineplexId, Location.values()[opt]);
            Database.CINEPLEX.put(cineplexId, newCineplex);
            Database.saveFileIntoDatabase(FileType.CINEPLEX);
            System.out.println("Cineplex created! Cineplex Details: ");
            printCineplexDetails(newCineplex);
        }
    }

    /**
     * Removes a {@link Cineplex} from {@link Database}
     */
    public static void removeCineplex(int opt) {
        Cineplex old = CineplexManager.getCineplexList().get(opt - 1);
        Database.CINEPLEX.remove(old.getCineplexId());
        ShowtimeManager.removeShowtimeByCineplex(old);
        Database.saveFileIntoDatabase(FileType.CINEPLEX);
        System.out.println("Removed cineplex!");
    }

    /**
     * Displays all existing {@link Cineplex}
     */
    public static void displayExistingCineplex() {
        System.out.println("Current Cineplex(es) we have: ");
        for (int i = 0; i < CineplexManager.getTotalNumOfCineplex(); i++) {
            System.out.println("(" + (i + 1) + ") " + CineplexManager.getCineplexList().get(i).getLocationStr());
        }
    }

    /**
     * Shows and prompts the admin to select a location to add a new
     * {@link Cineplex}
     * 
     * @return the index of location chosen
     */
    public static int promptLocation() {
        ArrayList<Cineplex> cineplexList = CineplexManager.getCineplexList();
        int opt = 1;
        if (CineplexManager.getTotalNumOfCineplex() != 0) {
            CineplexManager.displayExistingCineplex();
            System.out.println();
        }

        ArrayList<Integer> available_locations = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            available_locations.add(i);
        }
        for (int i = 0; i < CineplexManager.getTotalNumOfCineplex(); i++) {
            if (cineplexList.get(i).getLocationStr() == "Causeway Point") {
                available_locations.remove(Integer.valueOf(0));
            } else if (cineplexList.get(i).getLocationStr() == "Amk Hub") {
                available_locations.remove(Integer.valueOf(1));
            } else if (cineplexList.get(i).getLocationStr() == "Jem") {
                available_locations.remove(Integer.valueOf(2));
            } else if (cineplexList.get(i).getLocationStr() == "Somerset 313") {
                available_locations.remove(Integer.valueOf(3));
            } else if (cineplexList.get(i).getLocationStr() == "Jurong Point") {
                available_locations.remove(Integer.valueOf(4));
            }
        }

        System.out.println("Where do you want to add a new Cineplex ?");
        for (int i = 0; i < available_locations.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + Location.values()[available_locations.get(i)].getLabel());
        }

        System.out.println("(" + (available_locations.size() + 1) + ") Exit");
        opt = Helper.readInt(1, available_locations.size() + 1);
        if (opt == available_locations.size() + 1) {
            return -1;
        }
        return available_locations.get(opt - 1);
    }

    /**
     * Selects a specific {@link Cineplex} by index
     * 
     * @return the selected {@link Cineplex} object.
     */
    protected static Cineplex selectCineplex() {
        ArrayList<Cineplex> cineplexList = CineplexManager.getCineplexList();
        Cineplex selectedCineplex;
        System.out.println("Select a cineplex by entering it's index:");
        int choice = Helper.readInt(1, (cineplexList.size() + 1));
        selectedCineplex = cineplexList.get(choice - 1);
        System.out.println("\nYou selected:");
        CineplexManager.printCineplexDetails(selectedCineplex);
        Helper.pressAnyKeyToContinue();
        return selectedCineplex;
    }

    /**
     * Selects a specific {@link Cinema} from a {@link Cineplex}
     * 
     * @param selectedCineplex - the selected cineplex
     * 
     * @return the selected {@link Cinema}
     */
    protected static Cinema selectCinema(Cineplex selectedCineplex) {
        displayCinema(selectedCineplex);
        int choice = Helper.readInt(1, (selectedCineplex.getCinemaList().size() + 1));
        Cinema cinema = selectedCineplex.getCinemaList().get(choice - 1);
        System.out.println("\nYou selected: " + cinema.getCinemaCode());
        return cinema;
    }

    /**
     * Displays the list of {@link Cinema} of this {@link Cineplex}
     * 
     * @param selectedCineplex - the selected cineplex
     */
    private static void displayCinema(Cineplex selectedCineplex) {
        System.out.println("List of cinema(s):");
        for (int i = 0; i < selectedCineplex.getCinemaList().size(); i++) {
            System.out.println(
                    "(" + (i + 1) + ") " + "Cinema " + selectedCineplex.getCinemaList().get(i).getCinemaCode() + " ("
                            + (selectedCineplex.getCinemaList().get(i).getIsPlatinum() ? "Platinum" : "Normal")
                            + ")");
        }
    }

}
