package src.controller;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.MovieGoer;
import src.model.Staff;
import src.model.User;
import src.model.enums.AgeGroup;

/**
 * User is a controller class that acts as a "middleman" between the
 * view
 * classes - CineplexAppView and StaffView and the model class -
 * {@link Staff}.
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-31
 */
public class UserManager {
  /**
   * Create a staff account
   * 
   * @param username of the staff
   * @param password of the staff
   * @param mobile number of member
   * @param email of member
   * @param ageGroup of member
   * @param isStaff boolean {@code true} if the current user is staff, {@code false} otherwise
   */
  public static boolean createUser(String username, String password, String mobile, String email, AgeGroup ageGroup,
      boolean isStaff) {
    if (Database.USERS.containsKey(username)) {
      System.out.println("\nUser already exist!");
      return false;
    }

    int uId = Database.USERS.size() + 1;
    String userId = String.format("U%04d", uId);
    User newUser;
    if (isStaff) {
      newUser = new Staff(userId, username, password, true);
    } else {
      newUser = new MovieGoer(userId, username, mobile, email, ageGroup, password, false);
    }
    Database.USERS.put(username, newUser);
    Database.saveFileIntoDatabase(FileType.USERS);
    System.out.println("\nUser account created! User Details:");
    displayUserDetails(newUser);
    return true;
  }

  /**
   * Validates the user authentication
   * 
   * @param username entered
   * @param password entered
   * @return {@code true} if user is valid user, {@code false} otherwise
   */
  public static boolean validateUser(String username, String password) {
    if (Database.USERS.containsKey(username)) {
      if (Database.USERS.get(username).getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Initialize {@link Database} with {@link Staff} accounts
   */
  public static void initializeStaff() {
    createUser("$ks123", "ks123", "", "", null, true);
    createUser("$sw123", "sw123", "", "", null, true);
    createUser("$hor123", "hor123", "", "", null, true);
    createUser("$ace123", "ace123", "", "", null, true);
    createUser("$xy123", "xy123", "", "", null, true);
    createUser("test123", "test123", "88887803", "test@test.com", AgeGroup.CHILD, false);
  }

  /**
   * Print the complete details of the {@link User}
   * 
   * @param user object to print
   */
  protected static void displayUserDetails(User user) {
    System.out.println();
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println(String.format("%-20s: %s", "User ID", user.getUserId()));
    System.out.println(String.format("%-20s: %s", "Username", user.getName()));
    System.out.println(String.format("%-20s: %s", "Password", user.getPassword()));
    if (!user.getIsStaff()) {
      MovieGoer member = (MovieGoer) user;
      System.out.println(String.format("%-20s: %s", "Mobile", member.getMobile()));
      System.out.println(String.format("%-20s: %s", "Email", member.getEmail()));
      System.out.println(String.format("%-20s: %s", "Age Group", member.getAgeGroup()));
    }
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println();
  }

  /**
   * DIsplays all {@link Staff} with details
   */
  protected static void displayAllStaffs() {
    for (User staff : Database.USERS.values()) {
      System.out.println();
      displayUserDetails(staff);
    }
  }

  /**
   * Prompts the {@link User} details for sign up
   * 
   * @return boolean {@code true} if the user signed up successfully,
   *         {@code false} otherwise
   */
  public static boolean onSignUp() {
    System.out.print("Enter your username: ");
    String username = Helper.readString();
    System.out.print("Enter your password: ");
    String password = Helper.readString();

    String key = username.substring(0, 1);
    if (key.equals("$")) {
      return createUser(username, password, "", "", null, true);
    }

    System.out.print("Enter your mobile number (eg: 88889770): +65-");
    String mobile = Helper.readString();

    System.out.print("Enter your email: ");
    String email = Helper.readString();

    System.out.print("Enter your age: ");
    int age = Helper.readInt(1, 100);
    AgeGroup ageGroup;
    if (age >= 55) {
      ageGroup = AgeGroup.SENIOR_CITIZEN;
    } else if (age >= 21) {
      ageGroup = AgeGroup.ADULT;
    } else {
      ageGroup = AgeGroup.CHILD;
    }

    return createUser(username, password, mobile, email, ageGroup, false);
  }

  /**
   * Get {@link User} from database
   * 
   * @return {@code User} object if exist, {@code null} otherwise
   */
  protected static User getUser(String username) {
    if (Database.USERS.containsKey(username)) {
      return Database.USERS.get(username);
    }

    return null;
  }
}
