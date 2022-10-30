package model;

import java.io.Serializable;

import model.enums.Location;

/**
 * The class that initialise a Cinema.
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-19
 */
public class Cinema implements Serializable {
    /**
     * For Java Serializable.
     */
    private static final long serialVersionUID = 2L;

    /**
     * Its Cineplex
     */
    private Location cineplex;

    /**
     * Unique code of the cinema
     */
    private String cinemaCode;

    /**
     * Type of the cinema (Platinum/Normal)
     */
    private boolean isPlatinum;

    /**
     * Constructor of Cinema
     * 
     * @param cineplex   Cineplex
     * @param cinemaCode Code of the cinema
     * @param isPlatinum Type of cinema
     */
    public Cinema(Location cineplex, String cinemaCode, boolean isPlatinum) {
        setCineplex(cineplex);
        setCinemaCode(cinemaCode);
        setIsPlatinum(isPlatinum);
    }

    /**
     * Gets the Cineplex of the cinema
     * 
     * @return Cineplex of cinema
     */
    public Location getCineplex() {
        return cineplex;
    }

    /**
     * Sets the cinemaCode of the cinema
     */
    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    /**
     * Sets the if cinema is platinum
     */
    public void setIsPlatinum(boolean isPlatinum) {
        this.isPlatinum = isPlatinum;
    }

    /**
     * Sets the Cineplex of the cinema
     */
    public void setCineplex(Location cineplex) {
        this.cineplex = cineplex;
    }

    /**
     * Gets the code of the cinema
     * 
     * @return code of cinema
     */
    public String getCinemaCode() {
        return cinemaCode;
    }

    /**
     * Gets the type of cinema (Platinum/Normal)
     * 
     * @return Type of cinema
     */
    public boolean getIsPlatinum() {
        return isPlatinum;
    }
}
