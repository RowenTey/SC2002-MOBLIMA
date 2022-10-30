package controller;

import database.Database;
import database.FileType;
import helper.Helper;
import model.*;
import model.enums.AgeGroup;

import java.util.ArrayList;

/**
 * Booking Manager
 *
 * @author Ace Ang
 * @version 1.0
 * @since 2022-10-22
 */
public class BookingManager {
  /**
   * Creates a transaction ID for booking
   */
  private static ArrayList<Booking> getBookingList() {
    ArrayList<Booking> bookingList = new ArrayList<Booking>();
    for (Booking booking : Database.BOOKINGS.values()) {
      bookingList.add(booking);
    }
    return bookingList;
  }

  /**
   * Creates a transaction ID for booking
   */
  public static String createTransactionId(Seat seat) {
    String cinemacode = seat.getShowtime().getCinema().getCinemaCode(); // first two letters of location
    String timeShow = Helper.getTimeNow(); // get the current time
    timeShow = formatDate(timeShow);

    String transactionid = cinemacode + timeShow;
    /*
     * formats the transaction id as XXXYYYYMMDDhhmm (Y : year, M : month, D : day,
     * h : hour, m : minutes, XXX : cinema code in letters)
     */
    return transactionid;
  }

  /**
   * Compute price of ticket based on different factors
   * 
   * @param price
   * @return computed price
   */
  public static double computePrice(double price, Cinema cinema, Seat seat, MovieGoer movieGoer) {
    double adjustedPrice = price;
    double multiplier = 1;

    // Child & Senior Citizen 50% cheaper
    if (movieGoer.getAgeGroup() == AgeGroup.CHILD || movieGoer.getAgeGroup() == AgeGroup.SENIOR_CITIZEN) {
      multiplier *= 0.5;
    }

    String formattedDate = seat.getShowtime().getTime().substring(0, 10);
    // formats date to yyyy-MM-dd to match format in HOLIDAY database
    if (Database.HOLIDAYS.contains(formattedDate) || Helper.checkIsDateWeekend(seat.getShowtime().getTime())) {
      multiplier *= 1.3; // 30% surcharge for holiday or weekend
    }

    // include GST
    multiplier *= 1.07;
    adjustedPrice *= multiplier;

    if (cinema.getIsPlatinum()) {
      adjustedPrice += 5; // extra $5 for platinum cinema
    }

    return adjustedPrice;
  }

  /**
   * Creates a ticket for the createBooking method
   */
  public static Ticket createBookingTicket(double price, Seat seat, Cinema cinema, String movieTitle,
      MovieGoer movieGoer) {
    double finalPrice = BookingManager.computePrice(price, cinema, seat, movieGoer);

    Ticket newTicket = new Ticket(finalPrice, seat, cinema, movieTitle);

    return newTicket;
  }

  /**
   * Constructor for booking in BookingManager
   *
   * @param price    the price of the ticket
   * @param seat     the seat of the ticket
   * @param cineplex the cineplex of the ticket
   * @param name     the user associated with the ticket
   */
  public static void createBooking(Seat seat, Ticket ticket, MovieGoer movieGoer, String position, String movieTitle) {
    ArrayList<Booking> bookingList = BookingManager.getBookingList();
    String newTransactionId = createTransactionId(seat);
    ticket.setIsPaid(true);
    // Ticket newTicket =
    // createBookingTicket(price,seat,cineplex,movieTitle,cinema); //to be
    // implemented
    Booking newBooking = new Booking(newTransactionId, ticket, movieGoer,
        position);
    bookingList.add(newBooking);
    Database.BOOKINGS.put(newTransactionId, newBooking);
    Database.saveFileIntoDatabase(FileType.BOOKINGS);

    System.out.println("\nBooking created! Your ticket is printed below: ");
    printBookingDetails(newBooking);
  }

  /**
   * Print the complete details of the booking
   *
   * @param guest {@link Booking} object to print
   */
  public static void printBookingDetails(Booking booking) {
    MovieGoer movieGoer = booking.getMovieGoer();
    System.out.println();
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println(String.format("%-25s: %s", "Transaction ID", booking.getTransactionId()));
    System.out.println(String.format("%-25s: %s", "Name", movieGoer.getName()));
    System.out.println(String.format("%-25s: +65-%s", "Mobile Number", movieGoer.getMobile()));
    System.out.println(String.format("%-25s: %s", "Email", movieGoer.getEmail()));
    System.out.println(String.format("%-25s: %s", "Time", booking.getTicket().getSeat().getShowtime().getTime()));
    System.out.println(String.format("%-25s: %s", "Ticket Type", movieGoer.getAgeGroup().getLabel()));
    System.out.println(String.format("%-25s: %s", "Movie Title", booking.getTicket().getMovieTitle()));
    System.out.println(String.format("%-25s: %s", "Cinema", booking.getTicket().getCinema().getCinemaCode()));
    System.out.println(String.format("%-25s: %s", "Cinema Type",
        booking.getTicket().getCinema().getIsPlatinum() ? "Platinum" : "Not Platinum"));
    System.out.println(
        String.format("%-25s: %s", "Location", booking.getTicket().getCinema().getCineplex().getLabel()));
    System.out.println(String.format("%-25s: %s", "Seat", booking.getPosition()));
    System.out.println(String.format("%-25s: $%s", "Price", Helper.df2.format(booking.getTicket().getPrice())));
    System.out
        .println(String.format("%-25s: %s", "Status", booking.getTicket().getIsPaid() ? "Paid" : "Ready for Payment"));
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println();
  }

  /**
   * Print the complete details of the booking
   *
   * @param guest {@link Booking} object to print
   */
  public static void printTicketDetails(Ticket ticket, MovieGoer movieGoer, String position) {
    System.out.println();
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println(String.format("%-25s: %s", "Name", movieGoer.getName()));
    System.out.println(String.format("%-25s: +65-%s", "Mobile Number", movieGoer.getMobile()));
    System.out.println(String.format("%-25s: %s", "Email", movieGoer.getEmail()));
    System.out.println(String.format("%-25s: %s", "Time", ticket.getSeat().getShowtime().getTime()));
    System.out.println(String.format("%-25s: %s", "Ticket Type", movieGoer.getAgeGroup().getLabel()));
    System.out.println(String.format("%-25s: %s", "Movie Title", ticket.getMovieTitle()));
    System.out.println(String.format("%-25s: %s", "Cinema", ticket.getCinema().getCinemaCode()));
    System.out.println(
        String.format("%-25s: %s", "Cinema Type", ticket.getCinema().getIsPlatinum() ? "Platinum" : "Not Platinum"));
    System.out.println(String.format("%-25s: %s", "Location", ticket.getCinema().getCineplex().getLabel()));
    System.out.println(String.format("%-25s: %s", "Seat", position));
    System.out.println(String.format("%-25s: $%s", "Price", Helper.df2.format(ticket.getPrice())));
    System.out.println(String.format("%-25s: %s", "Status", ticket.getIsPaid() ? "Paid" : "Ready for Payment"));
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println();
  }

  /**
   * Prompt User's information
   */
  public static MovieGoer promptUserDetails() {
    System.out.print("\nEnter your name: ");
    String name = Helper.readString();
    System.out.print("Enter your mobile number (eg: 88889770): +65-");
    String mobile = Helper.readString();
    System.out.print("Enter your email: ");
    String email = Helper.readString();

    System.out.print("Enter your age: ");
    int age = Helper.readInt(1, 100);
    AgeGroup ageGroup;
    if (age >= 60) {
      ageGroup = AgeGroup.SENIOR_CITIZEN;
    } else if (age >= 21) {
      ageGroup = AgeGroup.ADULT;
    } else {
      ageGroup = AgeGroup.CHILD;
    }

    MovieGoer newMovieGoer = new MovieGoer(email, name, mobile, email, ageGroup);
    return newMovieGoer;
  }

  /**
   * Remove hyphen and semi-colon from Date
   */
  public static String formatDate(String date) {
    date = date.replaceAll(":", "");
    date = date.replaceAll("-", "");
    date = date.replace(" ", "");
    return date;
  }

  public static String promptEmail() {
    System.out.println("Enter your email: ");
    String email = Helper.readString();
    return email;
  }

  /**
   * Find booking by email
   */
  public static void findBooking(String email) {
    ArrayList<Booking> curList = BookingManager.getBookingList();
    for (int i = 0; i < curList.size(); i++) {
      if (curList.get(i).getMovieGoer().getEmail().equals(email)) {
        printBookingDetails(curList.get(i));
      }
    }
    Helper.pressAnyKeyToContinue();
  }

  /**
   * Prompt user for booking booking details
   */
  public static void promptBooking(String showtimeId) {
    String position;
    int row = 0;
    int col = 0;
    Showtime showtime = ShowtimeManager.getShowtimebyId(showtimeId);
    ShowtimeManager.displayShowtimeLayout(showtime);

    do {
      System.out.println("Please enter the desired seat coordinates (e.g A6): ");
      position = Helper.readString();
      row = ShowtimeManager.getSeatRow(position);
      col = Integer.parseInt(position.substring(1));
      if (row != 3 && row != 7 && col != 5 && col != 14) {
        break;
      }
      System.out.println("\nInvalid row and column!");
    } while (row == 3 || row == 7 || col == 5 || col == 14);

    if (showtime.getSeatAt(row + 1, col).getBooked()) {
      System.out.println("Booking failed! Seat is occupied...");
    } else {
      System.out.println("\nSeat " + position + " selected...");
      MovieGoer newMovieGoer = BookingManager.promptUserDetails();
      Ticket ticket = BookingManager.createBookingTicket(showtime.getMovie().getPrice(),
          showtime.getSeatAt(row + 1, col), showtime.getCinema(), showtime.getMovie().getTitle(), newMovieGoer);
      BookingManager.printTicketDetails(ticket, newMovieGoer, position);
      System.out.println("(1) Confirm Payment");
      System.out.println("(2) Back");
      System.out.print("Which would you like to do: ");

      int pay;
      pay = Helper.readInt(1, 2);
      switch (pay) {
        case 1:
          if (BookingManager.bookSeat(row + 1, col, showtime)) {
            System.out.println("\nSeat " + position + " is booked successfully!");
          }
          BookingManager.createBooking(showtime.getSeatAt(row + 1, col), ticket, newMovieGoer, position,
              showtime.getMovie().getTitle());
          break;
        case 2:
          System.out.println("Booking failed!");
          break;
        default:
          break;
      }
    }

    Helper.pressAnyKeyToContinue();
  }

  /**
   * Books the seat at that position for that showtime
   */
  protected static boolean bookSeat(int row, int column, Showtime showtime) {
    showtime.getSeatAt(row, column).setBooked(true);
    Database.SHOWTIME.put(showtime.getShowtimeId(), showtime);
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
    return true;
  }

}
