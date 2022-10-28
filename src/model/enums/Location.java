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

  public final String label;

  private Location(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }
}