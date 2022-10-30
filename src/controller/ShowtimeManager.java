package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;

import database.Database;
import database.FileType;
import model.*;
import model.enums.LayoutType;
import model.enums.ShowStatus;
import helper.Helper;

public class ShowtimeManager {
  /**
   * List of showtime for movies that are NOW_SHOWING
   */
  private static ArrayList<Showtime> showtimeList = new ArrayList<Showtime>();

  /**
   * Total number of showtime
   */
  private static int totalShowtimes;

  /**
   * HashMap to get row number from alphabets
   */
  private static HashMap<Integer, String> alphaRow = new HashMap<Integer, String>();

  /**
   * Constructor of ShowtimeManager
   */
  public ShowtimeManager() {
    ShowtimeManager.showtimeList.clear();
    ShowtimeManager.readShowtime();
    ShowtimeManager.totalShowtimes = showtimeList.size();
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

    Random random = new Random();
    int minDay = (int) LocalDate.of(2022, 10, 18).toEpochDay();
    int maxDay = (int) LocalDate.of(2023, 1, 1).toEpochDay();
    long randomDay = minDay + random.nextInt(maxDay - minDay);
    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
    ZoneId defaultZoneId = ZoneId.systemDefault();

    for (int j = 0; j < newCineplex.size(); j++) {
      for (int i = 0; i < 10; i++) {
        newCinema.add(newCineplex.get(j).getCinemaList().get(i));
      }
    }

    for (int i = 0; i < MovieManager.getTotalNumOfMovie(); i++) {
      randomDay = minDay + random.nextInt(maxDay - minDay);
      randomDate = LocalDate.ofEpochDay(randomDay);
      Date date = Date.from(randomDate.atStartOfDay(defaultZoneId).toInstant());
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      String strDate = dateFormat.format(date);
      newDate.add(strDate);
    }

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
    ArrayList<Showtime> toReturn = new ArrayList<Showtime>();

    for (Showtime showtime : showtimeList) {
      if (showtime.getMovie().getTitle().equals(movie.getTitle())) {
        toReturn.add(showtime);
      }
    }

    return toReturn;
  }

  /**
   * Display showtimes given an array of showtimes
   */
  public static void displayShowtime(ArrayList<Showtime> showtimes, String from) {
    System.out.println("List of showtimes(s) for this " + from + ":");
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
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
    if (movie.getStatus() == ShowStatus.NOW_SHOWING || movie.getStatus() == ShowStatus.PREVIEW) {
      ShowtimeManager.showtimeList.add(newShowtime);
      ShowtimeManager.totalShowtimes += 1;
    }
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
   * Read showtime data that is NOW_SHOWING from database
   */
  public static void readShowtime() {
    for (Showtime showtime : Database.SHOWTIME.values()) {
      if (showtime.getMovie().getStatus() == ShowStatus.NOW_SHOWING) {
        ShowtimeManager.showtimeList.add(showtime);
      }
    }
  }

  /**
   * Allow user to select a specific showtime by index
   */
  public static String selectShowtime(ArrayList<Showtime> showtimes) {
    Showtime selectedShowtime;
    System.out.println("Select a showtime by entering it's index:");
    int choice = Helper.readInt(1, (totalShowtimes + 1));
    selectedShowtime = showtimes.get(choice - 1);
    return selectedShowtime.getShowtimeId();
  }

  /**
   * Prints the showtime with details
   */
  public static void printAllShowtime() {
    for (Showtime showtime : Database.SHOWTIME.values()) {
      System.out.println();
      System.out.println(String.format("%-40s", "").replace(" ", "-"));
      System.out.println(String.format("%-20s: %s", "Showtime ID", showtime.getShowtimeId()));
      System.out.println(String.format("%-20s: %s", "Movie", showtime.getMovie().getTitle()));
      System.out.println(String.format("%-20s: %s", "Time", showtime.getTime()));
      System.out.println(String.format("%-20s: %s", "Cinema Type",
          showtime.getCinema().getIsPlatinum() ? "Platinum" : "Not Platinum"));
      System.out
          .println(String.format("%-20s: %s", "Location", showtime.getCinema().getCineplex().getLocationStr()));
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
    if (from.equals("cineplex")) {
      System.out.println(String.format("%-20s: %s", "Movie", showtime.getMovie().getTitle()));
    }
    System.out.println(String.format("%-20s: %s", "Time", showtime.getTime()));
    System.out.println(
        String.format("%-20s: %s", "Cinema Type", showtime.getCinema().getIsPlatinum() ? "Platinum" : "Not Platinum"));
    System.out.println(String.format("%-20s: %s", "Location", showtime.getCinema().getCineplex().getLocationStr()));
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
        MovieManager.printMovieDetails(movies.get(i));
      }
    }
  }

  /**
   * Get list of showtimes for a specific cineplex
   */
  public static ArrayList<Showtime> getShowtimeByCineplex(Cineplex cineplex) {
    ArrayList<Showtime> toReturn = new ArrayList<Showtime>();

    for (Showtime showtime : showtimeList) {
      if (showtime.getCinema().getCineplex().getLocation() == cineplex.getLocation()) {
        toReturn.add(showtime);
      }
    }

    if (toReturn.size() == 0) {
      return null;
    } else {
      return toReturn;
    }
  }

}
