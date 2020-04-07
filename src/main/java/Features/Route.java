package main.java.Features;

/**
 * The type Route. Specifies a route from address A to address B.
 */
public class Route implements Cloneable, Comparable<Route>{
    private Address start;
    private Address end;
    private int distance;
    private boolean doesContainPaidRoads;

    /**
     * Instantiates a new Route.
     *
     * @param origin               the origin Addres
     * @param destination          the destination Addres
     * @param distance             the distance between origin and destination
     * @param doesContainPaidRoads boolean, true if the route contains paid roads
     */
    public Route(Address origin, Address destination, int distance, boolean doesContainPaidRoads) {
        this.start = origin;
        this.end = destination;
        this.distance = distance;
        this.doesContainPaidRoads = doesContainPaidRoads;
    }

    /**
     * Deep copy implementation - copying route.
     * New Address is created, instead of using reference.
     * @return new cloned object
     */
    @Override
    public Object clone() {
        Route returnedRoute = null;
        try {
            returnedRoute = (Route) super.clone();
        } catch(CloneNotSupportedException e) {
            System.out.println("CloneNotSupportedException exception happened");
            returnedRoute = new Route(this.getStart(), this.getEnd(), this.getDistance(), this.doesContainPaidRoads());
        }
        returnedRoute.setStart((Address) this.start.clone());
        returnedRoute.setEnd((Address) this.end.clone());
        return returnedRoute;
    }

    /**
     * Overriding to fulfill Comparable Interface requirements.
     * @param route2 other Route object
     * @return 1 if this.distance is greater than route2. distance, 0 if they are equal, -1 otherwise
     */
    @Override
    public int compareTo(Route route2) {
        if (this.getDistance() == route2.getDistance())
            return 0;
        else if (this.getDistance() > route2.getDistance())
            return 1;
        else
            return -1;
    }

    /**
     * Allows displaying information about the Route in a readable form
     * @return string with information about the Route
     */
    @Override
    public String toString() {
        return "Route from " + this.getStart() + " to " + this.getEnd() + ", distance: " + this.getDistance() + " km.";
    }

    /**
     * Gets start Address.
     *
     * @return the start
     */
    public Address getStart() {
        return start;
    }

    /**
     * Sets start Address.
     *
     * @param start the start Address
     */
    public void setStart(Address start) {
        this.start = start;
    }

    /**
     * Gets end Address.
     *
     * @return the end Address
     */
    public Address getEnd() {
        return end;
    }

    /**
     * Sets end Address.
     *
     * @param end the end Address
     */
    public void setEnd(Address end) {
        this.end = end;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Sets distance.
     *
     * @param distance the distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Does contain paid roads boolean.
     *
     * @return true if paid roads are contained in the route, otherwise false
     */
    public boolean doesContainPaidRoads() {
        return doesContainPaidRoads;
    }

    /**
     * Sets does contain paid roads boolean.
     *
     * @param doesContainPaidRoads true if paid roads are contained in the route, otherwise false
     */
    public void setDoesContainPaidRoads(boolean doesContainPaidRoads) {
        this.doesContainPaidRoads = doesContainPaidRoads;
    }

}
