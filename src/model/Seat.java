package src.model;

/**
 * The class that records the seat of a showtime.
 * @author Kai Seong
 * @version 1.0
 * @since 2022-3-30
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
   * Showtime of seat
   */
  private Showtime showtime;

  /**
   * Constructor of Seat
   * @param row Row of seat
   * @param col Column of seat
   * @param showtime Showtime of seat
   * @param boooked Seat is booked
   */
  public Seat(int row, int col, Showtime showtime, boolean booked) {
    setPos(row, col);
    setShowtime(showtime);
    setBooked(booked)
  }
    
  /**
   * Sets the position of seat
   * @param row row of the seat
   * @param col column of the seat
   */
  public void setPos(int row, int column) {
    this.row = row;
    this.column = column;
  }

  /**
   * Sets if the seat is booked
   * @param booked {@code true} if the seat is booked. Otherwise, {@code false}.
   */
  public void setBooked(boolean booked) {
    this.booked = booked;
  }
  
  /**
   * Sets the showtime of the seat
   * @param showtime showtime of the seat
   */
  public void setShowtime(Showtime showtime) {
    this.showtime = showtime;
  }

  /**
   * Gets the position of seat
   * @return position of seat
   */
  public (int, int) getPos() {
    return (this.row, this.col);
  }
  
  /**
   * Gets if the seat is booked
   * @return {@code true} if the seat is booked. Otherwise, {@code false}
   */
  public boolean getBooked() {
    return this.booked;
  }
  
  /**
   * Gets the showtime of seat 
   * @return showtime the seat belongs to
   */
  public Showtime getShowtime() {
    return this.showtime;
  }
  
  /**
  * Override toString method to show the simplified details of the invoice.
  * @return a string that contains invoice details
  */
  @Override
  public String toString() {
    String res = String.format("Invoice Id: %s\t\tDate Of Issue: %s\t\tTotal: %.2f", getInvoiceId(), getDateOfPayment(),getTotal());
    return res;
  }
  
  /**
   * Override compareTo method to compare different invoice objects according to invoice id.
   */
  @Override
  public int compareTo(Invoice invoice) {
    if (this == invoice) {
      return 0;
    }

    int thisInvoiceId = Integer.parseInt(this.getInvoiceId().substring(1));
    int thatInvoiceId = Integer.parseInt(invoice.getInvoiceId().substring(1));

    return thisInvoiceId - thatInvoiceId;
  }
}   
