package model;

import java.io.Serializable;

/**
 * The base class that stores the name of the user
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-19
 */
public class User implements Serializable {
    /**
     * For Java Serializable.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Name of the user
     */
    private String username;

    /**
     * user ID of user
     */
    private String userId;

    /**
     * Constructor of User
     * 
     * @param username user's username
     */
    public User(String userId, String username) {
        setUserId(userId);
        setName(username);
    }

    /**
     * Sets the ID of the user
     *
     * @param userId ID of the user
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Sets the name of the user
     *
     * @param name name of the user
     */
    public void setName(String name) {
        this.username = name;
    }

    /**
     * Gets the ID of the user
     *
     * @return the ID of the user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets the name of the current user
     *
     * @return the name of the user
     */
    public String getName() {
        return this.username;
    }
}
