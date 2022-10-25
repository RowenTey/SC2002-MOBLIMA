package controller;

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
import view.ShowtimeView;
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
   * HashMap to get cinema location from cinema code
   */
  private static HashMap<String, String> CinematoCineplexLocation = new HashMap<String, String>();

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
    CinematoCineplexLocation.put("AM", "Amk Hub");
    CinematoCineplexLocation.put("JE", "Jem");
    CinematoCineplexLocation.put("CA", "Causeway Point");
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

    ArrayList<Movie> newMovies = MovieManager.getMovieList();
    ArrayList<Cineplex> newCineplex = CineplexManager.getCineplexList();
    ArrayList<String> newCinemaCode = new ArrayList<String>();
    ArrayList<Date> newDate = new ArrayList<Date>();
    Random random = new Random();
    int minDay = (int) LocalDate.of(2022, 10, 18).toEpochDay();
    int maxDay = (int) LocalDate.of(2023, 1, 1).toEpochDay();
    long randomDay = minDay + random.nextInt(maxDay - minDay);
    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
    ZoneId defaultZoneId = ZoneId.systemDefault();
    Date date = Date.from(randomDate.atStartOfDay(defaultZoneId).toInstant());

    for (int i = 0; i < 10; i++) {
      newCinemaCode.add(newCineplex.get(0).getCinemaList().get(i).getCinemaCode());
      newCinemaCode.add(newCineplex.get(1).getCinemaList().get(i).getCinemaCode());
    }

    for (int i = 0; i < MovieManager.getTotalNumOfMovie(); i++) {
      randomDay = minDay + random.nextInt(maxDay - minDay);
      randomDate = LocalDate.ofEpochDay(randomDay);
      date = Date.from(randomDate.atStartOfDay(defaultZoneId).toInstant());
      newDate.add(date);
    }

    for (int i = 0; i < MovieManager.getTotalNumOfMovie(); i++) {
      createShowtime(newDate.get(i), newMovies.get(i), newCinemaCode.get(i));
    }
    ShowtimeManager.printAllShowtime();
  }

  public static void getCurrentList() {
  }

  public static void getUpcomingList() {
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
  public static boolean createShowtime(Date time, Movie movie, String cinemaCode) {
    int sId = Helper.generateUniqueId(Database.SHOWTIME);
    String showtimeId = String.format("S%04d", sId);
    Showtime newShowtime = new Showtime(showtimeId, time, movie, cinemaCode, LayoutType.MEDIUM);
    Database.SHOWTIME.put(showtimeId, newShowtime);
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
    if (movie.getStatus() == ShowStatus.NOW_SHOWING) {
      ShowtimeManager.showtimeList.add(newShowtime);
      ShowtimeManager.totalShowtimes += 1;
    }
    return true;
  }

  /**
   * Action function to run on create showtime
   */
  public static boolean onCreateShowtime() {
    System.out.println("\nWhich movie would you like to creata a showtime for?");

    // movie list not empty
    if (MovieManager.displayListOfMovies()) {
      Movie selectedMovie = MovieManager.selectMovie();
      Date date = Helper.promptDate();
      System.out.println("Which cineplex would you like to air this movie?\n");
      CineplexManager.displayExistingCineplex();
      Cineplex selectedCineplex = CineplexManager.selectCineplex();
      System.out.println("Which ciname in this cineplex would you like to pick?\n");
      String cinemaCode = CineplexManager.selectCinema(selectedCineplex);
      ShowtimeManager.createShowtime(date, selectedMovie, cinemaCode);
      Helper.pressAnyKeyToContinue();
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
    String cinemaCode;

    for (Showtime showtime : Database.SHOWTIME.values()) {
      cinemaCode = showtime.getCinemaCode().substring(0, 2);
      System.out.println();
      System.out.println(String.format("%-40s", "").replace(" ", "-"));
      System.out.println(String.format("%-20s: %s", "Showtime ID", showtime.getShowtimeId()));
      System.out.println(String.format("%-20s: %s", "Movie", showtime.getMovie().getTitle()));
      System.out.println(String.format("%-20s: %s", "Time", showtime.getTime()));
      System.out
          .println(String.format("%-20s: %s", "Location", ShowtimeManager.CinematoCineplexLocation.get(cinemaCode)));
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
   * Prompt user to select a seat for booking
   */
  public static void promptSeatSelection(String showtimeId) {
    String position;
    int row = 3;
    int col = 4;
    Showtime showtime = getShowtimebyId(showtimeId);
    displayShowtimeLayout(showtime);

    do {
      System.out.println("Please enter the desired seat coordinates (e.g A6):");
      position = Helper.readString();
      row = getSeatRow(position);
      col = Integer.parseInt(position.substring(1));
      if (row != 3 && row != 7 && col != 5 && col != 14) {
        break;
      }
      System.out.println("\nInvalid row and column!");
    } while (row == 3 || row == 7 || col == 5 || col == 14);

    System.out.println("Row " + row + " Col " + col);
    if (showtime.getSeatAt(row + 1, col).getBooked()) {
      System.out.println("Booking failed! Seat is occupied");
    } else {
      if (bookSeat(row + 1, col, showtime)) {
        System.out.println("Seat " + position + " booked successfully");
      }
    }

    Helper.pressAnyKeyToContinue();
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
        String.format("%-20s: %s", "Location",
            ShowtimeManager.CinematoCineplexLocation.get(showtime.getCinemaCode().substring(0, 2))));
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println();
  }

  /**
   * Gets a showtime by showtime ID from database
   */
  private static Showtime getShowtimebyId(String showtimeId) {
    if (Database.SHOWTIME.containsKey(showtimeId)) {
      return Database.SHOWTIME.get(showtimeId);
    }
    return null;
  }

  /**
   * Gets the row from position
   */
  private static int getSeatRow(String position) {
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
   * Books the seat at that position for that showtime
   */
  private static boolean bookSeat(int row, int column, Showtime showtime) {
    showtime.getSeatAt(row, column).setBooked(true);
    Database.SHOWTIME.put(showtime.getShowtimeId(), showtime);
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
    return true;
  }

  /**
   * Print showtimes based on Status
   */
  public static void printShowtimeBasedOnStatus(int opt) {
    ArrayList<Movie> movies = MovieManager.getMovieList();
    ShowStatus status = ShowStatus.NOW_SHOWING;
    switch (opt) {
      case 1:
        status = ShowStatus.NOW_SHOWING;
        break;
      case 2:
        status = ShowStatus.PREVIEW;
        break;
      case 3:
        status = ShowStatus.COMING_SOON;
        break;
      case 4:
        status = ShowStatus.END_OF_SHOWING;
        break;
      default:
        break;
    }
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
      if (CinematoCineplexLocation.get(showtime.getCinemaCode().substring(0, 2)).equals(cineplex.getLocation())) {
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
