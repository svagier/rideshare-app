package main.java.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.Features.Address;
import main.java.Features.Ride;

/**
 * The type Driver.
 * right now car model and car license plate are not obligatory - it may change in the future
 *
 * @author Piotr Danielczyk
 * @version 1.0
 * @since 1.0
 */
public class Driver extends User {
    private String defaultCarModel = null;
    private String defaultLicensePlate = null;
    private String driversID;
    private List<Ride> rides;
    private int passengersTransported = 0;

    /**
     * Instantiates a new Driver.
     *
     * @param name  username
     * @param first first name of the user
     * @param last  last name of the user
     * @param birth date of birth of the user
     * @param ID    driver's licence ID
     */
    public Driver(String name, String first, String last, Date birth, String ID){
        super(name, first, last, birth);
        this.driversID = ID;
        this.rides = new ArrayList<Ride>();
    }

    /**
     * Instantiates a new Driver with additional info: car model and license plate.
     *
     * @param name  username
     * @param first first name of the user
     * @param last  last name of the user
     * @param birth date of birth of the user
     * @param ID    driver's licence ID
     * @param carModel     the car model
     * @param licensePlate the license plate of the car
     */
    public Driver(String name, String first, String last, Date birth, String ID, String carModel, String licensePlate){
        super(name, first, last, birth);
        this.driversID = ID;
        this.defaultCarModel = carModel;
        this.defaultLicensePlate = licensePlate;
        this.rides = new ArrayList<Ride>();
    }

    /**
     * Create Ride.
     *
     * @param origin                the origin
     * @param destination           the destination
     * @param carModel              the car model
     * @param licensePlate          the license plate
     * @param pricePerPassenger     the price per passenger
     * @param maxNumberOfPassengers the max number of passengers
     * @param isSmokingAccepted     the is smoking accepted
     * @return creates  new Ride and adds it to the Driver's list of rides
     */
    public Ride createRide(Address origin, Address destination, String carModel, String licensePlate,
                           double pricePerPassenger, int maxNumberOfPassengers, boolean isSmokingAccepted, LocalDateTime startDateTime) {
        Ride newRide = new Ride(origin, destination, carModel, licensePlate, pricePerPassenger,
                                maxNumberOfPassengers, isSmokingAccepted,this, startDateTime);
        this.rides.add(newRide);
        System.out.println(this.getFullName() + " created new ride from " + origin + " to " + destination + ".");
        return(newRide);
    }

    /**
     * Prints general information (statistics) about the Driver
     */
    public void printStats(){
        String passengersPrefix;
        if (this.getPassengersTransported() == 1) {
            passengersPrefix = " passenger!";
        }
        else {
            passengersPrefix = " passengers!";
        }
        System.out.println(this.getFullName() + " has completed " + this.getRidesCompleted() + " rides and transported " + this.getPassengersTransported() + passengersPrefix);
    }

    /**
     * Gets default car model.
     *
     * @return the default car model
     */
    public String getDefaultCarModel() {
        return defaultCarModel;
    }

    /**
     * Sets default car model.
     *
     * @param defaultCarModel the default car model
     */
    public void setDefaultCarModel(String defaultCarModel) {
        this.defaultCarModel = defaultCarModel;
    }

    /**
     * Gets default license plate.
     *
     * @return the default license plate
     */
    public String getDefaultLicensePlate() {
        return defaultLicensePlate;
    }

    /**
     * Sets default license plate.
     *
     * @param defaultLicensePlate the default license plate
     */
    public void setDefaultLicensePlate(String defaultLicensePlate) {
        this.defaultLicensePlate = defaultLicensePlate;
    }

    /**
     * Gets drivers id.
     *
     * @return the drivers id
     */
    public String getDriversID() {
        return driversID;
    }

    /**
     * Sets drivers id.
     *
     * @param driversID the drivers id
     */
    public void setDriversID(String driversID) {
        this.driversID = driversID;
    }

    /**
     * Gets Rides.
     *
     * @return the Rides
     */
    public List<Ride> getRides() {
        return rides;
    }

    /**
     * Sets Rides.
     *
     * @param rides the Rides
     */
    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    /**
     * Gets total number of passengers transported.
     *  passengersTransported is a counter of all passengers transported in the Driver's history
     * @return the total number of passengers transported
     */
    public int getPassengersTransported() {
        return passengersTransported;
    }

    /**
     * Increment the number of passengers transported.
     *  passengersTransported is a counter of all passengers transported in the Driver's history
     */
    public void incrementPassengersTransported() {
        this.passengersTransported = this.passengersTransported + 1;
    }
}
