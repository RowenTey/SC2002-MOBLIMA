package view;

import database.Database;
import helper.Helper;

/**
 * DatabaseView provides the view to manage {@link Database}.
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-22
 */
public class DatabaseView extends MainView {
  /**
   * Default constructor of DatabaseView.
   */
  public DatabaseView() {
    super();
  }

  /**
   * View Menu of the DatabaseView.
   */
  @Override
  public void printMenu() {
    Helper.clearScreen();
    printRoute("Cineplex App > Staff > Database");
    System.out.println("What would you like to do ?");
    System.out.println("(1) Initialize Cineplex");
    System.out.println("(2) Initialize Movies");
    System.out.println("(3) Initialize Showtimes");
    System.out.println("(4) Reset Database");
    System.out.println("(5) Exit");
  }

  /**
   * View Application of the DatabaseView.
   * <p>
   * See {@link Database} for more details.
   */
  @Override
  public void viewApp() {
    int choice = -1;
    do {
      printMenu();
      choice = Helper.readInt(1, 5);
      System.out.println();
      switch (choice) {
        case 1:
          printRoute("Cineplex App > Staff > Database > Initialise Cineplex");
          if (initializeCineplex()) {
            System.out.println("Cineplex initialization successful");
          } else {
            System.out.println("Cineplex initialization unsuccessful");
          }
          break;
        case 2:
          printRoute("Cineplex App > Staff > Database > Initialise Movies");
          if (initializeMovies()) {
            System.out.println("Movie initialization successful");
          } else {
            System.out.println("Movie initialization unsuccessful");
          }
          break;
        case 3:
          printRoute("Cineplex App > Staff > Database > Initialise Showtimes");
          if (initializeShowtime()) {
            System.out.println("Showtimes initialization successful");
          } else {
            System.out.println("Showtimes initialization unsuccessful");
          }
          break;
        case 4:
          System.out.println();
          printRoute("Cineplex App > Staff > Database > Reset Database");
          if (resetDatabase()) {
            System.out.println("Database cleared");
          }
          break;
        case 5:
          break;
        default:
          break;
      }
      if (choice != 5) {
        System.out.println();
        Helper.pressAnyKeyToContinue();
      }
    } while (choice != 5);
  }

  /**
   * A method that initialize dummy data for Cineplex.
   *
   * @return {@code true} if initialized successfully. Otherwise, {@code false}
   *         <p>
   *         see {@link Database} for more initialization details.
   */
  private boolean initializeCineplex() {
    return Database.initializeCineplex();
  }

  /**
   * A method that initialize dummy data for Cineplex.
   *
   * @return {@code true} if initialized successfully. Otherwise, {@code false}
   *         <p>
   *         see {@link Database} for more initialization details.
   */
  private boolean initializeMovies() {
    return Database.initializeMovies();
  }

  /**
   * A method that initialize dummy data for Cineplex.
   *
   * @return {@code true} if initialized successfully. Otherwise, {@code false}
   *         <p>
   *         see {@link Database} for more initialization details.
   */
  private boolean initializeShowtime() {
    return Database.initializeShowtime();
  }

  // /**
  // * A method that initialize dummy data for Menu.
  // *
  // * @return {@code true} if initialized successfully. Otherwise, {@code false}
  // * <p>
  // * see {@link Database} for more initialization details.
  // */
  // private boolean initializeMenu() {
  // return Database.initializeDummyMenu();
  // }

  /**
   * A method that reset the database.
   * 
   * @return {@code true} if reset successfully. Otherwise, {@code false}
   *         <p>
   *         see {@link Database} for more details.
   */
  public static boolean resetDatabase() { //To be changed back to private boolean
    if (Helper.promptConfirmation("reset the database")) {
      return Database.clearDatabase();
    } else {
      return false;
    }
  }
}