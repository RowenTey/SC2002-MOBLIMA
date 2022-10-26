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
     * List of bookings
     */
    private static ArrayList<Booking> bookingList = new ArrayList<Booking>();

    /**
     * Total number of bookings
     */
    private static int totalBookings;

    /**
     * Constructor of BookingManager
     */
    public BookingManager() {
        BookingManager.bookingList.clear();
        BookingManager.readBookings();
        BookingManager.totalBookings = bookingList.size();
    }

    /**
     * Get the number of bookings
     * 
     * @return the total number of bookings
     */
    public static int getTotalNumOfBooking() {
        return BookingManager.totalBookings;
    }

    public static ArrayList<Booking> getBookingList() {
        return BookingManager.bookingList;
    }

    /**
     * Read bookings data from database
     */
    public static void readBookings() {
        for (Booking booking : Database.BOOKINGS.values()) {
            BookingManager.bookingList.add(booking);
        }
    }

    /**
     * Constructor for booking in BookingManager
     *
     * @param price    the price of the ticket
     * @param seat     the seat of the ticket
     * @param cineplex the cineplex of the ticket
     * @param name     the user associated with the ticket
     */
    public static void createBooking(double price, Seat seat, Cineplex cineplex, MovieGoer movieGoer, String position,
            String movieTitle) {
        String cinemacode = seat.getShowtime().getCinemaCode(); // first two letters of location
        String timeShow = Helper.getTimeNow(); // get the current time
        timeShow = formatDate(timeShow);

        String transactionId = cinemacode + timeShow;
        /*
         * formats the transaction id as XXXYYYYMMDDhhmm (Y : year, M : month, D : day,
         * h : hour, m : minutes, XXX : cinema code in letters)
         */

        Booking newBooking = new Booking(transactionId, new Ticket(price, seat, cineplex, movieTitle), movieGoer,
                position);
        BookingManager.bookingList.add(newBooking);
        Database.BOOKINGS.put(transactionId, newBooking);
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
        System.out.println(String.format("%-25s: %s", "Mobile Number", movieGoer.getMobile()));
        System.out.println(String.format("%-25s: %s", "Email", movieGoer.getEmail()));
        System.out.println(String.format("%-25s: %s", "Age Group", movieGoer.getAgeGroup().getLabel()));
        System.out.println(String.format("%-25s: %s", "Movie Title", booking.getTicket().getMovieTitle()));
        System.out.println(String.format("%-25s: %s", "Location", booking.getTicket().getCineplex().getLocationStr()));
        System.out.println(String.format("%-25s: %s", "Seat", booking.getPosition()));
        System.out.println(String.format("%-25s: %s", "Price", booking.getTicket().getPrice()));
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

}
