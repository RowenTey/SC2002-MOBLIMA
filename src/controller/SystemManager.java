package src.controller;

import java.util.ArrayList;
import java.util.Map;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Movie;
import src.model.User;
import src.model.enums.MoviesType;

/**
 * SystemManager is a controller class that handles system configurations.
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-31
 */
public class SystemManager {

  /**
   * Add a holiday
   * 
   * @param date of holiday to add
   * @return {@code true} if holiday was added, {@code false} otherwise
   */
  public static boolean addHoliday(String date) {
    Database.HOLIDAYS.add(date);
    Database.saveFileIntoDatabase(FileType.HOLIDAYS);
    System.out.println("Holiday on " + date + " added!");
    return true;
  }

  /**
   * Get the current ticket types based on {@link MoviesType}
   * 
   * @return {@code ArrayList<MoviesType>} of {@link MoviesType}
   */
  private static ArrayList<MoviesType> getTicketTypes() {
    ArrayList<MoviesType> ticketTypes = new ArrayList<MoviesType>();
    for (MoviesType MoviesType : Database.PRICES.keySet()) {
      ticketTypes.add(MoviesType);
    }
    return ticketTypes;
  }

  /**
   * Set ticket prices based on movie type
   * 
   * @return {@code true} if ticket price was updated, {@code false} otherwise
   */
  public static boolean updateTicketPrices() {
    displayTicketPricesStaff();
    ArrayList<MoviesType> ticketTypes = getTicketTypes();
    int opt = -1;

    System.out.println("Which ticket price would you like to update: ");
    for (int i = 0; i < ticketTypes.size(); i++) {
      System.out.println("(" + (i + 1) + ") " + ticketTypes.get(i));
    }
    System.out.println("(" + (ticketTypes.size() + 1) + ") Exit");
    opt = Helper.readInt(1, ticketTypes.size() + 1);
    if (opt != ticketTypes.size() + 1) {
      MoviesType type = ticketTypes.get(opt - 1);
      System.out.println("\nUpdate price to: ");
      double newPrice = Helper.readDouble(0, 100);
      Database.PRICES.put(type, newPrice);
      Database.saveFileIntoDatabase(FileType.PRICES);
      if (updateMoviePrices(type, newPrice)) {
        System.out.println("Ticket price successfully updated!");
      }
    }
    return true;
  }

  /**
   * Update existing {@link Movie} prices after ticket price have been updated
   * 
   * @param movieType to be updated
   * @param price     to update with
   * @return boolean {@code true} if movie price was updated, {@code false}
   *         otherwise
   */
  private static boolean updateMoviePrices(MoviesType movieType, double price) {
    for (Movie movie : Database.MOVIES.values()) {
      if (movie.getType() == movieType) {
        movie.setPrice(price);
      }
    }
    return true;
  }

  /**
   * Validates the user authentication
   * 
   * @param username entered
   * @param password entered
   * @return {@code true} is valid user, {@code false} otherwise
   */
  public static boolean validateStaff(String username, String password) {
    for (User staff : Database.USERS.values()) {
      if (staff.getName().equals(username) && staff.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Initialize {@link Database} with 10 random holidays
   */
  public static void initializeHolidays() {
    String date;
    for (int i = 0; i < 10; i++) {
      date = Helper.generateRandomDate().substring(0, 10);
      addHoliday(date);
    }
  }

  /**
   * Display current ticket prices for staff to manage
   */
  protected static void displayTicketPricesStaff() {
    System.out.println("List of Current Ticket Prices");
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    for (Map.Entry<MoviesType, Double> currentTicketType : Database.PRICES.entrySet()) {
      System.out.println(String.format("%-30s: %s", currentTicketType.getKey(),
          Helper.df2.format(currentTicketType.getValue())));
    }
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
  }

  /**
   * Display usual ticket prices to movie goer
   */
  public static void displayTicketPrices() {
    double price = Database.PRICES.get(MoviesType.TWO_D);

    System.out.println("List of Normal Ticket Prices Inclusive of GST (Non-Blockbuster and Non-3D)");
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println(String.format("%-30s: %s", "Monday-Friday before 7pm",
        "$" + Helper.df2.format(price * 1.07)));
    System.out.println(String.format("%-30s: %s", "Monday-Friday after 7pm",
        "$" + Helper.df2.format(price * 1.15 * 1.07)));
    System.out.println(String.format("%-30s: %s", "Weekends & Public Holidays",
        "$" + Helper.df2.format(price * 1.3 * 1.07)));
    System.out.println(String.format("%-40s", "").replace(" ", "-"));

    System.out.println();

    System.out.println("*Special Note");
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println(String.format("%-30s: %s", "Children & Senior Citizens",
        "50% of all sessions"));
    System.out.println(String.format("%-30s: %s", "Platinum Cinemas",
        "Addtional charge of SGD5"));
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
  }
}
