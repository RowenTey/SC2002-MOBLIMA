package model.enums;

/**
 * An enum that stores the different locations of Cineplex
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */
public enum Location {

  /**
   * Causeway Point
   */
  CAUSEWAY_POINT("Causeway Point"),

  /**
   * Amk Hub
   */
  AMK_HUB("Amk Hub"),

  /**
   * Jem
   */
  JEM("Jem"),

  /**
   * Somerset 313
   */
  SOMERSET_313("Somerset 313"),

  /**
   * Jurong Point
   */
  JURONG_POINT("Jurong Point");

  /**
   * A String value for the location type for retrieving purposes
   */
  public final String label;

  /**
   * Constructor for the Location Enum.
   * 
   * @param label label type as a string
   */
  private Location(String label) {
    this.label = label;
  }

  /**
   * Get function to access the label of the location
   * 
   * @return the label of the location
   */
  public String getLabel() {
    return this.label;
  }
}