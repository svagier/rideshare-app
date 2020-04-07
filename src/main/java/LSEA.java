package main.java;

import main.java.Users.*;
import main.java.Features.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;

/**
 * The main entry point of the application.
 *
 * @author Piotr Danielczyk
 * @version 1.0
 * @since 1.0
 */
public class LSEA {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        /**
         * here in main we will show how the basics of the program work.
         * We will create exemplary users, and add them to an exemplary ride.
         */

        // creating test users
        Date birthday = new Date(1, 2, 2000);
        Driver dummyDriver = new Driver("driver_nick", "Tim", "Dunkey", birthday, "12345");
        Passenger dummyPassenger1 = new Passenger("passenger_nick", "John", "Jackson", birthday,
                                                false, false);
        Passenger dummyPassenger2 = new Passenger("passenger_nick_two", "Kate", "Mellow", birthday,
                true, false);

        // creating test addresses
        Address gdansk = new Address("Gdansk", "Poland", "Gabriela Narutowicza", 11);
        Address gdynia = new Address("Gdynia", "Poland", "Morska", 81);

        // print statistics of users before the ride
        System.out.println("\nSTATS BEFORE RIDE:");
        dummyDriver.printStats();
        dummyPassenger1.printStats();
        dummyPassenger2.printStats();

        // create test ride:
        Ride firstRide = dummyDriver.createRide(gdansk, gdynia, "Fiat 126p", "GD1337", 8,3, false, LocalDateTime.now());
        // both passengers join the ride:
        dummyPassenger1.joinRide(firstRide);
        dummyPassenger2.joinRide(firstRide);
        // second passenger decides not to take part in the ride:
        dummyPassenger2.cancelRide(firstRide);
        firstRide.startRide();

//        LocalDate aDate = LocalDate.of(2015, Month.JULY, 29);
//        LocalTime aTime = LocalTime.of(19, 30, 40);
//        firstRide.getRideInfo();
//        firstRide.setRideEndDateTime(aDate, aTime);
//        firstRide.getRideInfo();

        firstRide.finishRide();

        // print statistics of users after the ride
        System.out.println("\nSTATS AFTER RIDE:");
        dummyDriver.printStats();
        dummyPassenger1.printStats();
        dummyPassenger2.printStats();



        //testing deep cloning:
        Address sopot = new Address("Sopot", "Pomorska", 5);

        Route routeA = new Route(gdansk, gdynia, 12, false);
        Route routeB = routeA.clone();
        System.out.println(routeA);
        System.out.println(routeB);
        routeB.setStart(sopot);
        routeB.setDistance(2);
        System.out.println(routeA);
        System.out.println(routeB);


        //testing Comparable interface
        System.out.println(routeB.compareTo(routeA));
    }
}