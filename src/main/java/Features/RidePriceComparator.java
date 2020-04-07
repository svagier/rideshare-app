package main.java.Features;

import java.util.Comparator;

public class RidePriceComparator implements Comparator<Ride> {

    @Override
    public int compare(Ride o1, Ride o2) {
        if (o1.getPricePerPassenger() < o2.getPricePerPassenger()) return -1;
        if (o1.getPricePerPassenger() > o2.getPricePerPassenger()) return 1;
        return 0;
    }
}
