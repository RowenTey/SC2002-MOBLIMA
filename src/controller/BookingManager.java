package controller;

import database.Database;
import database.FileType;
import helper.Helper;
import model.*;

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
        int uId = Helper.generateUniqueId(Database.BOOKINGS);
        String transactionId = String.format("B%04d", uId);
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
        System.out.println(String.format("%-20s: %s", "Seat", booking.getTicket().getSeat()));
        System.out.println(String.format("%-20s: %s", "Cineplex", booking.getTicket().getCineplex()));
        System.out.println(String.format("%-20s: %s", "Name", booking.getName()));
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println();
    }

}
