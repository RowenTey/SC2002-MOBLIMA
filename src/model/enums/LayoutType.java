package model.enums;

/**
 * An enum that corresponds to the different type of layout types of the
 * Showtime
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-18
 */
public enum LayoutType {
  /**
   * Layout type corresponding to "large"
   */
  LARGE("large"),

  /**
   * Layout type corresponding to "medium"
   */
  MEDIUM("medium"),

  /**
   * Layout type corresponding to "small"
   */
  SMALL("small");

  /**
   * A String value for the Layout type for retrieving purposes
   */
  public final String layoutTypeAsStr;

  /**
   * Constructor for the layoutType Enum.
   * 
   * @param layoutTypeAsStr Layout type as a string
   */
  private LayoutType(String layoutTypeAsStr) {
    this.layoutTypeAsStr = layoutTypeAsStr;
  }
}