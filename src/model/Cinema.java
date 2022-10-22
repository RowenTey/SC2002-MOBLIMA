package model;

import java.io.Serializable;

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
    private Cineplex cineplex;

    /**
     * Unique code of the cinema
     */
    private String cinemaCode;

    /**
     * Type of the cinema (Platinum/Normal)
     */
    private boolean isPlatinum;

    /**
     * Type of movies being showed in the cinema (3D/2D)
     */
    private boolean is3D;

    /**
     * Constructor of Cinema
     * 
     * @param cineplex   Cineplex
     * @param cinemaCode Code of the cinema
     * @param isPlatinum Type of cinema
     * @param is3D       Type of movies showed in cinema
     */

    public Cinema(Cineplex cineplex, String cinemaCode, boolean isPlatinum, boolean is3D) {
        this.cineplex = cineplex;
        this.cinemaCode = cinemaCode;
        this.isPlatinum = isPlatinum;
        this.is3D = is3D;
    }

    /**
     * Gets the Cineplex of the cinema
     * 
     * @return Cineplex of cinema
     */
    public Cineplex getCineplex() {
        return this.cineplex;
    }

    /**
     * Gets the code of the cinema
     * 
     * @return code of cinema
     */
    public String getCinemaCode() {
        return this.cinemaCode;
    }

    /**
     * Gets the type of cinema (Platinum/Normal)
     * 
     * @return Type of cinema
     */
    public boolean getIsPlatinum() {
        return this.isPlatinum;
    }

    /**
     * Gets the type of movies showed in cinema (3D/2D)
     * 
     * @return Type of movies showed
     */
    public boolean getIs3D() {
        return this.is3D;
    }
}
