package src.model.enums;

/**
 * An enum that corresponds to the different types of movies
 * 
 * @author Horstann Ho
 * @version 1.0
 * @since 2022-10-20
 */
public enum TypeMovies {
    /**
     * Movie type corresponding to "2D"
     */
    TWO_D("2D"),

    /**
     * Movie type corresponding to "3D"
     */
    THREE_D("3D"),

    /**
     * Movie type corresponding to "Blockbuster"
     */
    BLOCKBUSTER("Blockbuster");

    /**
     * A String value for the Layout type for retrieving purposes
     */
    public final String label;

    /**
     * Constructor for the layoutType Enum.
     * 
     * @param label Layout type as a string
     */
    private TypeMovies(String label) {
        this.label = label;
    }

    /**
     * Get function to access the label of the Movie Type
     * 
     * @return the string label of the Movie Type
     */
    public String getLabel() {
        return this.label;
    }
}