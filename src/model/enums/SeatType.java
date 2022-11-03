package src.model.enums;

/**
 * An enum that corresponds to the different types of seat
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-11-3
 */
public enum SeatType {
    /**
     * Seat type corresponding to "Normal Seat"
     */
    NORMAL("Normal"),

    /**
     * Seat type corresponding to "Couple Seat"
     */
    COUPLE("Couple"),

    /**
     * Seat type corresponding to "Elite Seat"
     */
    ELITE("Elite"),

    /**
     * Seat type corresponding to "Ultima Seat"
     */
    ULTIMA("Ultima");

    /**
     * A String value for the Seat type for retrieving purposes
     */
    public final String label;

    /**
     * Constructor for the SeatType Enum.
     * 
     * @param label Seat type as a string
     */
    private SeatType(String label) {
        this.label = label;
    }

    /**
     * Get function to access the label of the Seat Type
     * 
     * @return the string label of the Seat Type
     */
    public String getLabel() {
        return this.label;
    }
}