package model

import java.util.ArrayList;

/**
 * The class that represents a movie goer in the MOBLIMA system
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-19
 */
public class Movie_Goer extends User {
    /**
     * mobile number of the Movie_Goer
     */
    private String mobile;
    
    /**
     * email address of the the Movie_Goer
     */  
    private String email;
  
    /**
     * agegroup of the Movie_Goer
     */
    private AgeGroup agegroup;
  
    /**
     * A list that stores all the booking history of the Movie_Goer
     */
    private ArrayList<Booking> bookings; 

  
    /**
     * Constructor of Movie_Goer
     * 
     * @param mobile      This Movie_Goer's mobile number
     * @param email       This Movie_Goer's email address
     * @param ageGroup    This Movie_Goer's agegroup
     */
    public Movie_Goer(String mobile, String email, AgeGroup ageGroup) 
    {
        this.mobile = mobile;
        this.email = email;
        this.agegroup = ageGroup;
        this.bookings = new ArrayList<>();
    }

    /**
     * Sets the mobile number of the Movie_Goer
     * 
     * @param mobile the mobile number of the Movie_Goer
     */
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    /**
     * Gets the mobile number of the Movie_Goer
     * 
     * @return mobile the mobile number of the Movie_Goer
     */
    public String getMobile()
    {
        return this.mobile;
    }

    /**
     * Sets the email address of the Movie_Goer
     * 
     * @param email the email address of the Movie_Goer
     */ 
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Gets the email address of the Movie_Goer
     * 
     * @return email the email address of the Movie_Goer
     */ 
    public String getEmail()
    {
        return this.email;
    }

    /**
     * Sets the agegroup of the Movie_Goer
     * 
     * @param agegroup the agegroup of the Movie_Goer
     */ 
    public void setAgeGroup(AgeGroup ageGroup)
    {
        this.agegroup = ageGroup;
    }

    /**
     * Gets the agegroup of the Movie_Goer
     * 
     * @return agegroup the agegroup of the Movie_Goer
     */ 
    public AgeGroup getAgeGroup()
    {
        return this.agegroup;
    }

    /**
     * Sets the booking of the Movie_Goer
     * 
     * @param bookings the booking of the Movie_Goer
     */  
    public setBooking(ArrayList<Booking> bookings)
    {
        this.bookings = bookings;
    }

    /**
     * Gets the list of bookings of the Movie_Goer
     * 
     * @return bookings the list of bookings of the Movie_Goer
     */  
    public ArrayList<Booking> getBooking()
    {
        return bookings;
    }
}
