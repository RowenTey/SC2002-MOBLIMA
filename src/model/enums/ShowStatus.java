package model.enums;

/**
 * An enum that corresponds to the different types of show status
 * 
 * @author Horstann Ho
 * @version 1.0
 * @since 2022-10-20
 */
public enum ShowStatus {
    /**
    * ShowStatus type corresponding to "coming soon"
    */
    COMING_SOON("coming soon"),

    /**
    * Layout type corresponding to "now showing"
    */
    NOW_SHOWING("now showing"),

    /**
    * Layout type corresponding to "preview"
    */
    PREVIEW("preview"),

    /**
    * Layout type corresponding to "preview"
    */
    END_OF_SHOWING("end of showing");

    /**
    * A String value for the Layout type for retrieving purposes
    */
    public final String label;

    /**
     * Constructor for the layoutType Enum.
     * 
     * @param label Layout type as a string
     */
    private ShowStatus(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}