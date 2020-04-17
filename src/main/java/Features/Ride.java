package main.java.Features;

import main.java.Users.Driver;
import main.java.Users.Passenger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Class that defines rides from origin A to destination B, offered by Drivers and accepted by Passengers
 *
 * @author Piotr Danielczyk
 * @version 1.4
 * @since 1.0
 */
public class Ride {
    /**
     * The enum Reason for driving, which describes why the Driver decided to create this Ride.
     * By default UNSPECIFIED is chosen
     */
    enum ReasonForDriving {
        PURELY_FOR_PROFIT,
        REDUCING_COST_OF_TRAVEL,
        MEETING_NEW_PEOPLE,
        HELPING_PEOPLE,
        UNSPECIFIED
    }
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
    private LocalDateTime rideStartDateTime;
    private LocalDateTime rideEndDateTime;
    private ReasonForDriving rideReason;

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
     * @param startDateTime         the start date time
     * @param reason                the reason why the driver created the ride (enum)
     */

    public Ride(Address origin, Address destination, String carModel, String licensePlate, double pricePerPassenger,
                int maxNumberOfPassengers, boolean isSmokingAccepted, Driver driver, LocalDateTime startDateTime, ReasonForDriving reason) {
        this.origin = origin;
        this.destination = destination;
        this.carModel = carModel;
        this.licensePlate = licensePlate;
        this.pricePerPassenger = pricePerPassenger;
        this.maxNumberOfPassengers = maxNumberOfPassengers;
        this.isSmokingAccepted = isSmokingAccepted;
        this.driver = driver;
        this.passengers = new ArrayList<Passenger>();
        this.rideStartDateTime = startDateTime;
        this.rideEndDateTime = null;
        this.rideReason = reason;
    }

    /**
     * Instantiates a new Ride, without info about ride reason, which is not compulsory.
     *
     * @param origin                the origin
     * @param destination           the destination
     * @param carModel              the car model
     * @param licensePlate          the license plate number
     * @param pricePerPassenger     the price per passenger
     * @param maxNumberOfPassengers the max number of passengers
     * @param isSmokingAccepted     boolean is smoking accepted
     * @param driver                the Driver
     * @param startDateTime         the start date time
     */
    public Ride(Address origin, Address destination, String carModel, String licensePlate, double pricePerPassenger,
         int maxNumberOfPassengers, boolean isSmokingAccepted, Driver driver, LocalDateTime startDateTime) {
        this.origin = origin;
        this.destination = destination;
        this.carModel = carModel;
        this.licensePlate = licensePlate;
        this.pricePerPassenger = pricePerPassenger;
        this.maxNumberOfPassengers = maxNumberOfPassengers;
        this.isSmokingAccepted = isSmokingAccepted;
        this.driver = driver;
        this.passengers = new ArrayList<Passenger>();
        this.rideStartDateTime = startDateTime;
        this.rideEndDateTime = null;
        this.rideReason = ReasonForDriving.UNSPECIFIED;
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
     * @param smokingAccepted true if smoking is accepted, otherwise false
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
     * when the ride starts, it becomes closed, so that new passengers cannot join.
     */
    public void getRideInfo(){
        if (this.getRideEndDateTime() == null)
            System.out.println("The ride from " + this.getOrigin() + " to " + this.getDestination() + ", start: " + this.getRideStartDateTime());
        else
            System.out.println("The ride from " + this.getOrigin() + " to " + this.getDestination() + ", start: " + this.getRideStartDateTime() + ", end: " + this.getRideEndDateTime());

    }

    /**
     * Is open boolean.
     * if the ride is Open, then new passengers may be added (if the limit allows that)
     *
     * @return true if the Ride is open, otherwise return false
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Sets open boolean.
     * if the ride is Open, then new passengers may be added (if the limit allows that)
     *
     * @param open true if the Ride is open, otherwise false
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }


    /**
     * Gets date and time of the start of ride
     *
     * @return date and time of the start of ride
     */
    public LocalDateTime getRideStartDateTime() {
        return rideStartDateTime;
    }

    /**
     * Sets date and time of the start of ride
     *
     * @param rideStartDateTime date and time of the start of ride
     */
    public void setRideStartDateTime(LocalDateTime rideStartDateTime) {
        this.rideStartDateTime = rideStartDateTime;
    }

    /**
     * Gets date and time of the end of the ride
     *
     * @return date and time of the ride
     */
    public LocalDateTime getRideEndDateTime() {
        return rideEndDateTime;
    }

    /**
     * Sets ride end date time.
     * Remember that this setter HAS TO be called when you want to give any value to rideEndDateTime for the first time.
     *
     * @param rideEndDateTime date and time of the end of the ride
     */
    public void setRideEndDateTime(LocalDateTime rideEndDateTime) {
        this.rideEndDateTime = rideEndDateTime;
    }

    /**
     * Sets ride end date time.
     * Remember that this setter HAS TO be called when you want to give any value to rideEndDateTime for the first time.
     *
     * @param rideEndDate date of the end of the ride
     * @param rideEndTime time of the end of the ride
     */
    public void setRideEndDateTime(LocalDate rideEndDate, LocalTime rideEndTime) {
        this.rideEndDateTime = LocalDateTime.of(rideEndDate, rideEndTime);
    }

    /**
     * Gets date of the end of the ride
     *
     * @return date of the ride
     */
    public LocalDate getRideEndDate() {
        return rideEndDateTime.toLocalDate();
    }

    /**
     * Sets ride end date.
     * The name of this setter starts with 'change' to put emphasis on the below statement:
     * Remember that setRideEndDateTime setter HAS TO be called when you want to give any value to rideEndDateTime for the first time.
     * Initiating (for the first time) rideEndDateTime with below setter will throw an error.
     *
     * @param rideEndDate date of the end of the ride
     */
    public void changeRideEndDate(LocalDate rideEndDate) {
        if (this.getRideEndDateTime() == null)
            throw new NullPointerException("You need to set rideEndDateTime first using setRideEndDateTime!");
        this.rideEndDateTime = LocalDateTime.of(rideEndDate, this.getRideEndTime());
    }

    /**
     * Gets time of the end of the ride
     *
     * @return time of the ride
     */
    public LocalTime getRideEndTime() {
        return rideEndDateTime.toLocalTime();
    }

    /**
     * Sets ride end time.
     * The name of this setter starts with 'change' to put emphasis on the below statement:
     * Remember that setRideEndDateTime setter HAS TO be called when you want to give any value to rideEndDateTime for the first time.
     * Initiating (for the first time) rideEndDateTime with below setter will throw an error.
     *
     * @param rideEndTime time of the end of the ride
     */
    public void changeRideEndTime(LocalTime rideEndTime) {
        if (this.getRideEndDateTime() == null)
            throw new NullPointerException("You need to set rideEndDateTime first using setRideEndDateTime!");
        LocalDateTime newDateTime = LocalDateTime.of(this.getRideEndDate(), rideEndTime);
        this.rideEndDateTime = newDateTime;
    }

    /**
     * Gets date of the start of ride
     *
     * @return date of the start of ride
     */
    public LocalDate getRideStartDate() {
        return rideStartDateTime.toLocalDate();
    }

    /**
     * Sets date of the start of ride
     *
     * @param rideStartDate date of the start of ride
     */
    public void setRideStartDate(LocalDate rideStartDate) {
        this.rideStartDateTime = LocalDateTime.of(rideStartDate, this.getRideStartTime());
    }

    /**
     * Gets time of the start of ride
     *
     * @return time of the start of ride
     */
    public LocalTime getRideStartTime() {
        return rideStartDateTime.toLocalTime();
    }

    /**
     * Sets time of the start of ride
     *
     * @param rideStartTime time of the start of ride
     */
    public void setRideStartTime(LocalTime rideStartTime) {
        this.rideStartDateTime = LocalDateTime.of(this.getRideStartDate(), rideStartTime);
    }

    /**
     * Gets Ride reason.
     *
     * @return the ride reason - enum ReasonForDriving
     */
    public ReasonForDriving getRideReason() {
        return rideReason;
    }

    /**
     * Sets Ride reason.
     *
     * @param rideReason the ride reason - enum ReasonForDriving
     */
    public void setRideReason(ReasonForDriving rideReason) {
        this.rideReason = rideReason;
    }
}
