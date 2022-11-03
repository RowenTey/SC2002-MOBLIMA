package src.database;

import java.util.HashMap;
import java.util.HashSet;

import src.controller.CineplexManager;
import src.controller.MovieManager;
import src.controller.ShowtimeManager;
import src.controller.UserManager;
import src.controller.SystemManager;

import src.model.*;
import src.model.enums.MoviesType;

import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Database class to read and write serialized data into .dat files.
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-18
 */
public class Database {
  /**
   * The folder name to contain .dat files.
   */
  private static final String folder = "data";

  /**
   * HashMap to contain {@link User} objects.
   */
  public static HashMap<String, User> USERS = new HashMap<String, User>();

  /**
   * HashMap to contain {@link Booking} objects.
   */
  public static HashMap<String, Booking> BOOKINGS = new HashMap<String, Booking>();

  /**
   * HashMap to contain {@link Cineplex} objects.
   */
  public static HashMap<String, Cineplex> CINEPLEX = new HashMap<String, Cineplex>();

  /**
   * HashMap to contain {@link Showtime} objects.
   */
  public static HashMap<String, Showtime> SHOWTIME = new HashMap<String, Showtime>();

  /**
   * HashMap to contain {@link Movie} objects.
   */
  public static HashMap<String, Movie> MOVIES = new HashMap<String, Movie>();

  /**
   * HashMap to contain movie prices according to type.
   */
  public static HashMap<MoviesType, Double> PRICES = new HashMap<MoviesType, Double>();

  /**
   * HashSet to contain dates of holidays
   */
  public static HashSet<String> HOLIDAYS = new HashSet<String>();

  /**
   * A bit to contain system settings
   */
  public static String SYSTEM = new String();

  /**
   * Number of movies in database
   */
  public static int numOfMovies = 0;

  /**
   * Number of showtimes in database
   */
  public static int numOfShowtimes = 0;

  /**
   * Constructor that reads all the data from the data file during initialization
   * of program.
   */
  public Database() {
    if (!readSerializedObject(FileType.USERS)) {
      System.out.println("Read into Users1 failed!");
    }
    if (!readSerializedObject(FileType.BOOKINGS)) {
      System.out.println("Read into Bookings failed!");
    }
    if (!readSerializedObject(FileType.CINEPLEX)) {
      System.out.println("Read into Cineplex failed!");
    }
    if (!readSerializedObject(FileType.SHOWTIME)) {
      System.out.println("Read into Showtime failed!");
    }
    if (!readSerializedObject(FileType.MOVIES)) {
      System.out.println("Read into Movies failed!");
    }
    if (!readSerializedObject(FileType.PRICES)) {
      System.out.println("Read into Prices failed!");
    }
    if (!readSerializedObject(FileType.HOLIDAYS)) {
      System.out.println("Read into Holidays failed!");
    }
    if (!readSerializedObject(FileType.SYSTEM)) {
      System.out.println("Read into System failed!");
    }
  }

  /**
   * Save a particular {@link FileType} into database.
   * 
   * @param fileType file type to be saved.
   * @see FileType for the different type of filetypes.
   */
  public static void saveFileIntoDatabase(FileType fileType) {
    writeSerializedObject(fileType);
  }

  /**
   * Save all files into database.
   */
  public static void saveAllFiles() {
    saveFileIntoDatabase(FileType.USERS);
    saveFileIntoDatabase(FileType.BOOKINGS);
    saveFileIntoDatabase(FileType.CINEPLEX);
    saveFileIntoDatabase(FileType.SHOWTIME);
    saveFileIntoDatabase(FileType.MOVIES);
    saveFileIntoDatabase(FileType.PRICES);
    saveFileIntoDatabase(FileType.HOLIDAYS);
    saveFileIntoDatabase(FileType.SYSTEM);
  }

  /**
   * Read serialized object from a particular {@link FileType}.
   * 
   * @param fileType file type to be read.
   * @return {@code true} if read from file is successful. Otherwise,
   *         {@code false}.
   */
  @SuppressWarnings("unchecked")
  private static boolean readSerializedObject(FileType fileType) {
    String fileExtension = ".dat";
    String filePath = "./src/database/" + folder + "/" + fileType.fileName + fileExtension;
    try {
      FileInputStream fileInputStream = new FileInputStream(filePath);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      Object object = objectInputStream.readObject();
      if (!(object instanceof HashMap) && !(object instanceof HashSet) && !(object instanceof String)) {
        System.out.println(fileType.fileName);
        objectInputStream.close();
        return false;
      }

      // Read into database
      if (fileType == FileType.USERS) {
        USERS = (HashMap<String, User>) object;
      } else if (fileType == FileType.BOOKINGS) {
        BOOKINGS = (HashMap<String, Booking>) object;
      } else if (fileType == FileType.CINEPLEX) {
        CINEPLEX = (HashMap<String, Cineplex>) object;
      } else if (fileType == FileType.SHOWTIME) {
        SHOWTIME = (HashMap<String, Showtime>) object;
        numOfShowtimes = SHOWTIME.size();
      } else if (fileType == FileType.MOVIES) {
        MOVIES = (HashMap<String, Movie>) object;
        numOfMovies = MOVIES.size();
      } else if (fileType == FileType.PRICES) {
        PRICES = (HashMap<MoviesType, Double>) object;
      } else if (fileType == FileType.HOLIDAYS) {
        HOLIDAYS = (HashSet<String>) object;
      } else if (fileType == FileType.SYSTEM) {
        SYSTEM = (String) object;
      }

      objectInputStream.close();
      fileInputStream.close();
    } catch (EOFException err) {
      System.out.println("Warning: " + err);
      if (fileType == FileType.USERS) {
        USERS = new HashMap<String, User>();
        Database.initializeStaff();
      }
    } catch (IOException err) {
      err.printStackTrace();
      return false;
    } catch (ClassNotFoundException err) {
      err.printStackTrace();
      return false;
    } catch (Exception err) {
      System.out.println("Error: " + err.getMessage());
      return false;
    }
    return true;
  }

  /**
   * Write serialized object to file
   * 
   * @param fileType file type to write into.
   * @return {@code true} if write to file is successful. Otherwise,
   *         {@code false}.
   */
  private static boolean writeSerializedObject(FileType fileType) {
    String fileExtension = ".dat";
    String filePath = "./src/database/" + folder + "/" + fileType.fileName + fileExtension;
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(filePath);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      if (fileType == FileType.USERS) {
        objectOutputStream.writeObject(USERS);
      } else if (fileType == FileType.BOOKINGS) {
        objectOutputStream.writeObject(BOOKINGS);
      } else if (fileType == FileType.CINEPLEX) {
        objectOutputStream.writeObject(CINEPLEX);
      } else if (fileType == FileType.SHOWTIME) {
        objectOutputStream.writeObject(SHOWTIME);
      } else if (fileType == FileType.MOVIES) {
        objectOutputStream.writeObject(MOVIES);
      } else if (fileType == FileType.PRICES) {
        objectOutputStream.writeObject(PRICES);
      } else if (fileType == FileType.HOLIDAYS) {
        objectOutputStream.writeObject(HOLIDAYS);
      } else if (fileType == FileType.SYSTEM) {
        objectOutputStream.writeObject(SYSTEM);
      }

      objectOutputStream.close();
      fileOutputStream.close();
      return true;
    } catch (Exception err) {
      System.out.println("Error: " + err.getMessage());
      return false;
    }
  }

  /**
   * A method to initialize {@link Staff} data when the database is empty.
   * <p>
   * Calls {@link UserManager} to initialize the dummy guests.
   * 
   * @return {@code true} if initialized successfully. Otherwise, {@code false} if
   *         database is not empty.
   */
  public static boolean initializeStaff() {
    if (USERS.size() != 0) {
      System.out.println("The database already has staffs. Reset database first to initialize staffs");
      return false;
    }
    UserManager.initializeStaff();
    return true;
  }

  /**
   * A method to initialize {@link Cineplex} data when the database is empty.
   * <p>
   * Calls {@link CineplexManager} to initialize the cineplex.
   * 
   * @return {@code true} if initialized successfully. Otherwise, {@code false} if
   *         database is not empty.
   */
  public static boolean initializeCineplex() {
    if (CINEPLEX.size() != 0) {
      System.out.println("The database already has cineplexes. Reset database first to initialize Cineplex");
      return false;
    }
    CineplexManager.initializeCineplex();
    return true;
  }

  /**
   * A method to initialize {@link Movie} data when the database is empty.
   * <p>
   * Calls {@link MovieManager} to initialize the cineplex.
   * 
   * @return {@code true} if initialized successfully. Otherwise, {@code false} if
   *         database is not empty.
   */
  public static boolean initializeMovies() {
    if (MOVIES.size() != 0) {
      System.out.println("The database already has movies. Reset database first to initialize Movies");
      return false;
    }
    MovieManager.initializeMovies();
    numOfMovies = MOVIES.size();
    return true;
  }

  /**
   * A method to initialize {@link Showtime} data when the database is empty.
   * <p>
   * Calls {@link ShowtimeManager} to initialize the cineplex.
   * 
   * @return {@code true} if initialized successfully. Otherwise, {@code false} if
   *         database is not empty.
   */
  public static boolean initializeShowtime() {
    if (SHOWTIME.size() != 0) {
      System.out.println("The database already has showtimes. Reset database first to initialize Showtimes");
      return false;
    }
    ShowtimeManager.initializeShowtime();
    numOfShowtimes = SHOWTIME.size();
    return true;
  }

  /**
   * A method to initialize {@code Holiday} data when the database is empty.
   * <p>
   * Calls {@link SystemManager} to initialize the cineplex.
   * 
   * @return {@code true} if initialized successfully. Otherwise, {@code false} if
   *         database is not empty.
   */
  public static boolean initializeHoliday() {
    if (HOLIDAYS.size() != 0) {
      System.out.println("The database already has holidays. Reset database first to initialize Holidays");
      return false;
    }
    SystemManager.initializeHolidays();
    return true;
  }

  /**
   * A method to clear out all the data in database.
   * 
   * @return {@code true} if data is cleared successfully.
   */
  public static boolean clearDatabase() {
    // Initialize staff data
    USERS = new HashMap<String, User>();
    Database.initializeStaff();
    writeSerializedObject(FileType.USERS);

    BOOKINGS = new HashMap<String, Booking>();
    writeSerializedObject(FileType.BOOKINGS);

    CINEPLEX = new HashMap<String, Cineplex>();
    writeSerializedObject(FileType.CINEPLEX);

    SHOWTIME = new HashMap<String, Showtime>();
    numOfShowtimes = 0;
    writeSerializedObject(FileType.SHOWTIME);

    MOVIES = new HashMap<String, Movie>();
    numOfMovies = 0;
    writeSerializedObject(FileType.MOVIES);

    PRICES = new HashMap<MoviesType, Double>();
    PRICES.put(MoviesType.TWO_D, 13.00);
    PRICES.put(MoviesType.THREE_D, 20.00);
    PRICES.put(MoviesType.BLOCKBUSTER, 16.00);
    writeSerializedObject(FileType.PRICES);

    HOLIDAYS = new HashSet<String>();
    Database.initializeHoliday();
    writeSerializedObject(FileType.HOLIDAYS);

    SYSTEM = new String("0");
    writeSerializedObject(FileType.SYSTEM);

    return true;
  }
}