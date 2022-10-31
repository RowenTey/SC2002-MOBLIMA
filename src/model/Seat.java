package src.model;

import java.io.Serializable;

/**
 * The class that records the seat of a {@link Showtime}.
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-18
 */
public class Seat implements Serializable {
  /*
   * For Java Serializable
   */
  private static final long serialVersionUID = 6L;

  /**
   * Seat is booked
   */
  private boolean booked;

  /**
   * Row of seat
   */
  private int row;

  /**
   * Column of seat
   */
  private int col;

  /**
   * {@link Showtime} of seat
   */
  private Showtime showtime;

  /**
   * Constructor of Seat
   * 
   * @param row      {@code row} of seat
   * @param col      {@code col} of seat
   * @param showtime {@link Showtime} of seat
   */
  public Seat(int row, int col, Showtime showtime) {
    setPos(row, col);
    setShowtime(showtime);
    setBooked(false);
  }

  /**
   * Sets the position of seat
   * 
   * @param row {@code row} of the seat
   * @param col {@code col} of the seat
   */
  public void setPos(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Sets if the seat is booked
   * 
   * @param booked {@code true} if the seat is booked. Otherwise, {@code false}.
   */
  public void setBooked(boolean booked) {
    this.booked = booked;
  }

  /**
   * Sets the {@link Showtime} of the seat
   * 
   * @param showtime {@link Showtime} of the seat
   */
  public void setShowtime(Showtime showtime) {
    this.showtime = showtime;
  }

  /**
   * Gets if the seat is booked
   * 
   * @return {@code true} if the seat is booked. Otherwise, {@code false}
   */
  public boolean getBooked() {
    return booked;
  }

  /**
   * Gets the {@link Showtime} of seat
   * 
   * @return {@link Showtime} the seat belongs to
   */
  public Showtime getShowtime() {
    return showtime;
  }
}
