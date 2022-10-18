package src.model;

/**
 * The Class that handles the data of hotel's invoices.
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
   * Constructor of Invoice
   * @param invoiceId Id of the invoice
   * @param guestId Id of the guest
   * @param roomTypeAsStr Room type of the stay
   * @param roomPrice Room price of the room
   * @param isRoomWifiEnabled Wifi enabled room 
   * @param reservationId Id of the reservation
   * @param nights Nights spent in the hotel
   * @param dateOfPayment Date which the payment is made
   * @param taxRate Tax rate of the invoice
   * @param discountRate Discount rate of the invoice
   * @param orders ArrayList of {@link Order} object that the room had made
   * @param subTotal Total amount without tax rate and discount rate
   * @param total Total amount with tax rate and discount rate
   */
  public Invoice(String invoiceId, String guestId, String roomTypeAsStr, double roomPrice, boolean isRoomWifiEnabled, String reservationId, int nights, String dateOfPayment,
          double taxRate, double discountRate, ArrayList<Order> orders ,double subTotal, double total) {
      // reservation will retrieve details for reservation id, guest id, room id
      // calculate sub total by searching orders
      setInvoiceId(invoiceId);
      setGuestId(guestId);
      setRoomTypeAsStr(roomTypeAsStr);
      setRoomPrice(roomPrice);
      setReservationId(reservationId);
      setNights(nights);
      setDateOfPayment(dateOfPayment);
      setTaxRate(taxRate);
      setDiscountRate(discountRate);
      setOrders(orders);
      setSubTotal(subTotal);
      setTotal(total);
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
   * Gets the date of payment.
   * @return date which the payment is made
   */
  public boolean getDateOfPayment() {
    return dateOfPayment;
  }
  /**
   * Gets the discount rate. 
   * @return discount rate of the invoice
   */
  public double getDiscountRate() {
    return discountRate;
  }
  /**
   * Gets the subtotal.
   * @return total amount without tax rate and discount rate.
   */
  public double getSubTotal() {
    return subTotal;
  }
  /**
   * Gets the tax rate.
   * @return tax rate of the invoice
   */
  public double getTaxRate() {
    return taxRate;
  }
  /**
   * Gets the guest Id.
   * @return Id of the guest
   */
  public String getGuestId() {
    return guestId;
  }

  /**
   * Gets the room type of the stay
   * @return Room type of the stay
   */
  public String getRoomTypeAsStr() {
    return roomTypeAsStr;
  }

  /**
   * Gets the room price of the room on the day of payment
   * @return Room price of the room on the day of payment
   */
  public double getRoomPrice() {
    return roomPrice;
  }

  /**
   * Gets if the room is wifi enabled
   * @return {@code true} if the room is wifi enabled. Otherwise, {@code false}.
   */
  public boolean getIsRoomWifiEnabled() {
    return isRoomWifiEnabled;
  }
  
  /**
   * Gets the nights spent in the hotel
   * @return Nights spent
   */
  public int getNights() {
    return nights;
  }
  /**
   * Gets the reservation Id.
   * @return Id of the reservation
   */
  public String getReservationId() {
    return reservationId;
  }
  /**
   * Gets the invoice Id.
   * @return Id of the invoice
   */
  public String getInvoiceId() {
    return invoiceId;
  }

  /**
   * Gets the orders made by the guest
   * @return ArrayList of {@link Order}(s) made by the guest
   */
  public ArrayList<Order> getOrders() {
    return orders;
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
