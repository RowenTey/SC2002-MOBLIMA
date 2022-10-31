package src.controller;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Staff;

public class StaffManager {
  /**
   * Constructor for the staff in the StaffManager
   * <p>
   * 
   * @param username the username of the staff
   * @param password the password of the staff
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
   * @param username user entered username
   * @param password user entered password
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
   * Initializer for cinema staffs
   */
  public static void initializeStaff() {
    createStaff("ks123", "ks123");
    createStaff("sw123", "sw123");
    createStaff("hor123", "hor123");
    createStaff("ace123", "ace123");
    createStaff("xy123", "xy123");
  }

  /**
   * Print the complete details of the staff
   * 
   * @param guest {@link Staff} object to print
   */
  public static void printStaffDetails(Staff staff) {
    System.out.println();
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println(String.format("%-20s: %s", "User ID", staff.getUserId()));
    System.out.println(String.format("%-20s: %s", "Username", staff.getName()));
    System.out.println(String.format("%-20s: %s", "Password", staff.getPassword()));
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println();
  }

  /**
   * Prints all staff with details
   */
  public static void printAllStaff() {
    for (Staff staff : Database.STAFF.values()) {
      System.out.println();
      printStaffDetails(staff);
    }
  }
}
