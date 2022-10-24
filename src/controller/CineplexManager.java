package controller;

import helper.Helper;
import java.util.ArrayList;

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
     * Total number of cineplex
     */
    private static int totalCineplex;

    public CineplexManager() {
        cineplexList.clear();
        readCineplexes();
        CineplexManager.totalCineplex = cineplexList.size();
    }

    /**
     * Initializer for cineplex
     */
    public static void initializeCineplex() {
        CineplexManager.addCineplex(1);
    }

    /**
     * Print details of cineplex
     */
    public static void printCineplexDetails(Cineplex cineplex) {
        System.out.println();
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println(String.format("%-20s: %s", "CineplexId", cineplex.getCineplexId()));
        System.out.println(String.format("%-20s: %s", "Location", cineplex.getLocation()));
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println();
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
    public static void addCineplex(int opt) {
        if (opt != (Location.values().length+ 1)) {
            int cId = Helper.generateUniqueId(Database.CINEPLEX);
            String cineplexId = String.format("C%04d", cId);
            Cineplex newCineplex = new Cineplex(Location.values()[opt]);
            Database.CINEPLEX.put(cineplexId, newCineplex);
            Database.saveFileIntoDatabase(FileType.CINEPLEX);
            CineplexManager.cineplexList.add(newCineplex);
            System.out.println("Cineplex created! Cineplex Details: ");
            printCineplexDetails(newCineplex);
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
        
        ArrayList<Integer> available_locations = new ArrayList<Integer>();
        available_locations.add(0); //Causeway
        available_locations.add(1); //Amk
        available_locations.add(2);// Jem
        for(int i=0; i<CineplexManager.getTotalNumOfCineplex(); i++){
            if(CineplexManager.cineplexList.get(i).getLocation() == "Causeway Point"){
                available_locations.remove(Integer.valueOf(0));
            }
            else if(CineplexManager.cineplexList.get(i).getLocation() == "Amk Hub"){
                available_locations.remove(Integer.valueOf(1));
            }
            else if(CineplexManager.cineplexList.get(i).getLocation() == "Jem"){
                available_locations.remove(Integer.valueOf(2));
            }
        }

        System.out.println("Where do you want to add a new Cineplex ?");
        for (int i = 0; i < available_locations.size(); i++) {
            System.out.println("(" + (i+1) + ") " + Location.values()[available_locations.get(i)].getLabel());
        }

        System.out.println("(" + (available_locations.size() + 1) + ") Exit");
        opt = Helper.readInt(1, available_locations.size()+1);
        if(opt == available_locations.size()+1){
            return -1;
        }
        return available_locations.get(opt-1);
    }
}
