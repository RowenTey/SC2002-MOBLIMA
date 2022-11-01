package src.model;

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
     * @param userId   of staff
     * @param username of staff
     * @param password of staff
     */
    public Staff(String userId, String username, String password) {
        super(userId, username);
        setPassword(password);
    }

    /**
     * Sets the password of the staff
     * 
     * @param password of staff
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the password of the staff
     * 
     * @return password of the staff
     */
    public String getPassword() {
        return password;
    }
}
