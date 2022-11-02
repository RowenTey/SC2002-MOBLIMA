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
     * Constructor of Staff
     * 
     * @param userId   of staff
     * @param username of staff
     * @param password of staff
     * @param isStaff  whether user is staff
     */
    public Staff(String userId, String username, String password, boolean isStaff) {
        super(userId, username, password, isStaff);
    }
}
