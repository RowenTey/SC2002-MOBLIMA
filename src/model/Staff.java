package model;

/**
 * The class that represents a staff in the MOBLIMA system
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-19
 */
public class Staff extends User {

    /**
     * Staff's password for enabling admin functions
     */
    private String password;

    /**
     * Constructor of Staff
     * 
     * @param username Staff's username
     * @param password Staff's password
     */
    public Staff(String username, String password) {
        super(username);
        setPassword(password);
    }

    /**
     * Sets the password of the staff
     * 
     * @param password Staff's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the password of the staff
     * 
     * @return the password of the staff
     */
    public String getPassword() {
        return this.password;
    }
}
