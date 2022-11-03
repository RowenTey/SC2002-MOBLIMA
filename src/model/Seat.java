package src.model;

import java.io.Serializable;

import src.model.enums.SeatType;

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
   * {@link SeatType} of seat
   */
  private SeatType seatType;

  /**
   * Position of the seat in string
   */
  private String position;

  /**
   * Constructor of Seat
   * 
   * @param row      {@code row} of seat
   * @param col      {@code col} of seat
   * @param showtime {@link Showtime} of seat
   * @param seatType {@link SeatType} of seat
   */
  public Seat(int row, int col, Showtime showtime, SeatType seatType) {
    setPos(row, col);
    setShowtime(showtime);
    setBooked(false);
    setSeatType(seatType);
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
   * Sets the {@link SeatType} of the seat
   * 
   * @param seatType of the seat
   */
  public void setSeatType(SeatType seatType){
    this.seatType = seatType;
  }
  
  /**
   * Sets the position of the seat
   * 
   * @param position of the seat
   */
  public void setPosition(String position){
    this.position = position;
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

  /**
   * Gets the {@link SeatType} of seat
   * 
   * @return {@link SeatType} of the seat
   */
  public SeatType getSeatType(){
    return this.seatType;
  }

  /**
   * Gets the position of the Seat
   * 
   * @return position of the seat
   */
  public String getPosition(){
    return this.position;
  }

  /**
   * Gets the row of the seat
   * 
   * @return row of seat
   */
  public int getRow(){
    return this.row;
  }
  
  /**
   * Gets the column of the seat
   * 
   * @return column of seat
   */
  public int getCol(){
    return this.col;
  }
}
