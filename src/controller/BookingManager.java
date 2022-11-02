package src.controller;

import java.util.ArrayList;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.*;
import src.model.enums.AgeGroup;

/**
 * BookingManager is a controller class that acts as a "middleman" between the
 * view
 * classes - CineplexAppView, MovieView, ShowtimeView and the model class -
 * {@link Booking}
 *
 * @author Ace Ang, Shao Wei
 * @version 1.0
 * @since 2022-10-22
 */
public class BookingManager {
  /**
   * Gets the list of {@link Booking}
   * 
   * @return list of {@link Booking}
   */
  private static ArrayList<Booking> getBookingList() {
    ArrayList<Booking> bookingList = new ArrayList<Booking>();
    for (Booking booking : Database.BOOKINGS.values()) {
      bookingList.add(booking);
    }
    return bookingList;
  }

  /**
   * Creates a transaction ID for {@link Booking}
   * 
   * @param seat of the booking
   * 
   * @return transaction id for the {@link Booking}
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
   * Computes the price of ticket based on different factors
   * 
   * @param price     (base price) of movie
   * @param cinema    of the movie
   * @param seat      of the booking
   * @param movieGoer of the booking
   * 
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
   * Creates a booking {@link Ticket}
   * 
   * @param movie     of booking
   * @param seat      of booking
   * @param cinema    of movie
   * @param movieGoer of the booking
   * 
   * @return {@link Ticket} of the booking
   */
  public static Ticket createBookingTicket(Movie movie, Seat seat, Cinema cinema, MovieGoer movieGoer) {
    double finalPrice = BookingManager.computePrice(movie.getPrice(), cinema, seat, movieGoer);

    Ticket newTicket = new Ticket(finalPrice, seat, cinema, movie.getTitle(), movie.getType());

    return newTicket;
  }

  /**
   * Creates a new {@link Booking} and stores it to {@link Database}
   *
   * @param seat       of the booking
   * @param ticket     of the booking
   * @param movieGoer  of the booking
   * @param position   of the seat
   * @param movieTitle of the booking
   */
  public static void createBooking(Seat seat, Ticket ticket, MovieGoer movieGoer, String position, String movieTitle) {
    ArrayList<Booking> bookingList = BookingManager.getBookingList();
    String newTransactionId = createTransactionId(seat);
    ticket.setIsPaid(true);
    Booking newBooking = new Booking(newTransactionId, ticket, movieGoer,
        position);
    bookingList.add(newBooking);
    Database.BOOKINGS.put(newTransactionId, newBooking);
    Database.saveFileIntoDatabase(FileType.BOOKINGS);
    System.out.println("\nBooking created! Your ticket is printed below: ");
    printBookingDetails(newBooking);
  }

  /**
   * Prints the complete details of the {@link Booking}
   *
   * @param booking to be printed
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
    System.out.println(String.format("%-25s: %s", "Movie Type", booking.getTicket().getMovieType()));
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
   * Prints the complete details of the {@link Ticket}
   *
   * @param ticket    to be printed
   * @param movieGoer of the booking
   * @param position  of the seat
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
    System.out.println(String.format("%-25s: %s", "Movie Type", ticket.getMovieType()));
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
   * Prompts the {@link MovieGoer} details
   * 
   * @return {@link MovieGoer}
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
   * Removes hyphen and semi-colon from Date
   * 
   * @param date to be formatted
   * 
   * @return formatted date in string
   */
  public static String formatDate(String date) {
    date = date.replaceAll(":", "");
    date = date.replaceAll("-", "");
    date = date.replace(" ", "");
    return date;
  }

  /**
   * Finds {@link Booking} by email
   * 
   * @param email of the moviegoer
   */
  public static void findBooking(String email) {
    ArrayList<Booking> curList = BookingManager.getBookingList();
    ArrayList<Booking> myList = new ArrayList<Booking>();
    for (int i = 0; i < curList.size(); i++) {
      if (curList.get(i).getMovieGoer().getEmail().equals(email)) {
        myList.add(curList.get(i));
      }
    }
    if (myList.size() == 0) {
      System.out.println("\nNo booking is found!");
    } else {
      for (int i = 0; i < myList.size(); i++) {
        BookingManager.printBookingDetails(myList.get(i));
      }
    }
    Helper.pressAnyKeyToContinue();
  }

  /**
   * Prompts user for {@link Booking} details
   * 
   * @param showtimeId of showtime
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
      if (row != -1 && row != 3 && row != 7 && col != 5 && col != 14 && col <= 17) {
        break;
      }
      System.out.println("\nInvalid row and column!");
    } while (row == 3 || row == 7 || col == 5 || col == 14 || row == -1 || col > 17);

    if (showtime.getSeatAt(row + 1, col).getBooked()) {
      System.out.println("Booking failed! Seat is occupied...");
    } else {
      System.out.println("\nSeat " + position + " selected...");
      MovieGoer newMovieGoer = BookingManager.promptUserDetails();
      Ticket ticket = BookingManager.createBookingTicket(showtime.getMovie(), showtime.getSeatAt(row + 1, col),
          showtime.getCinema(), newMovieGoer);
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
          updateTicketSales(showtime);
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
   * 
   * @param row      of the seat
   * @param column   of the seat
   * @param showtime of the booking
   * 
   * @return boolean {@code true} when seat is booked
   */
  protected static boolean bookSeat(int row, int column, Showtime showtime) {
    showtime.getSeatAt(row, column).setBooked(true);
    Database.SHOWTIME.put(showtime.getShowtimeId(), showtime);
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
    return true;
  }

  /**
   * Updates the ticket sales of {@link Movie}
   * 
   * @param showtime of the movie
   * 
   * @return boolean {@code true} when ticket sales is updated
   */
  protected static boolean updateTicketSales(Showtime showtime) {
    Movie movie = showtime.getMovie();
    movie.setTicketSales(movie.getTicketSales() + 1);
    Database.MOVIES.put(movie.getMovieId(), movie);
    Database.saveFileIntoDatabase(FileType.MOVIES);
    return true;
  }

  /**
   * Handles {@link Booking} checking
   */
  public static void handleCheckBooking() {
    System.out.print("Enter your email: ");
    String email = Helper.readString();
    BookingManager.findBooking(email);
  }
}
