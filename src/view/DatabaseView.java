package view;

import controller.CineplexManager;
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
    System.out.println("(1) Initialize movies");
    System.out.println("(2) Initialize showtimes");
    System.out.println("(3) Initialize cineplex");
    System.out.println("(4) Reset database");
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
      switch (choice) {
        case 1:
          // Helper.clearScreen();
          // printBreadCrumbs("Hotel App View > Initialize guests");
          // if (initializeGuest()) {
          // System.out.println("Guest initialization successful");
          // } else {
          // System.out.println("Guest initialization unsuccessful");
          // }
          break;
        case 2:
          // Helper.clearScreen();
          // printBreadCrumbs("Hotel App View > Database View > Initialize menu");
          // if (initializeMenu()) {
          // System.out.println("Menu initialization successful");
          // } else {
          // System.out.println("Menu initialization unsuccessful");
          // }
          break;
        case 3:
          printRoute("Cineplex App > Staff > Database > Initialise Cineplex");
          CineplexManager.initializeCineplex();
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

  // /**
  // * A method that initialize dummy data for Guest.
  // *
  // * @return {@code true} if initialized successfully. Otherwise, {@code false}
  // * <p>
  // * see {@link Database} for more initialization details.
  // */
  // private boolean initializeGuest() {
  // return Database.initializeDummyGuests();
  // }

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
  private boolean resetDatabase() {
    if (Helper.promptConfirmation("reset the database")) {
      return Database.clearDatabase();
    } else {
      return false;
    }
  }
}