package main.java.Features;

import java.util.Comparator;

/**
 * The type Ride Price Comparator, which compares 2 rides on the basis of their price per passenger.
 * @author Piotr Danielczyk
 * @version 1.4
 * @since 1.2
 */
public class RidePriceComparator implements Comparator<Ride> {

    /**
     *
     * @param o1 first object of class Ride
     * @param o2 second object of class Ride
     * @return 1 if price per passenger in first object is bigger than in the second, -1 if it is another way round, 0 if they are equal
     */
    @Override
    public int compare(Ride o1, Ride o2) {
        if (o1.getPricePerPassenger() < o2.getPricePerPassenger()) return -1;
        if (o1.getPricePerPassenger() > o2.getPricePerPassenger()) return 1;
        return 0;
    }
}
