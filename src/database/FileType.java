package src.database;

import src.model.*;

/**
 * An Enum that corresponds to the different file types that the database will
 * read from and write to.
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-19
 */
public enum FileType {
  /**
   * File type corresponding to the {@link Staff} file.
   */
  USERS("Users"),

  /**
   * File type corresponding to the {@link Booking} file.
   */
  BOOKINGS("Booking"),

  /**
   * File type corresponding to the {@link Cineplex} file.
   */
  CINEPLEX("Cineplex"),

  /**
   * File type corresponding to the {@link Showtime} file.
   */
  SHOWTIME("Showtime"),

  /**
   * File type corresponding to the {@link Movie} file.
   */
  MOVIES("Movies"),

  /**
   * File type corresponding to the movie prices file.
   */
  PRICES("Prices"),

  /**
   * File type corresponding to the Holidays file.
   */
  HOLIDAYS("Holidays"),

  /**
   * File type corresponding to the System file.
   */
  SYSTEM("System");

  /**
   * A String value for the FileType for retrieving purposes.
   */
  public final String fileName;

  /**
   * Constructor for the FileType Enum.
   * 
   * @param fileName File name of the file
   */
  private FileType(String fileName) {
    this.fileName = fileName;
  }
}