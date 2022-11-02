package src.model;

import java.io.Serializable;

import src.model.enums.Location;

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
     * {@link Location} of the cinema
     */
    private Location location;

    /**
     * Unique code of the cinema
     */
    private String cinemaCode;

    /**
     * Type of the cinema
     */
    private boolean isPlatinum;

    /**
     * Constructor of Cinema
     * 
     * @param location of the Cinema
     * @param cinemaCode of the cinema
     * @param isPlatinum (type) of cinema
     */
    public Cinema(Location location, String cinemaCode, boolean isPlatinum) {
        setCineplex(location);
        setCinemaCode(cinemaCode);
        setIsPlatinum(isPlatinum);
    }

    /**
     * Sets the cinemaCode of the cinema
     * 
     * @param cinemaCode of the cinema
     */
    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    /**
     * Sets the type of cinema
     * 
     * @param isPlatinum (type) of cinema
     */
    public void setIsPlatinum(boolean isPlatinum) {
        this.isPlatinum = isPlatinum;
    }

    /**
     * Sets the {@link Location} of the cinema
     * 
     * @param location of the cinema
     */
    public void setCineplex(Location location) {
        this.location = location;
    }

    /**
     * Gets the {@link Location} of the cinema
     * 
     * @return {@link Location} of the cinema
     */
    public Location getCineplex() {
        return this.location;
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
     * Gets the type of cinema 
     * 
     * @return Type of cinema
     */
    public boolean getIsPlatinum() {
        return isPlatinum;
    }
}
