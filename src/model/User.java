package src.model;

import java.io.Serializable;

/**
 * The base class that stores the basic information of the user
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-19
 */
public abstract class User implements Serializable {
    /**
     * For Java Serializable.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Name of the user
     */
    private String username;

    /**
     * User ID of user
     */
    private String userId;

    /**
     * Password of user
     */
    private String password;

    /**
     * Whether user is staff
     */
    private boolean isStaff;

    /**
     * Constructor of User
     * 
     * @param userId   of the user
     * @param username of the user
     * @param password of the user
     * @param isStaff  whether user is staff
     */
    public User(String userId, String username, String password, boolean isStaff) {
        setUserId(userId);
        setName(username);
        setPassword(password);
        setIsStaff(isStaff);
    }

    /**
     * Sets the ID of the user
     *
     * @param userId of the user
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Sets the name of the user
     *
     * @param name of the user
     */
    public void setName(String name) {
        this.username = name;
    }

    /**
     * Sets the password of the user
     *
     * @param password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets if user is staff
     *
     * @param isStaff whether user is staff
     */
    public void setIsStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }

    /**
     * Gets the ID of the user
     *
     * @return ID of the user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets the name of the current user
     *
     * @return name of the user
     */
    public String getName() {
        return username;
    }

    /**
     * Gets the password of the current user
     *
     * @return password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets if current user is staff
     *
     * @return boolean {@code true} if user is staff, {@code false} otherwise
     */
    public boolean getIsStaff() {
        return isStaff;
    }
}
