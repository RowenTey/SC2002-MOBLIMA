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
import model.enums.Location;
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
   * HashMap to get cinema location from cinema code
   */
  private static HashMap<String, Location> CinematoCineplexLocation = new HashMap<String, Location>();

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
    CinematoCineplexLocation.put("AM", Location.AMK_HUB);
    CinematoCineplexLocation.put("JE", Location.JEM);
    CinematoCineplexLocation.put("CA", Location.CAUSEWAY_POINT);
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
    ArrayList<String> newCinemaCode = new ArrayList<String>();
    ArrayList<String> newDate = new ArrayList<String>();

    Random random = new Random();
    int minDay = (int) LocalDate.of(2022, 10, 18).toEpochDay();
    int maxDay = (int) LocalDate.of(2023, 1, 1).toEpochDay();
    long randomDay = minDay + random.nextInt(maxDay - minDay);
    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
    ZoneId defaultZoneId = ZoneId.systemDefault();

    for (int i = 0; i < 10; i++) {
      newCinemaCode.add(newCineplex.get(0).getCinemaList().get(i).getCinemaCode());
      newCinemaCode.add(newCineplex.get(1).getCinemaList().get(i).getCinemaCode());
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
      if(newMovies.get(i).getStatus() == ShowStatus.NOW_SHOWING || newMovies.get(i).getStatus() == ShowStatus.PREVIEW){
        createShowtime(newDate.get(i), newMovies.get(i), newCinemaCode.get(i));
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
  public static boolean createShowtime(String time, Movie movie, String cinemaCode) {
    int sId = Helper.generateUniqueId(Database.SHOWTIME);
    String showtimeId = String.format("S%04d", sId);
    Showtime newShowtime = new Showtime(showtimeId, time, movie, cinemaCode, LayoutType.MEDIUM);
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
      System.out.println("Which ciname in this cineplex would you like to pick?\n");
      String cinemaCode = CineplexManager.selectCinema(selectedCineplex);
      ShowtimeManager.createShowtime(date, selectedMovie, cinemaCode);
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
   * Prompt user for booking booking details
   */
  public static void promptBooking(String showtimeId) {
    String position;
    int row = 0;
    int col = 0;
    Showtime showtime = getShowtimebyId(showtimeId);
    Cineplex cineplex = CineplexManager.getCineplexByShowtime(showtime);
    displayShowtimeLayout(showtime);

    do {
      System.out.println("Please enter the desired seat coordinates (e.g A6): ");
      position = Helper.readString();
      row = getSeatRow(position);
      col = Integer.parseInt(position.substring(1));
      if (row != 3 && row != 7 && col != 5 && col != 14) {
        break;
      }
      System.out.println("\nInvalid row and column!");
    } while (row == 3 || row == 7 || col == 5 || col == 14);

    if (showtime.getSeatAt(row + 1, col).getBooked()) {
      System.out.println("Booking failed! Seat is occupied...");
    } else {
      System.out.println("\nSeat " + position + " selected...");
      System.out.println("(1) Confirm Payment");
      System.out.println("(2) Back");
      System.out.print("Which would you like to do: ");

      int pay;
      pay = Helper.readInt(1, 2);
      switch (pay) {
        case 1:
          MovieGoer newMovieGoer = BookingManager.promptUserDetails();
          if (bookSeat(row + 1, col, showtime)) {
            System.out.println("\nSeat " + position + " is booked successfully!");
            System.out.println("Your Ticket will be generated in a short time... ");
          }
          BookingManager.createBooking(showtime.getMovie().getPrice(), showtime.getSeatAt(row + 1, col), cineplex,
              newMovieGoer, position, showtime.getMovie().getTitle());
          break;
        case 2:
          System.out.println("Booking failed!");
          break;
        default:
          break;
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
   * Books the seat at that position for that showtime
   */
  protected static boolean bookSeat(int row, int column, Showtime showtime) {
    showtime.getSeatAt(row, column).setBooked(true);
    Database.SHOWTIME.put(showtime.getShowtimeId(), showtime);
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
    return true;
  }

  /**
   * Print showtimes based on Status
   */
  public static void printShowtimeBasedOnStatus(ShowStatus status) {
    ArrayList<Movie> movies = MovieManager.getAllMovieList();
    for (int i = 0; i < movies.size(); i++) {
      if (movies.get(i).getStatus() == status) {
        System.out.println(i);
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
      if (CinematoCineplexLocation.get(showtime.getCinemaCode().substring(0, 2)) == cineplex.getLocation()) {
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
