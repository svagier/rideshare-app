package main.java.Users;

import main.java.Features.Ride;

/**
 * The type Passenger.
 *
 * @author Piotr Danielczyk
 * @version 1.4
 * @since 1.0
 */
public class Passenger extends User{

    /**
     * The enum Preferred seat, which describes which seat the Passenger would prefer to take in Rides.
     */
    public enum PreferredSeat {
        FRONT,
        BACK_LEFT,
        BACK_RIGHT,
        BACK_MIDDLE,
        BACK_ANY_SIDE,
        BACK_ANY,
        ANY
    }

    protected boolean isSmoker;
    protected boolean isTransportingPet;
    private PreferredSeat preferredSeat;

    /**
     * Instantiates a new Passenger.
     * by default, the passenger is considered non-smoker and not transporting a pet animal.
     * default seat preference is ANY.
     *
     * @param name  username
     * @param first first name of the user
     * @param last  last name of the user
     */
    public Passenger(String name, String first, String last) {
        super(name, first, last);
        this.isSmoker = false;
        this.isTransportingPet = false;
        this.preferredSeat = PreferredSeat.ANY;
    }

    /**
     * Instantiates a new Passenger with custom settings for smoking, preferred seat and transporting pet.
     *
     * @param name              username
     * @param first             first name of the user
     * @param last              last name of the user
     * @param isSmoker          the is smoker boolean
     * @param isTransportingPet the is transporting pet boolean
     * @param seat              preferences in seating in a car as passenger
     */
    public Passenger(String name, String first, String last, boolean isSmoker, boolean isTransportingPet, PreferredSeat seat) {
        super(name, first, last);
        this.isSmoker = isSmoker;
        this.isTransportingPet = isTransportingPet;
        this.preferredSeat = seat;
    }

    /**
     * adds the Passenger to a given Ride.
     *
     * @param ride the Ride
     * @return True if the attempt was successful, otherwise return false
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
     * Removes the Passenger from a given Ride
     *
     * @param ride the Ride
     * @return True if the attempt was successful, otherwise return false
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

    /**
     * Is smoker boolean.
     *
     * @return true if is smoker, false otherwise
     */
    protected boolean isSmoker() {
        return isSmoker;
    }

    /**
     * Sets smoker.
     *
     * @param smoker  true if is smoker, false otherwise
     */
    protected void setSmoker(boolean smoker) {
        isSmoker = smoker;
    }

    /**
     * Is transporting pet boolean.
     *
     * @return true if is transporting pet, false otherwise
     */
    protected boolean isTransportingPet() {
        return isTransportingPet;
    }

    /**
     * Sets transporting pet.
     *
     * @param transportingPet true if is transporting pet, false otherwise
     */
    protected void setTransportingPet(boolean transportingPet) {
        isTransportingPet = transportingPet;
    }

    /**
     * Gets preferred seat.
     *
     * @return the preferred seat
     */
    public PreferredSeat getPreferredSeat() {
        return preferredSeat;
    }

    /**
     * Sets preferred seat.
     *
     * @param preferredSeat the preferred seat
     */
    public void setPreferredSeat(PreferredSeat preferredSeat) {
        this.preferredSeat = preferredSeat;
    }
}
