package src.model;

/**
 * The class that records the Showtime of a showtime.
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-18
 */
public class Showtime {
  /**
   * Date of showtime
   */
  private Date date;

  /**
   * Time of showtime
   */
  private String time;
  
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
  private final static ROWS;

  /**
   * COLUMNS of showtime based on layoutType
   */
  private final static COLS;
  
  /**
   * Constructor of Showtime
   * @param row Row of Showtime
   * @param col Column of Showtime
   * @param showtime Showtime of howtime
   * @param boooked Showtime is booked
   */
  public Showtime(int row, int col, Showtime showtime, boolean booked) {
    setPos(row, col);
    setShowtime(showtime);
    setBooked(booked)
    this.seats = new Seat[ROWS][COLS];
  }
    
  /**
   * Sets the date of showtime
   * @param date date of showtime
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * Sets the time of showtime
   * @param time time of showtime
   */
  public void setTime(String time) {
    this.time = time;
  }
  
  /**
   * Sets the {@code Movie} of showtime
   * @param showtime {@code Movie} of showtime
   */
  public void setMovie(Movie movie) {
    this.movie = movie;
  }
  
  /**
   * Sets the {@code Cinema} of showtime
   * @param showtime {@code Cinema} of showtime
   */
  public void setCinema(Cinema cinema) {
    this.cinema = cinema;
  }

  /**
   * Gets the date of showtime
   * @return date of showtime
   */
  public Date getDate() {
    return this.date;
  }
  
  /**
   * Gets the time of showtime
   * @return time of showtime
   */
  public String getTime() {
    return this.time;
  }
  
  /**
   * Gets the movie of showtime 
   * @return {@code Movie} that is assigned to this showtime
   */
  public Movie getMovie() {
    return this.movie;
  }
  
  /**
   * Gets the cinema of showtime 
   * @return {@code Cinema} that is assigned to this showtime
   */
  public Cinema getCinema() {
    return this.cinema;
  }
  
  /**
   * Gets the cinema of showtime 
   * @param row row no. of seat
   * @param col column no. of seat
   * @return seat at ({@code row}, {@code col})
   */
  public Seat getSeatAt(int row, int col) {
    return this.seats[row-1][col-1];
  }
  
  // TODO: add in remaining methods and update constructor
  
  
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
