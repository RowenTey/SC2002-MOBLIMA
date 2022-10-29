package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.enums.Location;

/**
 * The class that initialise a Cineplex.
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */
public class Cineplex implements Serializable {
    /**
     * For Java Serializable.
     */
    private static final long serialVersionUID = 4L;

    /**
     * Cineplex ID
     */
    private String cineplexID;

    /**
     * Location of the Cineplex
     */
    private Location location;

    /**
     * Number of cinemas in the Cineplex
     */
    private int numOfCinemas;

    /**
     * List of cinemas in the Cineplex
     */
    private ArrayList<Cinema> cinemaList;

    /**
     * Constructor of Cineplex
     * 
     * @param location Location of Cineplex
     */
    public Cineplex(String cineplexId, Location location) {
        setCineplexId(cineplexId);
        this.location = location;
        this.numOfCinemas = 10;
        this.cineplexID = cineplexId;
        initCineplex();
    }

    /**
     * Initialise Cineplex
     */
    private void initCineplex() {
        String cinemaCode = location.getLabel().substring(0, 2).toUpperCase();
        this.cinemaList = new ArrayList<Cinema>();
        for (int i = 0; i < this.numOfCinemas; i++) {
            String s = "" + i;
            Cinema newCinema = new Cinema(this, cinemaCode + s, i>=4? false: true);
            this.cinemaList.add(newCinema);
        }
    }

    /**
     * Gets the ID of the cineplex
     * 
     * @return cineplexID
     */
    public String getCineplexId() {
        return this.cineplexID;
    }

    /**
     * Sets the ID of the cineplex
     * 
     * @param cineplexID
     */
    public void setCineplexId(String newID) {
        this.cineplexID = newID;
    }

    /**
     * Gets the location of the Cineplex in a String
     * 
     * @return string object of the location of Cineplex
     */
    public String getLocationStr() {
        return this.location.getLabel();
    }

    /**
     * Gets the location of the Cineplex
     * 
     * @return the location of Cineplex
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Gets the number of cinemas in the Cineplex
     * 
     * @return number of cinemas in the Cineplex
     */
    public int getNumOfCinemas() {
        return this.numOfCinemas;
    }

    /**
     * Sets the number of cinemas in the Cineplex
     * 
     * @param numberOfCinemas
     */
    public void setNumOfCinemas(int newNum) {
        this.numOfCinemas = newNum;
    }

    /**
     * Gets the list of cinemas in the Cineplex
     * 
     * @return list of cinemas
     */
    public ArrayList<Cinema> getCinemaList() {
        return this.cinemaList;
    }

    /**
     * Sets the list of cinemas in the Cineplex
     * 
     * @param cinemaList
     */
    public void setCinemaList(ArrayList<Cinema> newList) {
        this.cinemaList = newList;
    }
}
