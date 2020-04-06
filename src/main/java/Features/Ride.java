package main.java.Features;

/**
 * Class that defines rides from origin A to destination B, offered by Drivers and accepted by Passengers
 */

import main.java.Users.Driver;
import main.java.Users.Passenger;

import java.util.ArrayList;
import java.util.List;

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
    // in current version the ride does not have the date and time, however it will be implemented soon

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

    public Address getOrigin() {
        return origin;
    }

    public void setOrigin(Address origin) {
        this.origin = origin;
    }

    public Address getDestination() {
        return destination;
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getPricePerPassenger() {
        return pricePerPassenger;
    }

    public void setPricePerPassenger(double pricePerPassenger) {
        this.pricePerPassenger = pricePerPassenger;
    }

    public int getMaxNumberOfPassengers() {
        return maxNumberOfPassengers;
    }

    public void setMaxNumberOfPassengers(int maxNumberOfPassengers) {
        this.maxNumberOfPassengers = maxNumberOfPassengers;
    }

    public boolean isSmokingAccepted() {
        return isSmokingAccepted;
    }

    public void setSmokingAccepted(boolean smokingAccepted) {
        isSmokingAccepted = smokingAccepted;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public boolean addPassenger(Passenger newPassenger) {
        if (this.getPassengers().size() < this.getMaxNumberOfPassengers()){
            this.passengers.add(newPassenger);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean removePassenger(Passenger removedPassenger) {
        if (this.passengers.remove(removedPassenger)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
