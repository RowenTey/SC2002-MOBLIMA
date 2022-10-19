package model;

import java.util.Date;

import model.enums.LayoutType;

/**
 * The class that records the Showtime of a showtime.
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-18
 */
public class Showtime {
  /**
   * Time of showtime
   */
  private Date time;

  /**
   * {@code Movie} of showtime
   */
  private Movie movie;

  /**
   * {@code Cinema} of showtime
   */
  private Cinema cinema;

  /**
   * layoutType of showtime
   */
  private LayoutType layoutType;

  /**
   * Seats of showtime based on layoutType
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
   * @param movie      {@code Movie} of showtime
   * @param cinema     {@code Cinema} of showtime
   * @param layoutType {@code LayoutType} of showtime
   */
  public Showtime(Date time, Movie movie, Cinema cinema, LayoutType layoutType) {
    this.setTime(time);
    this.setMovie(movie);
    this.setCinema(cinema);
    this.setLayoutType(layoutType);
    this.initSeats();
  }

  /**
   * Sets the time of showtime
   * 
   * @param time time of showtime
   */
  public void setTime(Date time) {
    this.time = time;
  }

  /**
   * Sets the {@code Movie} of showtime
   * 
   * @param movie {@code Movie} of showtime
   */
  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  /**
   * Sets the {@code Cinema} of showtime
   * 
   * @param cinema {@code Cinema} of showtime
   */
  public void setCinema(Cinema cinema) {
    this.cinema = cinema;
  }

  /**
   * Sets the {@code LayoutType} of showtime
   * 
   * @param layoutType {@code LayoutType} of showtime
   */
  public void setLayoutType(LayoutType layoutType) {
    this.layoutType = layoutType;
  }

  /**
   * Gets the time of showtime
   * 
   * @return time of showtime
   */
  public Date getTime() {
    return this.time;
  }

  /**
   * Gets the movie of showtime
   * 
   * @return {@code Movie} that is assigned to this showtime
   */
  public Movie getMovie() {
    return this.movie;
  }

  /**
   * Gets the cinema of showtime
   * 
   * @return {@code Cinema} that is assigned to this showtime
   */
  public Cinema getCinema() {
    return this.cinema;
  }

  /**
   * Gets the {@code Seats} of showtime
   * 
   * @return {@code Seats[][]} of this showtime
   */
  public Seat[][] getSeats() {
    return this.seats;
  }

  /**
   * Gets the seat at a specified postion of showtime
   * 
   * @param row row no. of seat
   * @param col column no. of seat
   * @return {@code Seats} at ({@code row}, {@code col})
   */
  public Seat getSeatAt(int row, int col) {
    return this.seats[row - 1][col - 1];
  }

  // TODO: add in remaining methods and update constructor

  /**
   * Initialise the seats of the showtime
   * 
   * @param layoutType {@code LayoutType} of showtime
   */
  private void initSeats() {
    switch (this.layoutType) {
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

    this.seats = new Seat[ROWS][COLS];

    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col <= 16; col++) {
        if (row == 3 || row == 7)
          continue;
        if (col == 4 || col == 13)
          continue;

        this.seats[row][col] = new Seat(row, col, this);
      }
    }
  }
}
