package controller;

import database.Database;
import database.FileType;
import helper.Helper;
import model.*;
import model.enums.AgeGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Booking Manager
 *
 * @author Ace Ang
 * @version 1.0
 * @since 2022-10-22
 */

public class BookingManager {
    /**
     * Constructor for booking in BookingManager
     *
     * @param price    the price of the ticket
     * @param seat     the seat of the ticket
     * @param cineplex the cineplex of the ticket
     * @param name     the user associated with the ticket
     */
    public static void createBooking(double price, Seat seat, Cineplex cineplex, MovieGoer movieGoer, String position) {
        String cinemacode = seat.getShowtime().getCinemaCode(); // first two letters of location
        String timeShow = Helper.getTimeNow(); // get the current time
        timeShow = formatDate(timeShow);

        String transactionId = cinemacode + timeShow;
        /*
         * formats the transaction id as XXXYYYYMMDDhhmm (Y : year, M : month, D : day,
         * h : hour, m : minutes, XXX : cinema code in letters)
         */

        Booking newBooking = new Booking(transactionId, new Ticket(price, seat, cineplex), movieGoer.getName(),
                movieGoer.getMobile(), movieGoer.getEmail());
        Database.BOOKINGS.put(transactionId, newBooking);
        Database.saveFileIntoDatabase(FileType.BOOKINGS);
        System.out.println();
        System.out.println("Booking created! Your ticket is printed below: ");
        printBookingDetails(newBooking, position);
    }

    /**
     * Print the complete details of the booking
     *
     * @param guest {@link Booking} object to print
     */
    public static void printBookingDetails(Booking booking, String position) {
        System.out.println();
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println(String.format("%-30s: %s", "Transaction ID", booking.getTransactionId()));
        System.out.println(String.format("%-30s: %s", "Name", booking.getName()));
        System.out.println(String.format("%-30s: %s", "Mobile Number", booking.getMobileNum()));
        System.out.println(String.format("%-30s: %s", "Email", booking.getEmailAddr()));
        System.out.println(String.format("%-30s: %s", "Location", booking.getTicket().getCineplex().getLocationStr()));
        System.out.println(String.format("%-30s: %s", "Seat", position));
        System.out.println(String.format("%-30s: %s", "Price", booking.getTicket().getPrice()));
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println();
    }

    /**
     * Prompt User's information
     */
    public static MovieGoer promptUserDetails() {
        System.out.print("\nEnter your name: ");
        String name = Helper.readString();
        System.out.print("Enter your mobile number (eg: +65-88889770): ");
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

}
