package main.java;

import main.java.Generators.DriverGenerator;
import main.java.Tasks.GreenifyTask;
import main.java.Users.*;
import main.java.Features.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    public static void main(String[] args) {
        /**
         * here in main we will show how the basics of the program work.
         * We will create exemplary users, and add them to an exemplary ride.
         */

//        // creating test users
//        Date birthday = new Date(1, 2, 2000);
//        Driver dummyDriver = new Driver("driver_nick", "Tim", "Dunkey", birthday, "12345");
//        Passenger dummyPassenger1 = new Passenger("passenger_nick", "John", "Jackson", birthday,
//                                                false, false, Passenger.PreferredSeat.FRONT);
//        Passenger dummyPassenger2 = new Passenger("passenger_nick_two", "Kate", "Mellow", birthday,
//                                            true, false, Passenger.PreferredSeat.BACK_ANY_SIDE);



//        dummyDriver.loadProfileImage(inputPath, "jpg");
////        dummyDriver.convertImageToGreen(outputPath, "jpg");
//        dummyDriver.convertImageToMirror(inputPath, "jpg", outputPath, "jpg");


//        // creating test addresses
//        Address gdansk = new Address("Gdansk", "Poland", "Gabriela Narutowicza", 11);
//        Address gdynia = new Address("Gdynia", "Poland", "Morska", 81);
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
//
////        LocalDate aDate = LocalDate.of(2015, Month.JULY, 29);
////        LocalTime aTime = LocalTime.of(19, 30, 40);
////        firstRide.getRideInfo();
////        firstRide.setRideEndDateTime(aDate, aTime);
////        firstRide.getRideInfo();
//
//        firstRide.finishRide();
//
//        // print statistics of users after the ride
//        System.out.println("\nSTATS AFTER RIDE:");
//        dummyDriver.printStats();
//        dummyPassenger1.printStats();
//        dummyPassenger2.printStats();
//
//
//
//        //testing deep cloning:
//        System.out.println("\n\nTesting deep cloning of Route");
//        Address sopot = new Address("Sopot", "Pomorska", 5);
//        Route routeA = new Route(gdansk, gdynia, 12, false);
//        System.out.println("routeA: " + routeA);
//        System.out.println("Creating routeB, which is a clone of routeA");
//        Route routeB = (Route) routeA.clone();
//        System.out.println("routeA: " + routeA);
//        System.out.println("routeB: " + routeB);
//        System.out.println("Changing start Address and distance in routeB.");
//        routeB.setStart(sopot);
//        routeB.setDistance(2);
//        System.out.println("Display both routes after the change to routeB. RouteA is not affected:");
//        System.out.println("routeA: " + routeA);
//        System.out.println("routeB: " + routeB);
//
//        //testing Comparable interface
//        System.out.println("\n\nTesting Comparable interface in Route and using sorting.");
//        Route routeC = new Route(gdansk, sopot, 4, false);
//        System.out.println("routeA distance: " + routeA.getDistance());
//        System.out.println("routeB distance: " + routeB.getDistance());
//        System.out.println("routeC distance: " + routeC.getDistance());
//        ArrayList<Route> routes = new ArrayList<>();
//        routes.add(routeA);
//        routes.add(routeB);
//        routes.add(routeC);
//        System.out.println("Unsorted (distance of Route):");
//        for (int i=0; i<routes.size(); i++)
//            System.out.println(routes.get(i).getDistance());
//        Collections.sort(routes);
//        System.out.println("Sorted (distance of Route):");
//        for (int i=0; i<routes.size(); i++)
//            System.out.println(routes.get(i).getDistance());
//
//
//        //testing Comparator
//        System.out.println("\n\nTesting Comparator - RidePriceComparator - and sorting. List with 3 Rides with various prices per passenger is created.");
//        ArrayList<Ride> ridesArr = new ArrayList<Ride>();
//        Ride mostExpensiveRide = dummyDriver.createRide(gdynia, gdansk, "Ford Focus", "GDA1111", 3,2, false, LocalDateTime.now());
//        Ride leastExpensiveRide = dummyDriver.createRide(sopot, gdansk, "Fiat 126p", "GD3437", 1.5,4, true, LocalDateTime.now());
//        Ride moreExpensiveRide = dummyDriver.createRide(gdansk, sopot, "Fiat 125p", "GD3337", 2,1, true, LocalDateTime.now());
//
//        ridesArr.add(moreExpensiveRide);
//        ridesArr.add(mostExpensiveRide);
//        ridesArr.add(leastExpensiveRide);
//        System.out.println("Unsorted (values of pricePerPassenger):");
//        for (int i=0; i<ridesArr.size(); i++)
//            System.out.println(ridesArr.get(i).getPricePerPassenger());
//        Collections.sort(ridesArr, new RidePriceComparator());
//        System.out.println("Sorted (values of pricePerPassenger):");
//        for (int i=0; i<ridesArr.size(); i++)
//            System.out.println(ridesArr.get(i).getPricePerPassenger());


        //THREADS
        DriverGenerator driverGenerator = new DriverGenerator(10000);
        String pathToCsv = "input_data/drivers.csv";
        ArrayList<Driver> listOfDrivers = driverGenerator.generateDrivers(pathToCsv);
        for(int i = 0; i < listOfDrivers.size(); i++)
            System.out.println(listOfDrivers.get(i));
        int numberOfThreads = 2;
        String inputPath = "input_images/";
        String outputPath = "output_images/";
//        GreenifyTask greenifyTask = new GreenifyTask();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
//        executorService.execute(greenifyTask);

    }
}