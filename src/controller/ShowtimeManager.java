package controller;

import java.util.Date;
import java.util.HashMap;

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

  public static boolean createShowtime(Date time, Movie movie, String cinemaCode) {
    int sId = Helper.generateUniqueId(Database.SHOWTIME);
    String showtimeId = String.format("S%04d", sId);
    Showtime newShowtime = new Showtime(showtimeId, time, movie, cinemaCode, LayoutType.MEDIUM);
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

  /**
   * Display the cinema layout of current showtime
   */
  public static void displayShowtimeLayout(Showtime showtime){
    HashMap<Integer, String> alpaRow = new HashMap<Integer, String>();
    alpaRow.put(0,"A");
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
                if (showtime.getSeatAt(row+1, col+1) == null) System.out.print("   ");
                else {
                  if(showtime.getSeatAt(row+1, col+1).getBooked()){
                    System.out.println(" X ");
                  }else{
                    System.out.print(" O ");
                  }
                }
            }
            System.out.println();
        }
        System.out.println();
  }
}
