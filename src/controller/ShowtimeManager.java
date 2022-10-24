package controller;

import java.util.ArrayList;
import java.util.Date;

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

  public static void getCurrentList() {
  }

  public static void getUpcomingList() {
  }

  public static boolean createShowtime(Date time, Movie movie, String cinemaCode) {
    int sId = Helper.generateUniqueId(Database.SHOWTIME);
    String showtimeId = String.format("S%04d", sId);
    Showtime newShowtime = new Showtime(showtimeId, time, movie, cinemaCode, LayoutType.MEDIUM);
    Database.SHOWTIME.put(showtimeId, newShowtime);
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
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
      return true;
    }
    return false;
  }

  /**
   * Prints the showtime with details
   */
  public static void printAllShowtime() {
    for (Showtime showtime : Database.SHOWTIME.values()) {
      System.out.println();
      System.out.println("Showtime ID: " + showtime.getShowtimeId());
      System.out.println("Movie: " + showtime.getMovie().getTitle());
      System.out.println("Time: " + showtime.getTime());
    }
  }

}
