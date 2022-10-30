package controller;

import java.util.ArrayList;
import java.util.Map;

import database.Database;
import database.FileType;
import helper.Helper;
import model.Movie;
import model.enums.TypeMovies;

public class SystemManager {
  /**
   * Adds a holiday to database
   */
  public static void addHoliday() {
    String date;
    do {
      date = Helper.setDate(false, true);
    } while (date.equals(""));
    Database.HOLIDAYS.add(date);
    System.out.println("Holiday on " + date + " added!");
  }

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

  private static void displayTicketPrices() {
    System.out.println("List of Current Ticket Prices");
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    for (Map.Entry<TypeMovies, Double> currentTicketType : Database.PRICES.entrySet()) {
      System.out.println(String.format("%-25s: %s", currentTicketType.getKey(),
          Helper.df.format(currentTicketType.getValue())));
    }
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
  }

  private static ArrayList<TypeMovies> getTicketTypes() {
    ArrayList<TypeMovies> ticketTypes = new ArrayList<TypeMovies>();
    for (TypeMovies typeMovies : Database.PRICES.keySet()) {
      ticketTypes.add(typeMovies);
    }
    return ticketTypes;
  }

  private static boolean updateMoviePrices(TypeMovies movieType, double price) {
    for (Movie movie : Database.MOVIES.values()) {
      if (movie.getType() == movieType) {
        movie.setPrice(price);
      }
    }
    return true;
  }

}