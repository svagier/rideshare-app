package Users;

import java.util.Date;

/**
 * Basic User class after which all specific types of users inherit.
 */

abstract class User {
    private String username;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int ridesCompleted = 0;


    public User(String name, String first, String last, Date birth) {
        this.username = name;
        this.firstName = first;
        this.lastName = last;
        this.dateOfBirth = birth;
    }

    public String getFullName(){
        return this.firstName + ' ' + this.lastName;
    }

    public void printStats(){
        System.out.println(this.getFullName() + " has completed " + this.ridesCompleted + " rides!");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getRidesCompleted() {
        return ridesCompleted;
    }

    public void incrementRidesCompleted() {
        this.ridesCompleted = this.ridesCompleted + 1;
    }
}
