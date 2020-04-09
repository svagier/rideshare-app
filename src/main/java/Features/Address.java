package main.java.Features;

import main.java.Users.Driver;
import main.java.Users.Passenger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Address class to specify destination and origin of rides
 * (addresses to pickup passengers, to drop them off etc)
 *
 * @author Piotr Danielczyk
 * @version 1.0
 * @since 1.0
 */
public class Address implements Cloneable {
    private String city;
    private String country;
    private String street;
    private int buildingNumber;

    /**
     * Instantiates a new Address.
     * constructor in case there is no country - we assume Poland is default
     *
     * @param city           the city
     * @param street         the street
     * @param buildingNumber the building number on the given street
     */
    public Address(String city, String street, int buildingNumber){
        this.city = city;
        this.country = "Poland";
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    /**
     * Instantiates a new Address.
     * constructor when the city is explicitly specified.
     *
     * @param city           the city
     * @param country        the country
     * @param street         the street name
     * @param buildingNumber the building number on the given street
     */
    public Address(String city, String country, String street, int buildingNumber){
        this.city = city;
        this.country = country;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        /**
         * below: exemplary code from previous labs, showing how basics of the app work
         */
                // creating test addresses
        Address gdansk = new Address("Gdansk", "Poland", "Gabriela Narutowicza", 11);
        Address gdynia = new Address("Gdynia", "Poland", "Morska", 81);
//        // creating test users
        Date birthday = new Date(1, 2, 2000);
        Driver dummyDriver = new Driver("driver_nick", "Tim", "Dunkey", birthday, "12345");
//        Passenger dummyPassenger1 = new Passenger("passenger_nick", "John", "Jackson", birthday,
//                                                false, false, Passenger.PreferredSeat.FRONT);
//        Passenger dummyPassenger2 = new Passenger("passenger_nick_two", "Kate", "Mellow", birthday,
//                                            true, false, Passenger.PreferredSeat.BACK_ANY_SIDE);
//
//        // print statistics of users before the ride
//        System.out.println("\nSTATS BEFORE RIDE:");
//        dummyDriver.printStats();
//        dummyPassenger1.printStats();
//        dummyPassenger2.printStats();
//
//        // create test ride:
//        Ride firstRide = dummyDriver.createRide(gdansk, gdynia, "Fiat 126p", "GD1337", 1,3, false, LocalDateTime.now());
//        // both passengers join the ride:
//        dummyPassenger1.joinRide(firstRide);
//        dummyPassenger2.joinRide(firstRide);
//        // second passenger decides not to take part in the ride:
//        dummyPassenger2.cancelRide(firstRide);
//        firstRide.startRide();
//        firstRide.finishRide();
//
//        // print statistics of users after the ride
//        System.out.println("\nSTATS AFTER RIDE:");
//        dummyDriver.printStats();
//        dummyPassenger1.printStats();
//        dummyPassenger2.printStats();



        //testing deep cloning:
        /**
         * below: functionalities from Lab2 presented:
         */
        System.out.println("\n\nTesting deep cloning of Route");
        Address sopot = new Address("Sopot", "Pomorska", 5);
        Route routeA = new Route(gdansk, gdynia, 12, false);
        System.out.println("routeA: " + routeA);
        System.out.println("Creating routeB, which is a clone of routeA");
        Route routeB = (Route) routeA.clone();
        System.out.println("routeA: " + routeA);
        System.out.println("routeB: " + routeB);
        System.out.println("Changing start Address and distance in routeB.");
        routeB.setStart(sopot);
        routeB.setDistance(3);
        System.out.println("Display both routes after the change to routeB. RouteA is not affected:");
        System.out.println("routeA: " + routeA);
        System.out.println("routeB: " + routeB);

        //testing Comparable interface
        System.out.println("\n\nTesting Comparable interface in Route and using sorting.");
        Route routeC = new Route(gdansk, sopot, 4, false);
        System.out.println("routeA distance: " + routeA.getDistance());
        System.out.println("routeB distance: " + routeB.getDistance());
        System.out.println("routeC distance: " + routeC.getDistance());
        ArrayList<Route> routes = new ArrayList<>();
        routes.add(routeA);
        routes.add(routeB);
        routes.add(routeC);
        System.out.println("Unsorted (distance of Route):");
        for (int i=0; i<routes.size(); i++)
            System.out.println(routes.get(i).getDistance());
        Collections.sort(routes);
        System.out.println("Sorted (distance of Route):");
        for (int i=0; i<routes.size(); i++)
            System.out.println(routes.get(i).getDistance());


        //testing Comparator
        System.out.println("\n\nTesting Comparator - RidePriceComparator - and sorting. List with 3 Rides with various prices per passenger is created.");
        ArrayList<Ride> ridesArr = new ArrayList<Ride>();
        Ride mostExpensiveRide = dummyDriver.createRide(gdynia, gdansk, "Ford Focus", "GDA1111", 3,2, false, LocalDateTime.now());
        Ride leastExpensiveRide = dummyDriver.createRide(sopot, gdansk, "Fiat 126p", "GD3437", 1.5,4, true, LocalDateTime.now());
        Ride moreExpensiveRide = dummyDriver.createRide(gdansk, sopot, "Fiat 125p", "GD3337", 2,1, true, LocalDateTime.now());

        ridesArr.add(moreExpensiveRide);
        ridesArr.add(mostExpensiveRide);
        ridesArr.add(leastExpensiveRide);
        System.out.println("Unsorted (values of pricePerPassenger):");
        for (int i=0; i<ridesArr.size(); i++)
            System.out.println(ridesArr.get(i).getPricePerPassenger());
        Collections.sort(ridesArr, new RidePriceComparator());
        System.out.println("Sorted (values of pricePerPassenger):");
        for (int i=0; i<ridesArr.size(); i++)
            System.out.println(ridesArr.get(i).getPricePerPassenger());
    }


    /**
     * overriding toString() method to be able to print the address in a readable form
     */
    @Override
    public String toString() {
        if (this.isAbroad()) {  // if the country is not Poland, we will print the country's name
            return getStreet() + " " + getBuildingNumber() + ", " + getCity() + ", " + getCountry();
        }
        else {  // if the country is Poland, we do not print "Poland"
            return getStreet() + " " + getBuildingNumber() + ", " + getCity();
        }
    }

    /**
     * overriding clone() method and dealing with CloneNotSupportedException exception
     */
    @Override
    public Object clone() {
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("CloneNotSupportedException exception happened");
            return new Address(this.getCity(), this.getCountry(), this.getStreet(), this.getBuildingNumber());
        }
    }

    /**
     * Is abroad boolean.
     *
     * @return true if the country is Poland, otherwise return false
     */
    public boolean isAbroad() {
        // returns true if country is different than Poland. Poland here is considered as homecountry
        if (this.country == "Poland") {
            return (false);
        }
        else {
            return (true);
        }
    }

    /**
     * Gets city.
     *
     * @return the city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city name
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets country name.
     *
     * @return the country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country name.
     *
     * @param country the country name
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets street name.
     *
     * @return the street name
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street name.
     *
     * @param street the street name
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets building number.
     *
     * @return the building number
     */
    public int getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * Sets building number.
     *
     * @param buildingNumber the building number
     */
    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

}
