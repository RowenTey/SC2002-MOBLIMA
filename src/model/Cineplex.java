package model;

import java.util.ArrayList;

/**
 * The class that initialise a Cineplex.
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-19
 */
public class Cineplex {
    /**
     * Location of the Cineplex
     */
    private String location;

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
     * @param location    Location of Cineplex
     */
    public Cineplex(String location, int numOfCinemas){
        this.location = location;
        this.numOfCinemas = numOfCinemas;
    }

    /**
     * initialise Cineplex
     * 
     */
    public void initCineplex(){
        this.cinemaList = new ArrayList<Cinema>();
        for(int i=0; i<this.numOfCinemas;i++){
            Cinema newCinema = new Cinema(this,i, false, false);
            this.cinemaList.add(newCinema);
        }
    }

    /**
     * Gets the location of the Cineplex
     *     
     * @return location of the Cineplex
     */
    public String getLocation(){
        return this.location;
    }

    /**
     * Gets the number of cinemas in the Cineplex
     * 
     * @return number of cinemas in the Cineplex
     */
    public int getNumOfCinemas(){
        return this.numOfCinemas;
    }

    /**
     * Gets the list of cinemas in the Cineplex
     * 
     * @return list of cinemas
     */
    public ArrayList<Cinema> getCinemaList(){
        return this.cinemaList;
    }
}
