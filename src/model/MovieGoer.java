package model;

import java.util.ArrayList;
import model.enums.AgeGroup;

/**
 * The class that represents a movie goer in the MOBLIMA system
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-19
 */
public class MovieGoer extends User {
    /**
     * mobile number of the MovieGoer
     */
    private String mobile;

    /**
     * email address of the the MovieGoer
     */
    private String email;

    /**
     * age group of the MovieGoer
     */
    private AgeGroup ageGroup;

    /**
     * A list that stores all the booking history of the MovieGoer
     */
    private ArrayList<Booking> bookings;

    /**
     * Constructor of MovieGoer
     * 
     * @param userId   This MovieGoer's userId
     * @param username This MovieGoer's username
     * @param mobile   This MovieGoer's mobile number
     * @param email    This MovieGoer's email address
     * @param ageGroup This MovieGoer's age group
     */
    public MovieGoer(String userId, String username, String mobile, String email, AgeGroup ageGroup) {
        super(userId, username);
        this.mobile = mobile;
        this.email = email;
        this.ageGroup = ageGroup;
        this.bookings = new ArrayList<>();
    }

    /**
     * Sets the mobile number of the MovieGoer
     * 
     * @param mobile the mobile number of the MovieGoer
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Gets the mobile number of the MovieGoer
     * 
     * @return the mobile number of the MovieGoer
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * Sets the email address of the MovieGoer
     * 
     * @param email the email address of the MovieGoer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the email address of the MovieGoer
     * 
     * @return email the email address of the MovieGoer
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the ageGroup of the MovieGoer
     * 
     * @param ageGroup the ageGroup of the MovieGoer
     */
    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    /**
     * Gets the ageGroup of the MovieGoer
     * 
     * @return ageGroup the ageGroup of the MovieGoer
     */
    public AgeGroup getAgeGroup() {
        return this.ageGroup;
    }

    /**
     * Sets the booking of the MovieGoer
     * 
     * @param bookings the booking of the MovieGoer
     */
    public void setBooking(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Gets the list of bookings of the MovieGoer
     * 
     * @return {@code bookings} the list of bookings of the MovieGoer
     */
    public ArrayList<Booking> getBooking() {
        return bookings;
    }
}
