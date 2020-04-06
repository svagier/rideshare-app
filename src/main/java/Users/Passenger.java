package main.java.Users;
import java.util.Date;

import main.java.Features.Ride;

public class Passenger extends User{
    protected boolean isSmoker;
    protected boolean isTransportingPet;

    // by default, the passenger is considered non-smoker and not transporting a pet animal
    public Passenger(String name, String first, String last, Date birth) {
        super(name, first, last, birth);
        this.isSmoker = false;
        this.isTransportingPet = false;
    }

    public Passenger(String name, String first, String last, Date birth, boolean isSmoker, boolean isTransportingPet) {
        super(name, first, last, birth);
        this.isSmoker = isSmoker;
        this.isTransportingPet = isTransportingPet;
    }

    /**
     * adds the Passenger to a given Ride. Returns false if the attempt failed.
     */
    public boolean joinRide(Ride ride) {
        if (ride.isOpen()){
            if (ride.addPassenger(this)){   // if adding new passenger was successful
                System.out.println("Successfully added " + this.getFullName() + " to the ride.");
                return true;
            }
            else {  // if the list of passengers was full
                System.out.println("Unable to add " + this.getFullName() + " to the ride. Ride full.");
                return false;
            }
        }
        else {  // the ride already started or was cancelled
            System.out.println("Unable to add " + this.getFullName() + " to the ride. Ride already started or was cancelled.");
            return false;
        }
    }

    /**
     * Removes the Passenger from a given ride. Returns false if the attempt failed.
     */
    public boolean cancelRide(Ride ride) {
        if (ride.removePassenger(this)) {
            System.out.println("Passenger " + this.getFullName() + " successfully removed from the ride.");
            return true;
        }
        else {
            System.out.println("Passenger " + this.getFullName() + " was NOT removed from the ride.");
            return false;
        }
    }

    protected boolean isSmoker() {
        return isSmoker;
    }

    protected void setSmoker(boolean smoker) {
        isSmoker = smoker;
    }

    protected boolean isTransportingPet() {
        return isTransportingPet;
    }

    protected void setTransportingPet(boolean transportingPet) {
        isTransportingPet = transportingPet;
    }
}
