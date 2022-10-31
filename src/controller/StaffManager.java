package src.controller;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Staff;

/**
 * StaffManager is a controller class that acts as a "middleman" between the
 * view
 * classes - CineplexAppView and StaffView and the model class -
 * {@link Staff}.
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-31
 */
public class StaffManager {
  /**
   * Create a staff account
   * 
   * @param username of the staff
   * @param password of the staff
   */
  public static void createStaff(String username, String password) {
    int uId = Helper.generateUniqueId(Database.STAFF);
    String userId = String.format("U%04d", uId);
    Staff newStaff = new Staff(userId, username, password);
    Database.STAFF.put(userId, newStaff);
    Database.saveFileIntoDatabase(FileType.STAFF);
    System.out.println("Staff created! Staff Details: ");
    printStaffDetails(newStaff);
  }

  /**
   * Validates the staff authentication
   * 
   * @param username entered
   * @param password entered
   * @return {@code true} if user is valid staff, {@code false} otherwise
   */
  public static boolean validateStaff(String username, String password) {
    for (Staff staff : Database.STAFF.values()) {
      if (staff.getName().equals(username) && staff.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Initialize {@link Database} with {@link Staff} accounts
   */
  public static void initializeStaff() {
    createStaff("ks123", "ks123");
    createStaff("sw123", "sw123");
    createStaff("hor123", "hor123");
    createStaff("ace123", "ace123");
    createStaff("xy123", "xy123");
  }

  /**
   * Print the complete details of the {@link Staff}
   * 
   * @param staff object to print
   */
  protected static void printStaffDetails(Staff staff) {
    System.out.println();
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println(String.format("%-20s: %s", "User ID", staff.getUserId()));
    System.out.println(String.format("%-20s: %s", "Username", staff.getName()));
    System.out.println(String.format("%-20s: %s", "Password", staff.getPassword()));
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println();
  }

  /**
   * Prints all {@link Staff} with details
   */
  protected static void printAllStaff() {
    for (Staff staff : Database.STAFF.values()) {
      System.out.println();
      printStaffDetails(staff);
    }
  }
}
