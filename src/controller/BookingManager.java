package controller;

import database.Database;
import database.FileType;
import helper.Helper;
import model.*;
import model.enums.AgeGroup;

import java.text.DecimalFormat;
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
    public static ArrayList<Booking> bookingList = new ArrayList<Booking>();

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

    private static ArrayList<Booking> getBookingList() {
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
     * 2 dp constructor
     */
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Creates a transaction ID for booking
     */
    public static String createTransactionId (Seat seat){
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
    
    //TODO work out logic for holidays
//
//        multiplier*=1.07; // 7% GST
//        System.out.printf("\nTotal multiplier: %f",multiplier);
//        adjustedPrice *= multiplier;
//        Ticket newTicket = new Ticket(adjustedPrice, seat, cineplex, movieTitle);
//
//        return newTicket;
//    }

    /**
     * Creates a ticket for the createBooking method
     */
    public static Ticket createBookingTicket(double price, Seat seat, Cinema cinema,String movieTitle){
        double adjustedPrice = price;
        double multiplier = 1.07;

        adjustedPrice *= multiplier;
        if(cinema.getIsPlatinum()){
            adjustedPrice += 5; //extra $5 for platinum cinema
        }
        Ticket newTicket = new Ticket(adjustedPrice, seat, cinema, movieTitle);

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
    public static void createBooking(double price, Seat seat, Cinema cinema, MovieGoer movieGoer, String position,
                                     String movieTitle) {

        String newTransactionId = createTransactionId(seat);
        Ticket newTicket = createBookingTicket(price,seat,cinema,movieTitle);
        //Ticket newTicket = createBookingTicket(price,seat,cineplex,movieTitle,cinema); //to be implemented
        Booking newBooking = new Booking(newTransactionId, newTicket, movieGoer,
                position);
        BookingManager.bookingList.add(newBooking);
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
        System.out.println(String.format("%-25s: %s", "Mobile Number", movieGoer.getMobile()));
        System.out.println(String.format("%-25s: %s", "Email", movieGoer.getEmail()));
        System.out.println(String.format("%-25s: %s", "Ticket Type", movieGoer.getAgeGroup().getLabel()));
        System.out.println(String.format("%-25s: %s", "Movie Title", booking.getTicket().getMovieTitle()));
        System.out.println(String.format("%-25s: %s", "Cinema", booking.getTicket().getCinema().getCinemaCode()));
        System.out.println(String.format("%-25s: %s", "Cinema Type", booking.getTicket().getCinema().getIsPlatinum()? "Platinum": "Not Platinum"));
        System.out.println(String.format("%-25s: %s", "Location", booking.getTicket().getCinema().getCineplex().getLocationStr()));
        System.out.println(String.format("%-25s: %s", "Seat", booking.getPosition()));
        System.out.println(String.format("%-25s: %s", "Price", df.format(booking.getTicket().getPrice())));
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

}
