package src.model;

import java.util.ArrayList;
import src.model.enums.AgeGroup;

/**
 * The class that represents a movie goer in the MOBLIMA system
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-19
 */
public class MovieGoer extends User {
    /**
     * Mobile number of movie goer
     */
    private String mobile;

    /**
     * Email address of movie goer
     */
    private String email;

    /**
     * {@link AgeGroup} of movie goer
     */
    private AgeGroup ageGroup;

    /**
     * A list that stores all the {@link Booking} of movie goer
     */
    private ArrayList<Booking> bookings;

    /**
     * Constructor of movie goer
     * 
     * @param userId   of movie goer
     * @param username of movie goer
     * @param mobile   number of movie goer
     * @param email    address of movie goer
     * @param ageGroup {@link AgeGroup} of movie goer
     */
    public MovieGoer(String userId, String username, String mobile, String email, AgeGroup ageGroup) {
        super(userId, username);
        setMobile(mobile);
        setEmail(email);
        setAgeGroup(ageGroup);
        this.bookings = new ArrayList<>();
    }

    /**
     * Sets the mobile number of the movie goer
     * 
     * @param mobile number of movie goer
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Gets the mobile number of the movie goer
     * 
     * @return mobile number of the movie goer
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the email address of the movie goer
     * 
     * @param email address of the movie goer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the email address of the movie goer
     * 
     * @return email address of the movie goer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the {@link AgeGroup} of the movie goer
     * 
     * @param ageGroup of the movie goer
     */
    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    /**
     * Gets the {@link AgeGroup} of the movie goer
     * 
     * @return ageGroup of the movie goer
     */
    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    /**
     * Sets the {@link Booking} of the movie goer
     * 
     * @param bookings of the movie goer
     */
    public void setBooking(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Gets the list of {@link Booking} of the movie goer
     * 
     * @return {@code ArrayList<Booking>} list of bookings of the movie goer
     */
    public ArrayList<Booking> getBooking() {
        return bookings;
    }
}
