package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import database.Database;
import database.FileType;
import model.*;
import model.enums.LayoutType;
import helper.Helper;

public class ShowtimeManager {
  /**
   * List of showtime
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
   * Constructor of ShowtimeManager
   */
  public ShowtimeManager() {
    ShowtimeManager.showtimeList.clear();
    ShowtimeManager.readShowtime();
    ShowtimeManager.totalShowtimes = showtimeList.size();
    CinematoCineplexLocation.put("AM", "Amk Hub");
    CinematoCineplexLocation.put("JE", "Jem");
    CinematoCineplexLocation.put("CA", "Causeway Point");
  }

  /**
   * Initializer for cineplex
   */
  public static void initializeShowtime() {
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

    for (int i = 0; i < CineplexManager.getTotalNumOfCineplex(); i++) {
      newCinemaCode.add(newCineplex.get(i).getCinemaList().get(i).getCinemaCode());
      newCinemaCode.add(newCineplex.get(i).getCinemaList().get(i + 1).getCinemaCode());
    }
    for (int i = 0; i < newMovies.size(); i++) {
      randomDay = minDay + random.nextInt(maxDay - minDay);
      randomDate = LocalDate.ofEpochDay(randomDay);
      date = Date.from(randomDate.atStartOfDay(defaultZoneId).toInstant());
      newDate.add(date);
    }
    for (int i = 0; i < newMovies.size(); i++) {
      createShowtime(newDate.get(i), newMovies.get(i), newCinemaCode.get(i));
    }
    ShowtimeManager.printAllShowtime();

  }

  public static void getCurrentList() {
  }

  public static void getUpcomingList() {
  }

  public static ArrayList<Showtime> getMovieShowtime(Movie movie) {
    ArrayList<Showtime> toReturn = new ArrayList<Showtime>();

    for (Showtime showtime : showtimeList) {
      if (showtime.getMovie().getTitle().equals(movie.getTitle())) {
        toReturn.add(showtime);
      }
    }

    return toReturn;
  }

  public static void displayShowtime(ArrayList<Showtime> showtimes) {
    System.out.println("List of showtimes(s) for this movie:");
    for (int i = 0; i < showtimes.size(); i++) {
      System.out.println("Showtime " + "(" + i + 1 + ")");
      printShowtimeDetails(showtimes.get(i));
    }
  }

  public static boolean createShowtime(Date time, Movie movie, String cinemaCode) {
    int sId = Helper.generateUniqueId(Database.SHOWTIME);
    String showtimeId = String.format("S%04d", sId);
    Showtime newShowtime = new Showtime(showtimeId, time, movie, cinemaCode, LayoutType.MEDIUM);
    Database.SHOWTIME.put(showtimeId, newShowtime);
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
    ShowtimeManager.showtimeList.add(newShowtime);
    ShowtimeManager.totalShowtimes += 1;
    return true;
  }

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
   * Read movie data from database
   */
  public static void readShowtime() {
    for (Showtime showtime : Database.SHOWTIME.values()) {
      ShowtimeManager.showtimeList.add(showtime);
    }
  }

  /**
   * Allow user to select a specific showtime by index
   */
  public static String selectShowtime(ArrayList<Showtime> showtimes) {
    Showtime selectedShowtime;
    System.out.println("Select a movie by entering it's index:");
    int choice = Helper.readInt(1, (showtimes.size() + 1));
    selectedShowtime = showtimes.get(choice - 1);
    Helper.pressAnyKeyToContinue();
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
          .println(String.format("%-20s: %s", "Location", CinematoCineplexLocation.get(cinemaCode)));
      System.out.println(String.format("%-40s", "").replace(" ", "-"));
      System.out.println();
    }
  }

  /**
   * Display the cinema layout of current showtime
   */
  public static void displayShowtimeLayout(Showtime showtime) {
    HashMap<Integer, String> alpaRow = new HashMap<Integer, String>();
    alpaRow.put(0, "A");
    alpaRow.put(1, "B");
    alpaRow.put(2, "C");
    alpaRow.put(3, "D");
    alpaRow.put(4, "E");
    alpaRow.put(5, "F");
    alpaRow.put(6, "G");
    alpaRow.put(7, "H");
    alpaRow.put(8, "I");

    System.out.println();
    System.out.println("                    -------Screen------");
    System.out.println("     1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17");
    for (int row = 0; row <= 8; row++) {
      System.out.print(alpaRow.get(row) + "   ");
      for (int col = 0; col <= 16; col++) {
        if (showtime.getSeatAt(row + 1, col + 1) == null)
          System.out.print("   ");
        else {
          if (showtime.getSeatAt(row + 1, col + 1).getBooked()) {
            System.out.println(" X ");
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
   * Prompt user to select a seat
   */
  public static void promptSeatSelection(String showtimeId) {
    Showtime showtimeShowtime = getShowtimebyId(showtimeId);
    displayShowtimeLayout(showtimeShowtime);
    System.out.println("Please enter the desired seat coordinates (e.g A6):");
    String position = Helper.readString();
  }

  /**
   * Print details of showtime
   */
  public static void printShowtimeDetails(Showtime showtime) {
    System.out.println();
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println(String.format("%-20s: %s", "Showtime ID", showtime.getShowtimeId()));
    System.out.println(String.format("%-20s: %s", "Time", showtime.getTime()));
    System.out.println(
        String.format("%-20s: %s", "Location", CinematoCineplexLocation.get(showtime.getCinemaCode().substring(0, 2))));
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println();
  }

  private static Showtime getShowtimebyId(String showtimeId) {
    if (Database.SHOWTIME.containsKey(showtimeId)) {
      return Database.SHOWTIME.get(showtimeId);
    }
    return null;
  }
}
