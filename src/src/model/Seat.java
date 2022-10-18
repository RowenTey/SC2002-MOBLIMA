package src.model;

import java.util.Map;

/**
 * The class that records the seat of a {@code Showtime}.
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-18
 */
public class Seat {
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
   * {@code Showtime} of seat
   */
  private Showtime showtime;

  /**
   * Constructor of Seat
   * 
   * @param row      Row of seat
   * @param col      Column of seat
   * @param showtime {@code Showtime} of seat
   * @param boooked  Seat is booked
   */
  public Seat(int row, int col, Showtime showtime) {
    this.setPos(row, col);
    this.setShowtime(showtime);
    this.setBooked(false);
  }

  /**
   * Sets the position of seat
   * 
   * @param row row of the seat
   * @param col column of the seat
   */
  public void setPos(int row, int column) {
    this.row = row;
    this.col = column;
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
   * Sets the {@code Showtime} of the seat
   * 
   * @param showtime {@code Showtime} of the seat
   */
  public void setShowtime(Showtime showtime) {
    this.showtime = showtime;
  }

  /**
   * Gets the position of seat
   * 
   * @return position of seat
   */
  public Map.Entry<Integer, Integer> getPos() {
    return Map.entry(this.row, this.col);
  }

  /**
   * Gets if the seat is booked
   * 
   * @return {@code true} if the seat is booked. Otherwise, {@code false}
   */
  public boolean getBooked() {
    return this.booked;
  }

  /**
   * Gets the {@code Showtime} of seat
   * 
   * @return {@code Showtime} the seat belongs to
   */
  public Showtime getShowtime() {
    return this.showtime;
  }
}
