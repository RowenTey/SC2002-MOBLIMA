package controller;

import helper.Helper;
import java.util.ArrayList;

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
    private static int totalCineplex = 0;

    /**
     * get the number of cineplexes
     * 
     * @return the total number of cineplexes
     */
    public static int getTotalNumOfCineplex() {
        return CineplexManager.totalCineplex;
    }

    /**
     * Add new cineplex
     */
    public static void addCineplex(int choice) {
        Cineplex newCineplex = new Cineplex(Location.values()[choice - 1]);
        CineplexManager.cineplexList.add(newCineplex);
        System.out.println("New cineplex added!");
        CineplexManager.totalCineplex += 1;
    }

    /**
     * Remove a cineplex
     */
    public static void removeCineplex(Cineplex old) {
        CineplexManager.cineplexList.remove(old);
        System.out.println("Removed cineplex!");
        CineplexManager.totalCineplex -= 1;
    }

    /**
     * Get the list of cineplex
     * 
     * @return an array of cineplexes
     */
    public static ArrayList<Cineplex> getCineplexList() {
        return CineplexManager.cineplexList;
    }
}
