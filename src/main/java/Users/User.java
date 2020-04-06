package Users;

import java.util.Date;


/**
 * Basic User class after which all specific types of users inherit.
 * @author      Piotr Danielczyk
 * @version     %I%, %G%
 * @since       1.0
 */

abstract class User {
    private String username;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int ridesCompleted = 0;


    /**
     * Instantiates a new User.
     *
     * @param name  the name
     * @param first the first
     * @param last  the last
     * @param birth the birth
     */
    public User(String name, String first, String last, Date birth) {
        this.username = name;
        this.firstName = first;
        this.lastName = last;
        this.dateOfBirth = birth;
    }

    /**
     * Get full name string.
     *
     * @return the string
     */
    public String getFullName(){
        return this.firstName + ' ' + this.lastName;
    }

    /**
     * Print stats.
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
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets rides completed.
     *
     * @return the rides completed
     */
    public int getRidesCompleted() {
        return ridesCompleted;
    }

    /**
     * Increment rides completed.
     */
    public void incrementRidesCompleted() {
        this.ridesCompleted = this.ridesCompleted + 1;
    }
}
