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
    private String userName;

    /**
     * Sets the name of the user
     *
     * @param name name of the user
     */
    public void setName(String name) {
        this.userName = name;
    }

    /**
     * Gets the name of the current user
     *
     * @return the name of the user
     */
    public String getName() {
        return this.userName;
    }
}
