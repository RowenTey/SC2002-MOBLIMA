package controller;

import java.util.Date;

import database.Database;
import database.FileType;
import model.*;
import model.enums.LayoutType;
import helper.Helper;

public class ShowtimeManager {
  public static void getCurrentList() {
  }

  public static void getUpcomingList() {
  }

  public static boolean createShowtime(Date time, Movie movie, Cinema cinema) {
    int sId = Helper.generateUniqueId(Database.SHOWTIME);
    String showtimeId = String.format("S%04d", sId);
    Showtime newShowtime = new Showtime(showtimeId, time, movie, cinema, LayoutType.MEDIUM);
    Database.SHOWTIME.put(showtimeId, newShowtime);
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
    return true;
  }

  /**
   * Prints the showtime with details
   */
  public static void printShowtime() {
    for (Showtime showtime : Database.SHOWTIME.values()) {
      System.out.println();
      System.out.println("Showtime ID: " + showtime.getShowtimeId());
      System.out.println("Movie: " + showtime.getMovie().getTitle());
      System.out.println("Time: " + showtime.getTime());
    }
  }
}
