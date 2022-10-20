package model.enums;

/**
 * An enum that corresponds to the different types of movies
 * 
 * @author Horstann Ho
 * @version 1.0
 * @since 2022-10-20
 */
public enum TypeMovies {
    /**
    * ShowStatus type corresponding to "coming soon"
    */
    TWO_D("2D"),

    /**
    * Layout type corresponding to "now showing"
    */
    THREE_D("3D"),

    /**
    * Layout type corresponding to "preview"
    */
    BLOCKBUSTER("preview");

    /**
    * A String value for the Layout type for retrieving purposes
    */
    public final String layoutTypeAsStr;

    /**
     * Constructor for the layoutType Enum.
     * 
     * @param layoutTypeAsStr Layout type as a string
     */
    private TypeMovies(String layoutTypeAsStr) {
        this.layoutTypeAsStr = layoutTypeAsStr;
    }
}