package main.java.Features;

import main.java.Users.Driver;
import main.java.Users.Passenger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that defines rides from origin A to destination B, offered by Drivers and accepted by Passengers
 * Note: in current version the ride does not have the date and time, however it may be implemented in the future
 *
 * @author Piotr Danielczyk
 * @version 1.0
 * @since 1.0
 */
public class Ride {
    private Address origin;
    private Address destination;
    private String carModel;
    private String licensePlate;
    private double pricePerPassenger;   // by default the price is in PLN
    private int maxNumberOfPassengers;
    private boolean isSmokingAccepted;
    private Driver driver;
    private List<Passenger> passengers;
    private boolean isOpen = true;    //if the ride is Open, then new passengers may be added (if the limit allows that)

    /**
     * Instantiates a new Ride.
     *
     * @param origin                the origin
     * @param destination           the destination
     * @param carModel              the car model
     * @param licensePlate          the license plate number
     * @param pricePerPassenger     the price per passenger
     * @param maxNumberOfPassengers the max number of passengers
     * @param isSmokingAccepted     boolean is smoking accepted
     * @param driver                the Driver
     */
    public Ride(Address origin, Address destination, String carModel, String licensePlate, double pricePerPassenger,
         int maxNumberOfPassengers, boolean isSmokingAccepted, Driver driver) {
        this.origin = origin;
        this.destination = destination;
        this.carModel = carModel;
        this.licensePlate = licensePlate;
        this.pricePerPassenger = pricePerPassenger;
        this.maxNumberOfPassengers = maxNumberOfPassengers;
        this.isSmokingAccepted = isSmokingAccepted;
        this.driver = driver;
        this.passengers = new ArrayList<Passenger>();
    }

    /**
     * when the ride starts, it becomes closed, so that new passengers cannot join.
     */
    public void startRide(){
        this.setOpen(false);
        System.out.println("The ride from " + this.getOrigin() + " to " + this.getDestination() + " has started!");
    }

    /**
     * when the ride is finished, each passenger and the driver should get +1 in their 'number of rides completed'.
     * Moreover, the driver should get +1 for each transported passenger in the 'number of passengers transported'.
     */
    public void finishRide(){
        for (int i = 0; i < passengers.size(); i++) {
            this.driver.incrementPassengersTransported();
            this.passengers.get(i).incrementRidesCompleted();
        }
        this.driver.incrementRidesCompleted();
        System.out.println("The ride from " + this.getOrigin() + " to " + this.getDestination() + " has ended!");
    }

    /**
     * Gets origin.
     *
     * @return the origin
     */
    public Address getOrigin() {
        return origin;
    }

    /**
     * Sets origin.
     *
     * @param origin the origin
     */
    public void setOrigin(Address origin) {
        this.origin = origin;
    }

    /**
     * Gets destination.
     *
     * @return the destination
     */
    public Address getDestination() {
        return destination;
    }

    /**
     * Sets destination.
     *
     * @param destination the destination
     */
    public void setDestination(Address destination) {
        this.destination = destination;
    }

    /**
     * Gets car model.
     *
     * @return the car model
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     * Sets car model.
     *
     * @param carModel the car model
     */
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    /**
     * Gets license plate.
     *
     * @return the license plate
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Sets license plate.
     *
     * @param licensePlate the license plate
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * Gets price per passenger. It is PLN by default.
     *
     * @return the price per passenger
     */
    public double getPricePerPassenger() {
        return pricePerPassenger;
    }

    /**
     * Sets price per passenger. It is PLN by default.
     *
     * @param pricePerPassenger the price per passenger
     */
    public void setPricePerPassenger(double pricePerPassenger) {
        this.pricePerPassenger = pricePerPassenger;
    }

    /**
     * Gets max number of passengers.
     *
     * @return the max number of passengers
     */
    public int getMaxNumberOfPassengers() {
        return maxNumberOfPassengers;
    }

    /**
     * Sets max number of passengers.
     *
     * @param maxNumberOfPassengers the max number of passengers
     */
    public void setMaxNumberOfPassengers(int maxNumberOfPassengers) {
        this.maxNumberOfPassengers = maxNumberOfPassengers;
    }

    /**
     * Is smoking accepted boolean.
     *
     * @return true if smoking is accepted, otherwise return false
     */
    public boolean isSmokingAccepted() {
        return isSmokingAccepted;
    }

    /**
     * Sets smoking accepted.
     *
     * @param smokingAccepted  true if smoking is accepted, otherwise false
     */
    public void setSmokingAccepted(boolean smokingAccepted) {
        isSmokingAccepted = smokingAccepted;
    }

    /**
     * Gets Driver.
     *
     * @return the Driver
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Sets Driver.
     *
     * @param driver the Driver
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Gets Passengers.
     *
     * @return the Passengers
     */
    public List<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * Add Passenger boolean.
     *
     * @param newPassenger the new Passenger
     * @return true if there was enough space to add the passenger, otherwise false
     */
    public boolean addPassenger(Passenger newPassenger) {
        if (this.getPassengers().size() < this.getMaxNumberOfPassengers()){
            this.passengers.add(newPassenger);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Remove Passenger boolean.
     *
     * @param removedPassenger the removed Passenger
     * @return true if passenger was successfully removed, false otherwise
     */
    public boolean removePassenger(Passenger removedPassenger) {
        if (this.passengers.remove(removedPassenger)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Is open boolean.
     * if the ride is Open, then new passengers may be added (if the limit allows that)
     * @return  true if the Ride is open, otherwise return false
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Sets open boolean.
     * if the ride is Open, then new passengers may be added (if the limit allows that)
     * @param open true if the Ride is open, otherwise false
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }
}
