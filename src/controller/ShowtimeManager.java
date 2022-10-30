package controller;

import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import database.Database;
import database.FileType;
import model.*;
import model.enums.LayoutType;
import model.enums.ShowStatus;
import helper.Helper;

public class ShowtimeManager {
  /*
   * HashMap to get row number from alphabets
   */
  private static HashMap<Integer, String> alphaRow = new HashMap<Integer, String>();

  /**
   * Constructor of ShowtimeManager
   */
  public ShowtimeManager() {
    ShowtimeManager.initializeHashMap();
  }

  /**
   * Initialise HashMap
   */
  public static void initializeHashMap() {
    alphaRow.put(0, "A");
    alphaRow.put(1, "B");
    alphaRow.put(2, "C");
    alphaRow.put(3, "D");
    alphaRow.put(4, "E");
    alphaRow.put(5, "F");
    alphaRow.put(6, "G");
    alphaRow.put(7, "H");
    alphaRow.put(8, "I");
  }

  /**
   * Initializer for showtime
   */
  public static void initializeShowtime() {
    ShowtimeManager.initializeHashMap();

    ArrayList<Movie> newMovies = MovieManager.getAllMovieList();
    ArrayList<Cineplex> newCineplex = CineplexManager.getCineplexList();
    ArrayList<Cinema> newCinema = new ArrayList<Cinema>();
    ArrayList<String> newDate = new ArrayList<String>();

    String strDate;

    for (int j = 0; j < newCineplex.size(); j++) {
      for (int i = 0; i < 10; i++) {
        newCinema.add(newCineplex.get(j).getCinemaList().get(i));
      }
    }

    for (int i = 0; i < MovieManager.getTotalNumOfMovie(); i++) {
      strDate = Helper.generateRandomDate();
      newDate.add(strDate);
    }
    Collections.shuffle(newCinema);

    for (int i = 0; i < MovieManager.getTotalNumOfMovie(); i++) {
      if (newMovies.get(i).getStatus() == ShowStatus.NOW_SHOWING
          || newMovies.get(i).getStatus() == ShowStatus.PREVIEW) {
        createShowtime(newDate.get(i), newMovies.get(i), newCinema.get(i));
      }
    }

    ShowtimeManager.printAllShowtime();
  }

  /**
   * Get all showtimes for this movie
   */
  public static ArrayList<Showtime> getMovieShowtime(Movie movie) {
    ArrayList<Showtime> showtimes = new ArrayList<Showtime>();

    for (Showtime showtime : Database.SHOWTIME.values()) {
      if (showtime.getMovie().getTitle().equals(movie.getTitle())) {
        showtimes.add(showtime);
      }
    }

    return showtimes;
  }

  /**
   * Display showtimes given an array of showtimes
   */
  public static void displayShowtime(ArrayList<Showtime> showtimes, String from) {
    if (showtimes.size() == 0) {
      System.out.println("No showtimes found...");
    }

    if (from.equals("")) {
      System.out.println("List of showtimes(s):");
    } else {
      System.out.println("List of showtimes(s) for this " + from + ":");
    }

    for (int i = 0; i < showtimes.size(); i++) {
      System.out.println("\nShowtime " + "(" + (i + 1) + ")");
      printShowtimeDetails(showtimes.get(i), from);
    }
  }

  /**
   * Create a showtime and store in database
   */
  public static boolean createShowtime(String time, Movie movie, Cinema cinema) {
    int sId = Helper.generateUniqueId(Database.SHOWTIME);
    String showtimeId = String.format("S%04d", sId);
    Showtime newShowtime = new Showtime(showtimeId, time, movie, cinema, LayoutType.MEDIUM);
    Database.SHOWTIME.put(showtimeId, newShowtime);
    Database.numOfShowtimes++;
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
    return true;
  }

  /**
   * Action function to run on create showtime
   */
  public static boolean onCreateShowtime() {
    System.out.println("Which movie would you like to create a showtime for?\n");

    // movie list not empty
    if (MovieManager.displayListOfBookableMovies()) {
      Movie selectedMovie = MovieManager.selectMovie();
      String date;
      do {
        date = Helper.setDate(false, false);
      } while (date.equals(""));
      System.out.println(date);
      System.out.println("\nWhich cineplex would you like to air this movie?\n");
      CineplexManager.displayExistingCineplex();
      Cineplex selectedCineplex = CineplexManager.selectCineplex();
      System.out.println("Which cinema in this cineplex would you like to pick?\n");
      Cinema cinema = CineplexManager.selectCinema(selectedCineplex);
      ShowtimeManager.createShowtime(date, selectedMovie, cinema);
      return true;
    }

    return false;
  }

  /**
   * Get showtime data that is NOW_SHOWING from database
   */
  public static ArrayList<Showtime> getShowtime(String type) {
    ArrayList<Showtime> showtimes = new ArrayList<Showtime>();

    for (Showtime showtime : Database.SHOWTIME.values()) {
      if (type.equals("all")) {
        showtimes.add(showtime);
      } else if (type.equals("bookable") && (showtime.getMovie().getStatus() == ShowStatus.NOW_SHOWING
          || showtime.getMovie().getStatus() == ShowStatus.PREVIEW)) {
        showtimes.add(showtime);
      }
    }

    return showtimes;
  }

  /**
   * Allow user to select a specific showtime by index
   */
  public static String selectShowtime(ArrayList<Showtime> showtimes) {
    Showtime selectedShowtime;
    System.out.println("Select a showtime by entering it's index:");
    int choice = Helper.readInt(1, (showtimes.size() + 1));
    selectedShowtime = showtimes.get(choice - 1);
    return selectedShowtime.getShowtimeId();
  }

  /**
   * Prints the showtime with details
   */
  public static void printAllShowtime() {
    if (Database.numOfShowtimes == 0) {
      System.out.println("No showtimes found...\n");
      return;
    }

    for (Showtime showtime : Database.SHOWTIME.values()) {
      System.out.println();
      System.out.println(String.format("%-40s", "").replace(" ", "-"));
      System.out.println(String.format("%-20s: %s", "Showtime ID", showtime.getShowtimeId()));
      System.out.println(String.format("%-20s: %s", "Movie", showtime.getMovie().getTitle()));
      System.out.println(String.format("%-20s: %s", "Time", showtime.getTime()));
      System.out.println(String.format("%-20s: %s", "Cinema Type",
          showtime.getCinema().getIsPlatinum() ? "Platinum" : "Normal"));
      System.out
          .println(String.format("%-20s: %s", "Location", showtime.getCinema().getCineplex()));
      System.out.println(String.format("%-40s", "").replace(" ", "-"));
      System.out.println();
    }
  }

  /**
   * Display the cinema layout of current showtime
   */
  public static void displayShowtimeLayout(Showtime showtime) {
    System.out.println();
    System.out.println("                      -------Screen------");
    System.out.println("     1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17");
    for (int row = 0; row <= 8; row++) {
      System.out.print(alphaRow.get(row) + "   ");
      for (int col = 0; col <= 16; col++) {
        if (showtime.getSeatAt(row + 1, col + 1) == null)
          System.out.print("   ");
        else {
          if (showtime.getSeatAt(row + 1, col + 1).getBooked()) {
            System.out.print(" X ");
          } else {
            System.out.print(" O ");
          }
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  /**
   * Print details of showtime
   */
  public static void printShowtimeDetails(Showtime showtime, String from) {
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println(String.format("%-20s: %s", "Showtime ID", showtime.getShowtimeId()));
    if (from.equals("cineplex") || from.equals("")) {
      System.out.println(String.format("%-20s: %s", "Movie", showtime.getMovie().getTitle()));
    }
    System.out.println(String.format("%-20s: %s", "Time", showtime.getTime()));
    System.out.println(
        String.format("%-20s: %s", "Cinema Type", showtime.getCinema().getIsPlatinum() ? "Platinum" : "Normal"));
    System.out.println(String.format("%-20s: %s", "Location", showtime.getCinema().getCineplex()));
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println();
  }

  /**
   * Gets a showtime by showtime ID from database
   */
  protected static Showtime getShowtimebyId(String showtimeId) {
    if (Database.SHOWTIME.containsKey(showtimeId)) {
      return Database.SHOWTIME.get(showtimeId);
    }
    return null;
  }

  /**
   * Gets the row from position
   */
  protected static int getSeatRow(String position) {
    String rowStr = position.substring(0, 1);
    int row = 0;

    for (Entry<Integer, String> entry : alphaRow.entrySet()) {
      if (entry.getValue().equals(rowStr)) {
        row = entry.getKey();
      }
    }

    return row;
  }

  /**
   * Print showtimes based on Status
   */
  public static void printShowtimeBasedOnStatus(ShowStatus status) {
    ArrayList<Movie> movies = MovieManager.getAllMovieList();
    for (int i = 0; i < movies.size(); i++) {
      if (movies.get(i).getStatus() == status) {
        MovieManager.displayMovieDetails(movies.get(i));
      }
    }
  }

  /**
   * Get list of showtimes for a specific cineplex
   */
  public static ArrayList<Showtime> getShowtimeByCineplex(Cineplex cineplex) {
    ArrayList<Showtime> toReturn = new ArrayList<Showtime>();

    for (Showtime showtime : Database.SHOWTIME.values()) {
      if (showtime.getCinema().getCineplex() == cineplex.getLocation()) {
        toReturn.add(showtime);
      }
    }

    if (toReturn.size() == 0) {
      return null;
    } else {
      return toReturn;
    }
  }

  /**
   * Remove a showtime
   */
  public static void removeShowtime() {
    int opt = -1;
    ArrayList<Showtime> showtimes = getShowtime("all");
    if (showtimes.size() == 0) {
      System.out.println("No showtimes found...");
    } else {
      displayShowtime(showtimes, "");
      System.out.println("(" + (showtimes.size() + 1) + ") Exit");
      System.out.println("\nWhich showtime do you want to remove ?");
      opt = Helper.readInt(1, showtimes.size() + 1);
      if (opt != showtimes.size() + 1) {
        Showtime showtime = showtimes.get(opt - 1);
        Database.SHOWTIME.remove(showtime.getShowtimeId());
        Database.numOfShowtimes--;
        Database.saveFileIntoDatabase(FileType.SHOWTIME);
        System.out.println("\nRemoved showtime!");
      }
    }
  }

  /**
   * Remove all showtime related to movie
   */
  public static void removeShowtimeByMovie(Movie movie) {
    HashMap<String, Showtime> updatedShowtime = new HashMap<String, Showtime>();

    for (Showtime showtime : Database.SHOWTIME.values()) {
      if (!showtime.getMovie().getTitle().equals(movie.getTitle())) {
        updatedShowtime.put(showtime.getShowtimeId(), showtime);
      }
    }

    Database.SHOWTIME = updatedShowtime;
    Database.numOfShowtimes = updatedShowtime.size();
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
  }

  /**
   * Remove all showtime related to cineplex
   */
  public static void removeShowtimeByCineplex(Cineplex cineplex) {
    HashMap<String, Showtime> updatedShowtime = new HashMap<String, Showtime>();

    for (Showtime showtime : Database.SHOWTIME.values()) {
      if (showtime.getCinema().getCineplex() != cineplex.getLocation()) {
        updatedShowtime.put(showtime.getShowtimeId(), showtime);
      }
    }

    Database.SHOWTIME = updatedShowtime;
    Database.numOfShowtimes = updatedShowtime.size();
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
  }

  /**
   * Update a showtime
   */
  public static void updateShowtime() {
    int opt = -1;
    if (ShowtimeManager.getTotalNumOfShowtime() == 0) {
        System.out.println("No showtimes found!");
    } else {
        System.out.println("Which showtime do you want to update ?");
        ShowtimeManager.displayShowtime(ShowtimeManager.getShowtime("all"),"");
        opt = Helper.readInt(1, ShowtimeManager.getTotalNumOfShowtime() + 1);
        if (opt != ShowtimeManager.getTotalNumOfShowtime() + 1) {
            Showtime showtime = ShowtimeManager.getShowtime("all").get(opt - 1);
            String showtimeId = showtime.getShowtimeId();
            CineplexManager.displayExistingCineplex();
            System.out.println("\nUpdate Cineplex to: ");
            Cineplex newCineplex = CineplexManager.selectCineplex();
            System.out.println("Cineplex selected: " + newCineplex.getLocationStr());
            System.out.println("Update Cinema to: ");
            Cinema newCinema = CineplexManager.selectCinema(newCineplex);
            System.out.println("Cinema selected: " + newCinema.getCinemaCode());
            showtime.setCinema(newCinema);
            Database.SHOWTIME.remove(showtime.getShowtimeId());
            Database.SHOWTIME.put(showtimeId, showtime);
            Database.saveFileIntoDatabase(FileType.MOVIES);
            System.out.println("Showtime successfully updated!");
        }
    }
  }

}
