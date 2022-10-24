package controller;

import helper.Helper;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import database.Database;
import database.FileType;
import model.Cineplex;
import model.enums.Location;

/**
 * Cineplex Manager
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-22
 */
public class CineplexManager {
    /**
     * List of cineplex
     */
    private static ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();

    /**
     * total number of cineplex
     */
    private static int totalCineplex;

    public CineplexManager() {
        readCineplexes();
        CineplexManager.totalCineplex = cineplexList.size();
    }

    /**
     * get the number of cineplexes
     * 
     * @return the total number of cineplexes
     */
    public static int getTotalNumOfCineplex() {
        return CineplexManager.totalCineplex;
    }

    public static void readCineplexes() {
        for (Cineplex cineplex : Database.CINEPLEX.values()) {
            cineplexList.add(cineplex);
        }
    }

    /**
     * Add new cineplex
     */
    public static void addCineplex() {
        int opt = CineplexManager.promptLocation();
        if (opt != (Location.values().length + 1)) {
            int cId = Helper.generateUniqueId(Database.CINEPLEX);
            String cineplexId = String.format("C%04d", cId);
            Cineplex newCineplex = new Cineplex(Location.values()[opt - 1]);
            Database.CINEPLEX.put(cineplexId, newCineplex);
            Database.saveFileIntoDatabase(FileType.CINEPLEX);
            CineplexManager.cineplexList.add(newCineplex);
            System.out.println("Cineplex created! Cineplex Details: ");
            CineplexManager.totalCineplex += 1;
        }
    }

    /**
     * Remove a cineplex
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
                CineplexManager.cineplexList.remove(old);
                System.out.println("Removed cineplex!");
                CineplexManager.totalCineplex -= 1;
            }
        }
    }

    /**
     * Get the list of cineplex
     * 
     * @return an array of cineplexes
     */
    public static ArrayList<Cineplex> getCineplexList() {
        return CineplexManager.cineplexList;
    }

    /**
     * Display existing Cineplexes
     */
    public static void displayExistingCineplex() {
        System.out.println("Current Cineplex(es) we have: ");
        for (int i = 0; i < CineplexManager.getTotalNumOfCineplex(); i++) {
            System.out.println("(" + (i + 1) + ") " + CineplexManager.getCineplexList().get(i).getLocation());
        }
    }

    /**
     * Prompt which Cineplex to add
     */
    public static int promptLocation() {
        int opt = 1;
        if (CineplexManager.getTotalNumOfCineplex() != 0) {
            CineplexManager.displayExistingCineplex();
            System.out.println();
        }

        System.out.println("Where do you want to add a new Cineplex ?");
        for (int i = 0; i < Location.values().length; i++) {
            System.out.println("(" + (i + 1) + ") " + Location.values()[i].getLabel());
        }

        System.out.println("(" + (Location.values().length + 1) + ") Exit");
        opt = Helper.readInt(1, Location.values().length + 1);
        return opt;
    }

}
