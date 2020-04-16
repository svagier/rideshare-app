package main.java.Users;



/**
 * Basic User class after which all specific types of users inherit.
 * @author      Piotr Danielczyk
 * @version     1.0
 * @since       1.0
 */
abstract class User {
    private String username;
    private String firstName;
    private String lastName;
    private int ridesCompleted = 0;


    /**
     * Instantiates a new User.
     *
     * @param name  username
     * @param first first name of the user
     * @param last  last name of the user
     */
    public User(String name, String first, String last) {
        this.username = name;
        this.firstName = first;
        this.lastName = last;
    }

    /**
     * Get full name string.
     *
     * @return the full name of the user string
     */
    public String getFullName(){
        return this.firstName + ' ' + this.lastName;
    }

    /**
     * Print stats - how many Rides were completed.
     */
    public void printStats(){
        System.out.println(this.getFullName() + " has completed " + this.ridesCompleted + " rides!");
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets total number of rides completed.
     *
     * @return the rides completed
     */
    public int getRidesCompleted() {
        return ridesCompleted;
    }

    /**
     * Increment total number of rides completed by 1.
     */
    public void incrementRidesCompleted() {
        this.ridesCompleted = this.ridesCompleted + 1;
    }
}
