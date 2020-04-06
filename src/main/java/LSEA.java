import Users.*;
import Features.*;
import java.util.Date;
public class LSEA {
    public static void main(String[] args) {
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
        Ride firstRide = dummyDriver.createRide(gdansk, gdynia, "Fiat 126p", "GD1337", 8,3, false);
        // both passengers join the ride:
        dummyPassenger1.joinRide(firstRide);
        dummyPassenger2.joinRide(firstRide);
        // second passenger decides not to take part in the ride:
        dummyPassenger2.cancelRide(firstRide);
        firstRide.startRide();
        firstRide.finishRide();

        // print statistics of users after the ride
        System.out.println("\nSTATS AFTER RIDE:");
        dummyDriver.printStats();
        dummyPassenger1.printStats();
        dummyPassenger2.printStats();

    }
}