package main.java.Features;

/**
 * The type Route. Specifies a route from address A to address B.
 */
public class Route implements Cloneable{
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
