package src.model;

import java.io.Serializable;

import src.model.enums.LayoutType;

/**
 * The class that records the showtime of a {@link Movie}
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-18
 */
public class Showtime implements Serializable {
  /**
   * For Java Serializable
   */
  private static final long serialVersionUID = 5L;

  /**
   * Id of the showtime
   */
  private String showtimeId;

  /**
   * Time of showtime
   */
  private String time;

  /**
   * {@link Movie} of showtime
   */
  private Movie movie;

  /**
   * {@link Cinema} of showtime
   */
  private Cinema cinema;

  /**
   * {@link LayoutType} of showtime
   */
  private LayoutType layoutType;

  /**
   * {@link Seat} of showtime based on layoutType
   */
  private Seat[][] seats;

  /**
   * ROWS of showtime based on layoutType
   */
  private static int ROWS;

  /**
   * COLUMNS of showtime based on layoutType
   */
  private static final int COLS = 17;

  /**
   * Constructor of Showtime
   * 
   * @param time       time of showtime
   * @param showtimeId id of showtime
   * @param movie      {@link Movie} of showtime
   * @param cinema     {@link Cinema} of showtime
   * @param layoutType {@link LayoutType} of showtime
   */
  public Showtime(String showtimeId, String time, Movie movie, Cinema cinema, LayoutType layoutType) {
    setShowtimeId(showtimeId);
    setTime(time);
    setMovie(movie);
    setCinema(cinema);
    setLayoutType(layoutType);
    initSeats();
  }

  /**
   * Sets the time of showtime
   * 
   * @param time time of showtime
   */
  public void setTime(String time) {
    this.time = time;
  }

  /**
   * Sets the Id of showtime
   * 
   * @param showtimeId Id of showtime
   */
  public void setShowtimeId(String showtimeId) {
    this.showtimeId = showtimeId;
  }

  /**
   * Sets the {@link Movie} of showtime
   * 
   * @param movie {@link Movie} of showtime
   */
  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  /**
   * Sets the {@link Cinema} of showtime
   * 
   * @param cinema {@link Cinema} of showtime
   */
  public void setCinema(Cinema cinema) {
    this.cinema = cinema;
  }

  /**
   * Sets the {@link LayoutType} of showtime
   * 
   * @param layoutType {@link LayoutType} of showtime
   */
  public void setLayoutType(LayoutType layoutType) {
    this.layoutType = layoutType;
  }

  /**
   * Gets the time of showtime
   * 
   * @return time of showtime
   */
  public String getTime() {
    return time;
  }

  /**
   * Gets the Id of the showtime
   * 
   * @return Id of the showtime
   */
  public String getShowtimeId() {
    return showtimeId;
  }

  /**
   * Gets the movie of showtime
   * 
   * @return {@link Movie} object that is assigned to this showtime
   */
  public Movie getMovie() {
    return movie;
  }

  /**
   * Gets the cinema of showtime
   * 
   * @return {@link Cinema} object that is assigned to this showtime
   */
  public Cinema getCinema() {
    return this.cinema;
  }

  /**
   * Gets the {@link Seat} of showtime
   * 
   * @return {@code Seats[][]} of this showtime
   */
  public Seat[][] getSeats() {
    return seats;
  }

  /**
   * Gets the seat at a specified postion of showtime
   * 
   * @param row row no. of seat
   * @param col column no. of seat
   * @return {@link Seat} at ({@code row}, {@code col})
   */
  public Seat getSeatAt(int row, int col) {
    return seats[row - 1][col - 1];
  }

  /**
   * Initialise the seats of the showtime
   * 
   * @param layoutType {@link LayoutType} of showtime
   */
  private void initSeats() {
    switch (layoutType) {
      case LARGE:
        ROWS = 11;
        break;
      case MEDIUM:
        ROWS = 9;
        break;
      case SMALL:
        ROWS = 7;
        break;
      default:
        ROWS = 9;
        break;
    }

    seats = new Seat[ROWS][COLS];

    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col <= 16; col++) {
        if (row == 3 || row == 7)
          continue;
        if (col == 4 || col == 13)
          continue;

        seats[row][col] = new Seat(row, col, this);
      }
    }
  }
}
