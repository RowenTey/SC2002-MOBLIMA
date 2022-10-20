package model;

import java.util.ArrayList;
import model.enums.Location;

/**
 * The class that initialise a Cineplex.
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */
public class Cineplex {
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
     * List of ShowTimes in the Cineplex
     */
    private ArrayList<Showtime> showtimeList;

    /**
     * Constructor of Cineplex
     * 
     * @param location Location of Cineplex
     */
    public Cineplex(Location location, int numOfCinemas) {
        this.location = location;
        this.numOfCinemas = numOfCinemas;
        this.initCineplex();
    }

    /**
     * Initialise Cineplex
     */
    public void initCineplex() {

        String cinemaCode = this.location.getLabel().substring(0,3).toUpperCase();
        this.cinemaList = new ArrayList<Cinema>();
        for (int i = 0; i < this.numOfCinemas; i++) {
            Cinema newCinema = new Cinema(this, cinemaCode, false, false);
            this.cinemaList.add(newCinema);
        }
    }

    /**
     * Gets the location of the Cineplex
     * 
     * @return location of the Cineplex
     */
    public String getLocation() {
        return this.location.getLabel();
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
     * Gets the list of cinemas in the Cineplex
     * 
     * @return list of cinemas
     */
    public ArrayList<Cinema> getCinemaList() {
        return this.cinemaList;
    }

    /**
     * Gets the list of movies in the Cineplex
     * 
     * @return list of movies
     */
    public ArrayList<Showtime> getShowtimeList(){
        return this.showtimeList;
    }
}
