package main.java.Generators;

import main.java.Users.Driver;

import java.util.ArrayList;


public class DriverGenerator {
    private int numberOfDrivers;

    public DriverGenerator(int numberOfDrivers) {
        this.numberOfDrivers = numberOfDrivers;
    }
    public ArrayList<Driver> generateDrivers() {
        ArrayList<Driver> listOfDrivers = new ArrayList<Driver>();
        for (int i = 0; i<this.getNumberOfDrivers(); i++) {
//            Driver newDriver = new Driver()
        }
    }


    public int getNumberOfDrivers() {
        return numberOfDrivers;
    }

    public void setNumberOfDrivers(int numberOfDrivers) {
        this.numberOfDrivers = numberOfDrivers;
    }
}
