package src.controller;

import java.util.ArrayList;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.*;
import src.model.enums.AgeGroup;
import src.model.enums.SeatType;

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

    // formats date to yyyy-MM-dd to match format in HOLIDAY database
    String formattedDate = seat.getShowtime().getTime().substring(0, 10);
    if (Database.HOLIDAYS.contains(formattedDate) || Helper.checkIsDateWeekend(seat.getShowtime().getTime())) {
      multiplier *= 1.3; // 30% surcharge for holiday or weekend
    } else if (Helper.checkIsTimeNight(seat.getShowtime().getTime())) {
      multiplier *= 1.15; // 15% surcharge for night time
    }

    // include GST
    multiplier *= 1.07;
    adjustedPrice *= multiplier;

    // extra price for special seats
    if (seat.getSeatType() == SeatType.ULTIMA) {
      adjustedPrice += 2;
    } else if (seat.getSeatType() == SeatType.ELITE) {
      adjustedPrice += 3;
    } else if (seat.getSeatType() == SeatType.COUPLE) {
      adjustedPrice += 4;
    }

    // extra $5 for platinum cinema
    if (cinema.getIsPlatinum()) {
      adjustedPrice += 5;
    }

    return adjustedPrice;
  }

  /**
   * Creates a booking {@link Ticket}
   * 
   * @param showtime  of movie
   * @param seat      of booking
   * @param movieGoer of the booking
   * 
   * @return {@link Ticket} of the booking
   */
  public static Ticket createBookingTicket(Showtime showtime, ArrayList<Seat> seat, MovieGoer movieGoer) {
    double total = 0;
    double individualPrice;
    for (int i = 0; i < seat.size(); i++) {
      individualPrice = computePrice(showtime.getMovie().getPrice(), showtime.getCinema(), seat.get(i), movieGoer);
      total += individualPrice;
    }

    Ticket newTicket = new Ticket(showtime, total, seat);

    return newTicket;
  }

  /**
   * Creates a new {@link Booking} and stores it to {@link Database}
   *
   * @param ticket    of the booking
   * @param movieGoer of the booking
   */
  public static void createBooking(Ticket ticket, MovieGoer movieGoer) {
    ArrayList<Booking> bookingList = BookingManager.getBookingList();
    String newTransactionId = createTransactionId(ticket.getSeat().get(0));
    ticket.setIsPaid(true);
    Booking newBooking = new Booking(newTransactionId, ticket, movieGoer, ticket.getSeat());
    bookingList.add(newBooking);
    Database.BOOKINGS.put(newTransactionId, newBooking);
    Database.saveFileIntoDatabase(FileType.BOOKINGS);
    System.out.println("\nBooking created! Your ticket is printed below: ");
    printBookingDetails(newBooking);
  }

  /**
   * Gets the list of seats in string
   * 
   * @param ticket made by the buyer
   * @return list of seats in string
   */
  protected static String getAllSeatsInString(Ticket ticket) {
    String res = "";
    for (int i = 0; i < ticket.getSeat().size(); i++) {
      res += ticket.getSeat().get(i).getPosition();
      res += " ";
    }
    return res;
  }

  /**
   * Gets the list of type of seats in string
   * 
   * @param ticket made by the buyer
   * @return list of type of seats in string
   */
  protected static String getAllSeatsTypeInString(Ticket ticket) {
    String res = "";
    for (int i = 0; i < ticket.getSeat().size(); i++) {
      res += ticket.getSeat().get(i).getSeatType().getLabel();
      res += " ";
    }
    return res;
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
    System.out
        .println(String.format("%-25s: %s", "Time", booking.getTicket().getSeat().get(0).getShowtime().getTime()));
    System.out.println(String.format("%-25s: %s", "Ticket Type", movieGoer.getAgeGroup().getLabel()));
    System.out
        .println(String.format("%-25s: %s", "Movie Title", booking.getTicket().getShowtime().getMovie().getTitle()));
    System.out.println(
        String.format("%-25s: %s", "Movie Type", booking.getTicket().getShowtime().getMovie().getType().getLabel()));
    System.out
        .println(String.format("%-25s: %s", "Cinema", booking.getTicket().getShowtime().getCinema().getCinemaCode()));
    System.out.println(String.format("%-25s: %s", "Cinema Type",
        booking.getTicket().getShowtime().getCinema().getIsPlatinum() ? "Platinum" : "Not Platinum"));
    System.out.println(
        String.format("%-25s: %s", "Location", booking.getTicket().getShowtime().getCinema().getCineplex().getLabel()));
    System.out.println(String.format("%-25s: %s", "Seat(s)", BookingManager.getAllSeatsInString(booking.getTicket())));
    System.out
        .println(String.format("%-25s: %s", "Seat Type", BookingManager.getAllSeatsTypeInString(booking.getTicket())));
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
   */
  public static void printTicketDetails(Ticket ticket, MovieGoer movieGoer) {
    System.out.println();
    System.out.println(String.format("%-40s", "").replace(" ", "-"));
    System.out.println(String.format("%-25s: %s", "Name", movieGoer.getName()));
    System.out.println(String.format("%-25s: +65-%s", "Mobile Number", movieGoer.getMobile()));
    System.out.println(String.format("%-25s: %s", "Email", movieGoer.getEmail()));
    System.out.println(String.format("%-25s: %s", "Time", ticket.getShowtime().getTime()));
    System.out.println(String.format("%-25s: %s", "Ticket Type", movieGoer.getAgeGroup().getLabel()));
    System.out.println(String.format("%-25s: %s", "Movie Title", ticket.getShowtime().getMovie().getTitle()));
    System.out.println(String.format("%-25s: %s", "Movie Type", ticket.getShowtime().getMovie().getType()));
    System.out.println(String.format("%-25s: %s", "Cinema", ticket.getShowtime().getCinema().getCinemaCode()));
    System.out.println(
        String.format("%-25s: %s", "Cinema Type",
            ticket.getShowtime().getCinema().getIsPlatinum() ? "Platinum" : "Not Platinum"));
    System.out
        .println(String.format("%-25s: %s", "Location", ticket.getShowtime().getCinema().getCineplex().getLabel()));
    System.out.println(String.format("%-25s: %s", "Seat(s)", BookingManager.getAllSeatsInString(ticket)));
    System.out.println(String.format("%-25s: %s", "Seat Type", BookingManager.getAllSeatsTypeInString(ticket)));
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
    if (age >= 55) {
      ageGroup = AgeGroup.SENIOR_CITIZEN;
    } else if (age >= 21) {
      ageGroup = AgeGroup.ADULT;
    } else {
      ageGroup = AgeGroup.CHILD;
    }

    MovieGoer newMovieGoer = new MovieGoer(email, name, mobile, email, ageGroup, "test", false);
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
      System.out.println("Your booking history is as follow:");
      for (int i = 0; i < myList.size(); i++) {
        BookingManager.printBookingDetails(myList.get(i));
      }
    }
    Helper.pressAnyKeyToContinue();
  }

  /**
   * Prompts user for {@link Seat} details
   * 
   * @param showtime of the movie
   * @param seat     list of {@link Seat}
   * 
   * @return {@link Seat} that is selected
   */
  protected static Seat promptSeat(Showtime showtime, ArrayList<Seat> seat) {
    ShowtimeManager.displayShowtimeLayout(showtime, seat);
    String position;
    int row = 0;
    int col = 0;
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

    if (position.equals("I1") || position.equals("I2")) {
      showtime.getSeatAt(9, 1).setPosition("I1");
      showtime.getSeatAt(9, 2).setPosition("I2");
    } else if (position.equals("I3") || position.equals("I4")) {
      showtime.getSeatAt(9, 3).setPosition("I3");
      showtime.getSeatAt(9, 4).setPosition("I4");
    } else {
      showtime.getSeatAt(row + 1, col).setPosition(position);
    }

    return showtime.getSeatAt(row + 1, col);
  }

  /**
   * Prompts user for {@link Booking} details
   * 
   * @param username   of the moviegoer
   * @param showtimeId of showtime
   */
  public static void promptBooking(String showtimeId, String username) {
    ArrayList<Seat> seatList = new ArrayList<Seat>();
    Seat newSeat;
    int opt = -1;
    Showtime showtime = ShowtimeManager.getShowtimebyId(showtimeId);

    do {
      do {
        newSeat = promptSeat(showtime, seatList);
        if (newSeat.getBooked() || seatList.contains(newSeat)) {
          System.out.println("The selected seat is booked or selected!!");
        }
      } while (newSeat.getBooked() || seatList.contains(newSeat));

      if (newSeat.getPosition().equals("I1") || newSeat.getPosition().equals("I2")) {
        Seat couple1 = showtime.getSeatAt(9, 1);
        Seat couple2 = showtime.getSeatAt(9, 2);
        System.out.println("\n" + couple1.getSeatType().getLabel() + " Seat " + couple1.getPosition() + " & "
            + couple2.getPosition() + " selected...");
        seatList.add(couple1);
        seatList.add(couple2);
      } else if (newSeat.getPosition().equals("I3") || newSeat.getPosition().equals("I4")) {
        Seat couple1 = showtime.getSeatAt(9, 3);
        Seat couple2 = showtime.getSeatAt(9, 4);
        System.out.println("\n" + couple1.getSeatType().getLabel() + " Seat " + couple1.getPosition() + " & "
            + couple2.getPosition() + " selected...");
        seatList.add(couple1);
        seatList.add(couple2);
      } else {
        System.out.println("\n" + newSeat.getSeatType().getLabel() + " Seat " + newSeat.getPosition() + " selected...");
        seatList.add(newSeat);
      }

      System.out.println("(1) Select another seat");
      System.out.println("(2) Proceed to payment");
      opt = Helper.readInt(1, 2);
    } while (opt != 2);

    MovieGoer newMovieGoer = username.equals("") ? promptUserDetails()
        : (MovieGoer) UserManager.getUser(username);

    Ticket newTicket = createBookingTicket(showtime, seatList, newMovieGoer);
    printTicketDetails(newTicket, newMovieGoer);

    System.out.println("(1) Confirm Payment");
    System.out.println("(2) Back");
    System.out.print("Which would you like to do: ");

    int pay;
    pay = Helper.readInt(1, 2);
    switch (pay) {
      case 1:
        for (int i = 0; i < newTicket.getSeat().size(); i++) {
          if (BookingManager.bookSeat(newTicket.getSeat().get(i), showtime)) {
            System.out.println("\n" + newTicket.getSeat().get(i).getSeatType().getLabel() + " Seat "
                + newTicket.getSeat().get(i).getPosition() + " is booked successfully!");
          }
        }
        createBooking(newTicket, newMovieGoer);
        updateTicketSales(showtime, newTicket);
        break;
      case 2:
        System.out.println("Booking failed!");
        break;
      default:
        break;
    }

    Helper.pressAnyKeyToContinue();
  }

  /**
   * Books the seat at that position for that showtime
   * 
   * @param seat     that is selected
   * @param showtime of the booking
   * 
   * @return boolean {@code true} when seat is booked
   */
  protected static boolean bookSeat(Seat seat, Showtime showtime) {
    int row = seat.getRow() + 1;
    int col = seat.getCol() + 1;
    showtime.getSeatAt(row, col).setBooked(true);
    Database.SHOWTIME.put(showtime.getShowtimeId(), showtime);
    Database.saveFileIntoDatabase(FileType.SHOWTIME);
    return true;
  }

  /**
   * Updates the ticket sales of {@link Movie}
   * 
   * @param showtime of the movie
   * @param ticket   of the booking
   * 
   * @return boolean {@code true} when ticket sales is updated
   */
  protected static boolean updateTicketSales(Showtime showtime, Ticket ticket) {
    Movie movie = showtime.getMovie();
    int newSales = ticket.getSeat().size();
    movie.setTicketSales(movie.getTicketSales() + newSales);
    Database.MOVIES.put(movie.getMovieId(), movie);
    Database.saveFileIntoDatabase(FileType.MOVIES);
    return true;
  }

  /**
   * Handles {@link Booking} checking
   * 
   * @param username of current user
   */
  public static void handleCheckBooking(String username) {
    String email;
    if (username.equals("")) {
      System.out.print("Enter your email: ");
      email = Helper.readString();
    } else {
      MovieGoer currentUser = (MovieGoer) UserManager.getUser(username);
      email = currentUser.getEmail();
    }
    BookingManager.findBooking(email);
  }
}
