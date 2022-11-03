package src.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import src.database.Database;
import src.database.FileType;
import src.model.*;
import src.model.enums.LayoutType;
import src.model.enums.ShowStatus;
import src.helper.Helper;

/**
 * ShowtimeManager is a controller class that acts as a "middleman" between the
 * view
 * classes - CineplexAppView and ShowtimeView and the model class -
 * {@link Showtime}.
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-31
 */
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
   * Initialize {@link Database} with {@link Showtime}
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

    for (int i = 0; i < MovieManager.getTotalNumOfMovie() * 2; i++) {
      strDate = Helper.generateRandomDate();
      newDate.add(strDate);
    }

    Collections.shuffle(newCinema);

    for (int i = 0; i < MovieManager.getTotalNumOfMovie(); i++) {
      if (newMovies.get(i).getStatus() == ShowStatus.NOW_SHOWING
          || newMovies.get(i).getStatus() == ShowStatus.PREVIEW) {
        createShowtime(newDate.get(i), newMovies.get(i), newCinema.get(i));
        createShowtime(newDate.get(2 * i), newMovies.get(i), newCinema.get(i));
      }
    }

    ShowtimeManager.displayAllShowtime();
  }

  /**
   * Get the list of {@link Showtime} for a {@link Movie}
   * 
   * @param movie object to retrieve showtimes of
   * @return {@code ArrayList<Showtime>} of {@link Showtime}
   */
  private static ArrayList<Showtime> getMovieShowtime(Movie movie) {
    ArrayList<Showtime> showtimes = new ArrayList<Showtime>();

    for (Showtime showtime : Database.SHOWTIME.values()) {
      if (showtime.getMovie().getTitle().equals(movie.getTitle())) {
        showtimes.add(showtime);
      }
    }

    return showtimes;
  }

  /**
   * Display list of {@link Showtime} for different routes
   * 
   * @param showtimes list of showtimes to display
   * @param from      route it came from ({@code ""} indicates it should display
   *                  all
   *                  showtime)
   */
  protected static void displayShowtime(ArrayList<Showtime> showtimes, String from) {
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
      displayShowtimeDetails(showtimes.get(i), from);
    }
  }

  /**
   * Create a {@link Showtime} for a {@link Movie} in a specific {@link Cinema}
   * 
   * @param time   of showtime
   * @param movie  to be showed
   * @param cinema of showtime
   * @return boolean {@code true} if showtime was created, {@code false}
   *         otherwise
   */
  private static boolean createShowtime(String time, Movie movie, Cinema cinema) {
    int sId = Helper.generateUniqueId(Database.SHOWTIME);
    String showtimeId = String.format("S%04d", sId);
    Showtime newShowtime = new Showtime(showtimeId, time, movie, cinema, LayoutType.MEDIUM);
    Database.SHOWTIME.put(showtimeId, newShowtime);
    Database.numOfShowtimes++;
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
    return true;
  }

  /**
   * Action function to run on create {@link Showtime}
   * 
   * @return boolean {@code true} if showtime was created, {@code false}
   *         otherwise
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
   * Get {@link Showtime} according to specified type
   * 
   * @param type {@code "all"} to get all showtimes, {@code "bookable"} to get
   *             showtimes that are bookable by {@link MovieGoer}
   * @return {@code ArrayList<Showtime>} of {@link Showtime}
   */
  protected static ArrayList<Showtime> getShowtime(String type) {
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
   * Allow user to select a specific {@link Showtime}
   * 
   * @param showtimes list of showtimes to select from
   * @return {@code showtimeId} of selected showtime
   */
  private static String selectShowtime(ArrayList<Showtime> showtimes) {
    Showtime selectedShowtime;
    System.out.println("Select a showtime by entering it's index:");
    int choice = Helper.readInt(1, (showtimes.size() + 1));
    selectedShowtime = showtimes.get(choice - 1);
    return selectedShowtime.getShowtimeId();
  }

  /**
   * Display all {@link Showtime} in {@link Database}
   */
  public static void displayAllShowtime() {
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
   * Display the layout of the specified {@link Showtime}
   * 
   * @param showtime to be displayed
   * @param seat that is already selected
   */
  protected static void displayShowtimeLayout(Showtime showtime, ArrayList<Seat> seat) {
    System.out.println();
    System.out.println("                                       -------Screen------");
    System.out.println("      1    2    3    4   5   6    7    8    9    10   11   12   13  14  15   16   17");
    for (int row = 0; row <= 8; row++) {
      System.out.print(alphaRow.get(row) + "   ");
      for (int col = 0; col <= 16; col++) {
        if (showtime.getSeatAt(row + 1, col + 1) == null)
          System.out.print("   ");
        else {
          if (showtime.getSeatAt(row + 1, col + 1).getBooked()) {
            System.out.print(" [X] ");
          } else {
            if(seat.contains(showtime.getSeatAt(row + 1, col + 1))){
              System.out.print(" [O] ");
            }else{
              System.out.print(" [ ] ");
            }
          }
        }
      }
      System.out.println();
    }
    System.out.println();
    System.out.println("Note:");
    System.out.println("I1-I4 >> Couple Seat (I1 & I2 must be booked together, I3 & I4 must be booked together)");
    System.out.println("I6-I13 >> Elite Seat");
    System.out.println("I15-I17 >> Ultima Seat");
    System.out.println();
    System.out.println("[X] >> Booked Seat");
    System.out.println("[O] >> Selected Seat");
    System.out.println("[ ] >> Empty Seat");
    System.out.println();
  }

  /**
   * Displays the details of the {@link Showtime}
   * 
   * @param showtime to be displayed
   * @param from     route it came from ({@code ""} or {@code "cineplex"}
   *                 indicates it should display
   *                 all
   *                 details)
   */
  public static void displayShowtimeDetails(Showtime showtime, String from) {
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
   * Get a {@link Showtime} by ID from {@link Database}
   * 
   * @param showtimeId of showtime to be retrieved
   * @return {@link Showtime} object corresponding to that ID
   */
  protected static Showtime getShowtimebyId(String showtimeId) {
    if (Database.SHOWTIME.containsKey(showtimeId)) {
      return Database.SHOWTIME.get(showtimeId);
    }
    return null;
  }

  /**
   * Get {@link Seat} row number from alphabets
   * 
   * @param position of seat
   * @return int row number of seat
   */
  protected static int getSeatRow(String position) {
    String rowStr = position.substring(0, 1);
    int row = -1;

    for (Entry<Integer, String> entry : alphaRow.entrySet()) {
      if (entry.getValue().equals(rowStr)) {
        row = entry.getKey();
      }
    }

    return row;
  }

  /**
   * Get the list of {@link Showtime} for a {@link Cineplex}
   * 
   * @param cineplex object to retrieve showtimes of
   * @return {@code ArrayList<Showtime>} of {@link Showtime}
   */
  public static ArrayList<Showtime> getCineplexShowtime(Cineplex cineplex) {
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
   * Remove a {@link Showtime}
   * 
   * @return boolean {@code true} if showtime was removed, {@code false}
   *         otherwise
   */
  public static boolean removeShowtime() {
    int opt = -1;
    ArrayList<Showtime> showtimes = getShowtime("all");
    if (showtimes.size() == 0) {
      System.out.println("No showtimes found...");
      return false;
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
        return true;
      }
    }

    return false;

  }

  /**
   * Update a {@link Showtime}
   * 
   * @return boolean {@code true} if showtime was updated, {@code false}
   *         otherwise
   */
  public static boolean updateShowtime() {
    int opt = -1;
    if (ShowtimeManager.getShowtime("all").size() == 0) {
      System.out.println("No showtimes found!");
      return false;
    } else {
      System.out.println("Which showtime do you want to update ?");
      ShowtimeManager.displayShowtime(ShowtimeManager.getShowtime("all"), "");
      opt = Helper.readInt(1, ShowtimeManager.getShowtime("all").size() + 1);
      if (opt != ShowtimeManager.getShowtime("all").size() + 1) {
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
        return true;
      }
    }

    return false;
  }

  /**
   * Remove all {@link Showtime} related to a {@link Movie} that is removed
   * 
   * @param movie that was removed
   */
  protected static void removeShowtimeByMovie(Movie movie) {
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
   * Remove all {@link Showtime} related to a {@link Cineplex} that is removed
   * 
   * @param cineplex that was removed
   */
  protected static void removeShowtimeByCineplex(Cineplex cineplex) {
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
   * Action function to handle {@link Showtime} selection from a specific
   * {@link Movie}
   * 
   * @param movie    to select showtime from
   * @param username of current user
   */
  public static void handleShowtimeSelection(Movie movie, String username) {
    ArrayList<Showtime> movieShowtimes = ShowtimeManager.getMovieShowtime(movie);
    if (movieShowtimes.size() == 0 || movieShowtimes == null) {
      System.out.println("No showtimes available for this movie...");
      Helper.pressAnyKeyToContinue();
      return;
    } else {
      ShowtimeManager.displayShowtime(movieShowtimes, "movie");
    }
    String showtimeId = ShowtimeManager.selectShowtime(movieShowtimes);
    BookingManager.promptBooking(showtimeId, username);
  }

  /**
   * Action function to handle {@link Showtime} selection from a specific
   * {@link Cineplex}
   * 
   * @param cineplex to select showtime from
   * @param username of current user
   */
  public static void handleShowtimeSelection(Cineplex cineplex, String username) {
    ArrayList<Showtime> movieShowtimes = ShowtimeManager.getCineplexShowtime(cineplex);
    if (movieShowtimes.size() == 0 || movieShowtimes == null) {
      System.out.println("No showtimes available for this cineplex...");
      Helper.pressAnyKeyToContinue();
    } else {
      ShowtimeManager.displayShowtime(movieShowtimes, "cineplex");
    }
    String showtimeId = ShowtimeManager.selectShowtime(movieShowtimes);
    BookingManager.promptBooking(showtimeId, username);
  }

}
