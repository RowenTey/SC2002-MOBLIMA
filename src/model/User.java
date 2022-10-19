package model;

/**
 * The abstract class that stores the name of the user
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-19
 */
public abstract class User {
    /**
     * Name of the user
     */
    private String userName;
    
    /**
     * Sets the name of the user
     *
     * @param name name of the user
     */
    public void setName(String name)
    {
        this.userName = name;
    }

    /**
     * Gets the name of the current user
     *
     * @return the name of the user
     */
    public String getName()
    {
        return this.userName;
    }
}
