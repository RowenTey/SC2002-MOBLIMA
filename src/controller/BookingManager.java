package controller;

import database.Database;
import database.FileType;
import helper.Helper;
import model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Booking Manager
 *
 * @author Ace Ang
 * @version 1.0
 * @since 2022-10-22
 */

public class BookingManager {

    /**
     * Constructor for ticket in BookingManager
     *
     * @param price    the price of the ticket
     * @param seat     the seat of the ticket
     * @param cineplex the cineplex of the ticket
     */
    public static void createTicket(double price, Seat seat, Cineplex cineplex) {
        Ticket newTicket = new Ticket(price, seat, cineplex);
    }

    /**
     * Constructor for booking in BookingManager
     *
     * @param price    the price of the ticket
     * @param seat     the seat of the ticket
     * @param cineplex the cineplex of the ticket
     * @param name     the user associated with the ticket
     */
    public static void createBooking(double price, Seat seat, Cineplex cineplex, String name) {
        String cinemacode = seat.getShowtime().getCinemaCode(); // first two letters of location
        Date timeShow = seat.getShowtime().getTime(); // get the date and time of the show
        DateFormat targetFormat = new SimpleDateFormat("yyyyMMddhhmm"); // target config to parse into
        String formattedDate = targetFormat.format(timeShow); // parses the date and time of the show into the above

        String transactionId = String.format(cinemacode + formattedDate);
        /*
         * formats the transaction id as XXXYYYYMMDDhhmm (Y : year, M : month, D : day,
         * h : hour, m : minutes, XXX : cinema code in letters)
         */

        Booking newBooking = new Booking(transactionId, new Ticket(price, seat, cineplex), name);
        Database.BOOKINGS.put(transactionId, newBooking);
        Database.saveFileIntoDatabase(FileType.BOOKINGS);
        System.out.println("Booking created! Booking Details: ");
        printBookingDetails(newBooking);
    }

    /**
     * Print the complete details of the booking
     *
     * @param guest {@link Booking} object to print
     */
    public static void printBookingDetails(Booking booking) {
        System.out.println();
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println(String.format("%-20s: %s", "Price", booking.getTicket().getPrice()));
        System.out.println(String.format("%-20s: %s", "Seat", booking.getTicket().getSeat().getPos()));
        System.out.println(String.format("%-20s: %s", "Cineplex", booking.getTicket().getCineplex().getLocation()));
        System.out.println(String.format("%-20s: %s", "Name", booking.getName()));
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println();
    }

}