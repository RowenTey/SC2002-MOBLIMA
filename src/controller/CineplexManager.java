package controller;

import helper.Helper;
import java.util.ArrayList;

import database.Database;
import database.FileType;
import model.Cinema;
import model.Cineplex;
import model.enums.Location;

/**
 * Cineplex Manager is a controller class that helps the staff/admin to manage all the cineplexes.
 * 
 * It can initialize, create or remove {@link Cineplex} details.
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-22
 */
public class CineplexManager {
    /**
     * Initializer for Cineplex for demonstration of project. (3 Cineplexes will be initialized)
     */
    public static void initializeCineplex() {
        CineplexManager.addCineplex(1);
        CineplexManager.addCineplex(2);
        CineplexManager.addCineplex(3);
    }

    /**
     * Gets the array list of cineplexes.
     * @return ArrayList of existing cineplexes.
     */
    public static ArrayList<Cineplex> getCineplexList() {
        ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
        for (Cineplex cineplex : Database.CINEPLEX.values()) {
            cineplexList.add(cineplex);
        }
        return cineplexList;
    }

    /**
     * Print the complete details of the cineplex.
     * @param cineplex {@link Cineplex} object to print.
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
     * Gets the total number of existing cineplexes.
     * 
     * @return the total number of existing cineplexes.
     */
    public static int getTotalNumOfCineplex() {
        return CineplexManager.getCineplexList().size();
    }

    /**
     * Creates and adds a new cineplex object.
     * @param opt The index of the location chosen from the {@link Location} enum class.
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
     * Handles the removal of cineplex.
     */
    public static void removeCineplex() {
        int opt = -1;
        if (CineplexManager.getTotalNumOfCineplex() == 0) {
            System.out.println("No cineplex found!");
        } else {
            System.out.println("Which cineplex do you want to remove ?");
            CineplexManager.displayExistingCineplex();
            System.out.println("(" + (CineplexManager.getTotalNumOfCineplex() + 1) + ") Exit");
            opt = Helper.readInt(1, CineplexManager.getTotalNumOfCineplex() + 1);
            if (opt != CineplexManager.getTotalNumOfCineplex() + 1) {
                Cineplex old = CineplexManager.getCineplexList().get(opt - 1);
                Database.CINEPLEX.remove(old.getCineplexId());
                ShowtimeManager.removeShowtimeByCineplex(old);
                Database.saveFileIntoDatabase(FileType.CINEPLEX);
                System.out.println("Removed cineplex!");
            }
        }
    }

    /**
     * Display all existing Cineplexes
     */
    public static void displayExistingCineplex() {
        System.out.println("Current Cineplex(es) we have: ");
        for (int i = 0; i < CineplexManager.getTotalNumOfCineplex(); i++) {
            System.out.println("(" + (i + 1) + ") " + CineplexManager.getCineplexList().get(i).getLocationStr());
        }
    }

    /**
     * Shows and prompts the admin to select a location to add a new {@link Cineplex} object.
     * @return the index of location chosen from {@link Location}.
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
     * Allow user to select a specific cineplex by index
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
     * Allow user to select a specific cinema from a cineplex
     * 
     * @return the selected {@link Cinema} object.
     */
    protected static Cinema selectCinema(Cineplex selectedCineplex) {
        displayCinema(selectedCineplex);
        int choice = Helper.readInt(1, (selectedCineplex.getCinemaList().size() + 1));
        Cinema cinema = selectedCineplex.getCinemaList().get(choice - 1);
        System.out.println("\nYou selected: " + cinema.getCinemaCode());
        return cinema;
    }

    /**
     * Displays the list of cinemas of this cineplex.
     * 
     * @param selectedCineplex the selected cineplex
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
