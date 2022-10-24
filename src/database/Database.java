package database;

import java.util.HashMap;

import controller.CineplexManager;
import controller.MovieManager;
import controller.ShowtimeManager;
import controller.StaffManager;

import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.*;

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
   * HashMap to contain {@link Staff} objects.
   */
  public static HashMap<String, Staff> STAFF = new HashMap<String, Staff>();

  /**
   * HashMap to contain {@link MovieGoer} objects.
   */
  public static HashMap<String, MovieGoer> MOVIE_GOER = new HashMap<String, MovieGoer>();

  /**
   * HashMap to contain {@link Cinema} objects.
   */
  public static HashMap<String, Cinema> CINEMAS = new HashMap<String, Cinema>();

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
   * Constructor that reads all the data from the data file during initialization
   * of program.
   */
  public Database() {
    if (!readSerializedObject(FileType.STAFF)) {
      System.out.println("Read into Staffs failed!");
    }
    if (!readSerializedObject(FileType.MOVIE_GOERS)) {
      System.out.println("Read into MovieGoers failed!");
    }
    if (!readSerializedObject(FileType.CINEMAS)) {
      System.out.println("Read into Cinema failed!");
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
  }

  /**
   * A method to save a particular {@link FileType} into database.
   * 
   * @param fileType file type to be saved.
   * @see FileType for the different type of filetypes.
   */
  public static void saveFileIntoDatabase(FileType fileType) {
    writeSerializedObject(fileType);
  }

  /**
   * A method to save all files into database.
   */
  public static void saveAllFiles() {
    saveFileIntoDatabase(FileType.STAFF);
    saveFileIntoDatabase(FileType.MOVIE_GOERS);
    saveFileIntoDatabase(FileType.CINEMAS);
    saveFileIntoDatabase(FileType.BOOKINGS);
    saveFileIntoDatabase(FileType.CINEPLEX);
    saveFileIntoDatabase(FileType.SHOWTIME);
    saveFileIntoDatabase(FileType.MOVIES);
  }

  /**
   * A method to read serialized object from a particular {@link FileType}.
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
      if (!(object instanceof HashMap)) {
        System.out.println(fileType.fileName);
        objectInputStream.close();
        return false;
      }

      // Read into database
      if (fileType == FileType.STAFF) {
        STAFF = (HashMap<String, Staff>) object;
      } else if (fileType == FileType.MOVIE_GOERS) {
        MOVIE_GOER = (HashMap<String, MovieGoer>) object;
      } else if (fileType == FileType.CINEMAS) {
        CINEMAS = (HashMap<String, Cinema>) object;
      } else if (fileType == FileType.BOOKINGS) {
        BOOKINGS = (HashMap<String, Booking>) object;
      } else if (fileType == FileType.CINEPLEX) {
        CINEPLEX = (HashMap<String, Cineplex>) object;
      } else if (fileType == FileType.SHOWTIME) {
        SHOWTIME = (HashMap<String, Showtime>) object;
      } else if (fileType == FileType.MOVIES) {
        MOVIES = (HashMap<String, Movie>) object;
      }

      objectInputStream.close();
      fileInputStream.close();
    } catch (EOFException err) {
      System.out.println("Warning: " + err.getMessage());
      if (fileType == FileType.STAFF) {
        STAFF = new HashMap<String, Staff>();
      } else if (fileType == FileType.MOVIE_GOERS) {
        MOVIE_GOER = new HashMap<String, MovieGoer>();
      } else if (fileType == FileType.CINEMAS) {
        CINEMAS = new HashMap<String, Cinema>();
      } else if (fileType == FileType.BOOKINGS) {
        BOOKINGS = new HashMap<String, Booking>();
      } else if (fileType == FileType.CINEPLEX) {
        CINEPLEX = new HashMap<String, Cineplex>();
      } else if (fileType == FileType.SHOWTIME) {
        SHOWTIME = new HashMap<String, Showtime>();
      } else if (fileType == FileType.MOVIES) {
        MOVIES = new HashMap<String, Movie>();
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
   * A method to write serialized object to file.
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
      if (fileType == FileType.STAFF) {
        objectOutputStream.writeObject(STAFF);
      } else if (fileType == FileType.MOVIE_GOERS) {
        objectOutputStream.writeObject(MOVIE_GOER);
      } else if (fileType == FileType.CINEMAS) {
        objectOutputStream.writeObject(CINEMAS);
      } else if (fileType == FileType.BOOKINGS) {
        objectOutputStream.writeObject(BOOKINGS);
      } else if (fileType == FileType.CINEPLEX) {
        objectOutputStream.writeObject(CINEPLEX);
      } else if (fileType == FileType.SHOWTIME) {
        objectOutputStream.writeObject(SHOWTIME);
      } else if (fileType == FileType.MOVIES) {
        objectOutputStream.writeObject(MOVIES);
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
   * Calls {@link StaffManager} to initialize the dummy guests.
   * 
   * @return {@code true} if initialized successfully. Otherwise, {@code false} if
   *         database is not empty.
   */
  public static boolean initializeStaff() {
    if (STAFF.size() != 0) {
      System.out.println("The database already has staffs. Reset database first to initialize staffs");
      return false;
    }
    StaffManager.initializeStaff();
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
    return true;
  }

  /**
   * A method to clear out all the data in database.
   * 
   * @return {@code true} if data is cleared successfully.
   */
  public static boolean clearDatabase() {
    // Initialize empty data
    STAFF = new HashMap<String, Staff>();
    Database.initializeStaff();
    writeSerializedObject(FileType.STAFF);

    MOVIE_GOER = new HashMap<String, MovieGoer>();
    writeSerializedObject(FileType.MOVIE_GOERS);

    CINEMAS = new HashMap<String, Cinema>();
    // Database.initializeRooms();
    writeSerializedObject(FileType.CINEMAS);

    BOOKINGS = new HashMap<String, Booking>();
    writeSerializedObject(FileType.BOOKINGS);

    CINEPLEX = new HashMap<String, Cineplex>();
    writeSerializedObject(FileType.CINEPLEX);

    SHOWTIME = new HashMap<String, Showtime>();
    writeSerializedObject(FileType.SHOWTIME);

    MOVIES = new HashMap<String, Movie>();
    writeSerializedObject(FileType.MOVIES);

    return true;
  }
}