package view;

import helper.Helper;

/**
 * Viewing interface for Booking
 *
 * @author Ace Ang
 * @version 1.0
 * @since 2022-10-23
 */
public class BookingView extends MainView {
  /**
   * Path of entry for booking view
   */
  public String path;

  /**
   * Default contructor for the BookingView
   */
  public BookingView(String path) {
    super();
    this.path = path;
  }

  /**
   * View Menu
   */
  public void printMenu() {
    Helper.clearScreen();
    printRoute(this.path + " > Booking View");
    System.out.println("What would you like to do ?");
    System.out.println("(1) Make a new booking.");
    System.out.println("(2) View booking history.");
    System.out.println("(3) Exit");
  };

  /**
   * View App
   */
  public void viewApp() {
    int choice = -1;
    do {
      this.printMenu();
      choice = Helper.readInt(1, 3);
      switch (choice) {
        case 1:
          System.out.println("Select a movie");
          // movieView.viewApp();
          System.out.println("Select a cineplex");
          // CineplexManager.getCineplexList();
          System.out.println("Select a showtime");
          // ShowtimeManager.getCurrentList
          break;
        case 2:
          System.out.println("Booking history");
          // TODO list bookings

          break;
        case 3:
          break;
        default:
          break;
      }
    } while (choice != 3);
  };
}