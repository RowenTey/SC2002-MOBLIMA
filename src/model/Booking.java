package model;

import java.io.Serializable;

/**
 * The class that stores a booking
 * 
 * @author Ace Ang
 * @version 1.0
 * @since 2022-10-20
 */

public class Booking implements Serializable {
    /**
     * For Java Serializable.
     */
    private static final long serialVersionUID = 3L;

    /**
     * transaction ID for booking
     */
    private String transactionId;

    /**
     * ticket for booking
     */
    private Ticket ticket;

    /**
     * name of the customer of the booking
     */
    private String name;

    /**
     * mobile number of the customer
     */
    private String mobileNum;

    /**
     * email address of the customer
     */
    private String emailAddr;

    /**
     * Gets the transaction ID of the booking
     *
     * @return the transaction ID of the booking
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction ID of the booking
     *
     * @param transactionId transaction ID of the booking
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the ticket of the booking
     *
     * @return the ticket of the booking
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * Sets the ticket of the booking
     *
     * @param ticket ticket of the booking
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /**
     * Gets the name of the booking
     *
     * @return the name of the booking
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the booking
     *
     * @param name name of the booking
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the mobile number of the customer
     *
     * @return the mobile number of the customer
     */
    public String getMobileNum() {
        return mobileNum;
    }

    /**
     * Sets the mobile number of the customer
     *
     * @param mobileNum mobile number of the customer
     */
    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    /**
     * Gets the email address of the customer
     *
     * @return the email address of the customer
     */
    public String getEmailAddr() {
        return emailAddr;
    }

    /**
     * Sets the email address of the customer
     *
     * @param emailAddr email address of the customer
     */
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }
}