package model.enums;

/**
 * An enum that stores the different agegroups of movie-goers
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-19
 */

public enum AgeGroup {

  /**
   * ADULT AGEGROUP
   */
  ADULT("Adult"),

  /**
   * CHILD AGEGROUP
   */
  CHILD("Child"),

  /**
   * SENIOR AGEGROUP
   */
  SENIOR_CITIZEN("Senior Citizen");

  /**
   * A String value for the agegroup type for retrieving purposes
   */
  public final String label;

  /**
   * Constructor for the AgeGroup Enum.
   * 
   * @param label label type as a string
   */
  private AgeGroup(String label) {
    this.label = label;
  }

  /**
   * Get function to access the label of the age group
   * 
   * @return the label of the age group
   */
  public String getLabel() {
    return this.label;
  }
}
