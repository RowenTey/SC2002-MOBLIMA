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

  public final String label;

  private AgeGroup(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }
}
