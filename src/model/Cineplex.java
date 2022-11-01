package src.model;

import java.io.Serializable;
import java.util.ArrayList;

import src.model.enums.Location;

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
     * Id of cineplex
     */
    private String cineplexID;

    /**
     * {@link Location} of the Cineplex
     */
    private Location location;

    /**
     * Number of cinemas in the Cineplex
     */
    private int numOfCinemas;

    /**
     * List of {@link Movie} of the Cineplex
     */
    private ArrayList<Cinema> cinemaList;

    /**
     * Constructor of Cineplex
     * 
     * @param cineplexId of cineplex
     * @param location of Cineplex
     */
    public Cineplex(String cineplexId, Location location) {
        setCineplexId(cineplexId);
        this.location = location;
        this.numOfCinemas = 10;
        this.cineplexID = cineplexId;
        initCineplex();
    }

    /**
     * Initialize the cineplex with {@link Cinema}
     */
    private void initCineplex() {
        String cinemaCode = location.getLabel().substring(0, 2).toUpperCase();
        this.cinemaList = new ArrayList<Cinema>();
        for (int i = 0; i < this.numOfCinemas; i++) {
            String s = "" + i;
            Cinema newCinema = new Cinema(this.getLocation(), cinemaCode + s, i >= 4 ? false : true);
            this.cinemaList.add(newCinema);
        }
    }

    /**
     * Sets the ID of the cineplex
     * 
     * @param newID of cineplex
     */
    public void setCineplexId(String newID) {
        this.cineplexID = newID;
    }

    /**
     * Sets the number of cinemas in the cineplex
     * 
     * @param newNum number of cinemas of the cineplex
     */
    public void setNumOfCinemas(int newNum) {
        this.numOfCinemas = newNum;
    }

    /**
     * Sets the list of {@link Cinema} in the cineplex
     * 
     * @param newList of cinemas of the cineplex
     */
    public void setCinemaList(ArrayList<Cinema> newList) {
        this.cinemaList = newList;
    }

    /**
     * Gets the ID of the cineplex
     * 
     * @return cineplexID of the cineplex
     */
    public String getCineplexId() {
        return this.cineplexID;
    }

    /**
     * Gets the {@link Location} of the cineplex
     * 
     * @return the {@link Location} of cineplex
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Gets the location of the Cineplex in a string
     * 
     * @return location of the Cineplex in string format
     */
    public String getLocationStr() {
        return this.location.getLabel();
    }

    /**
     * Gets the number of cinemas of the cineplex
     * 
     * @return number of cinemas of the cineplex
     */
    public int getNumOfCinemas() {
        return this.numOfCinemas;
    }

    /**
     * Gets the list of {@link Cinema} of the cineplex
     * 
     * @return list of {@link Cinema} of the cineplex
     */
    public ArrayList<Cinema> getCinemaList() {
        return this.cinemaList;
    }
}
