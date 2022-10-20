package model;

/**
 * The class that initialise a Cineplex.
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-19
 */
public class Cinema {

    /**
     * Its Cineplex
     */
    private Cineplex cineplex;

    /**
     * Unique ID of the cinema
     */
    private int cinemaID;

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
     * @param cinemaID   ID of the cinema
     * @param isPlatinum Type of cinema
     * @param is3D       Type of movies showed in cinema
     */

    public Cinema(Cineplex cineplex, int cinemaID, boolean isPlatinum, boolean is3D) {
        this.cineplex = cineplex;
        this.cinemaID = cinemaID;
        this.isPlatinum = isPlatinum;
        this.is3D = is3D;
    }

    /**
     * Gets the ID of the cinema
     * 
     * @return ID of cinema
     */
    public int getCinemaID() {
        return this.cinemaID;
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
