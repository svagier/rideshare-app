package Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Features.Address;
import Features.Ride;

public class Driver extends User {

    // right now car model and car license plate are not obligatory - it may change in the future
    private String defaultCarModel = null;
    private String defaultLicensePlate = null;
    private String driversID;
    private List<Ride> rides;
    private int passengersTransported = 0;  // counter of all passengers transported in the Driver's history

    /**
     * @param name  username
     * @param first first name of the user
     * @param last last name of the user
     * @param birth date of birth of the user
     * @param ID driver's licence ID
     */
    public Driver(String name, String first, String last, Date birth, String ID){
        super(name, first, last, birth);
        this.driversID = ID;
        this.rides = new ArrayList<Ride>();
    }

    /**
     *
     * @param name
     * @param first
     * @param last
     * @param birth
     * @param ID
     * @param carModel
     * @param licensePlate
     */
    public Driver(String name, String first, String last, Date birth, String ID, String carModel, String licensePlate){
        super(name, first, last, birth);
        this.driversID = ID;
        this.defaultCarModel = carModel;
        this.defaultLicensePlate = licensePlate;
        this.rides = new ArrayList<Ride>();
    }

    /**
     * @param origin
     * @param destination
     * @param carModel
     * @param licensePlate
     * @param pricePerPassenger
     * @param maxNumberOfPassengers
     * @param isSmokingAccepted
     * @return
     * creates new Ride and adds it to the Driver's list of rides
     */
    public Ride createRide(Address origin, Address destination, String carModel, String licensePlate,
                            double pricePerPassenger, int maxNumberOfPassengers, boolean isSmokingAccepted) {
        Ride newRide = new Ride(origin, destination, carModel, licensePlate, pricePerPassenger,
                                maxNumberOfPassengers, isSmokingAccepted,this);
        this.rides.add(newRide);
        System.out.println(this.getFullName() + " created new ride from " + origin + " to " + destination + ".");
        return(newRide);
    }


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

    public String getDefaultCarModel() {
        return defaultCarModel;
    }

    public void setDefaultCarModel(String defaultCarModel) {
        this.defaultCarModel = defaultCarModel;
    }

    public String getDefaultLicensePlate() {
        return defaultLicensePlate;
    }

    public void setDefaultLicensePlate(String defaultLicensePlate) {
        this.defaultLicensePlate = defaultLicensePlate;
    }

    public String getDriversID() {
        return driversID;
    }

    public void setDriversID(String driversID) {
        this.driversID = driversID;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public int getPassengersTransported() {
        return passengersTransported;
    }

    public void incrementPassengersTransported() {
        this.passengersTransported = this.passengersTransported + 1;
    }
}
