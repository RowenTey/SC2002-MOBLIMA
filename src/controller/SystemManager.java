package src.controller;

import java.util.ArrayList;
import java.util.Map;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Movie;
import src.model.enums.TypeMovies;

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
   * Get the current ticket types based on {@link TypeMovies}
   * 
   * @return {@code ArrayList<TypeMovies>} of {@link TypeMovies}
   */
  private static ArrayList<TypeMovies> getTicketTypes() {
    ArrayList<TypeMovies> ticketTypes = new ArrayList<TypeMovies>();
    for (TypeMovies typeMovies : Database.PRICES.keySet()) {
      ticketTypes.add(typeMovies);
    }
    return ticketTypes;
  }

  /**
   * Set ticket prices based on movie type
   * 
   * @return {@code true} if ticket price was updated, {@code false} otherwise
   */
  public static boolean updateTicketPrices() {
    displayTicketPrices();
    ArrayList<TypeMovies> ticketTypes = getTicketTypes();
    int opt = -1;

    System.out.println("Which ticket price would you like to update: ");
    for (int i = 0; i < ticketTypes.size(); i++) {
      System.out.println("(" + (i + 1) + ") " + ticketTypes.get(i));
    }
    System.out.println("(" + (ticketTypes.size() + 1) + ") Exit");
    opt = Helper.readInt(1, ticketTypes.size() + 1);
    if (opt != ticketTypes.size() + 1) {
      TypeMovies type = ticketTypes.get(opt - 1);
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
  private static boolean updateMoviePrices(TypeMovies movieType, double price) {
    for (Movie movie : Database.MOVIES.values()) {
      if (movie.getType() == movieType) {
        movie.setPrice(price);
      }
    }
    return true;
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
   * Display current ticket prices
   */
  protected static void displayTicketPrices() {
    System.out.println("List of Current Ticket Prices");
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    for (Map.Entry<TypeMovies, Double> currentTicketType : Database.PRICES.entrySet()) {
      System.out.println(String.format("%-25s: %s", currentTicketType.getKey(),
          Helper.df2.format(currentTicketType.getValue())));
    }
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
  }

}
