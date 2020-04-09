package main.java.Generators;

import main.java.Users.Driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class DriverGenerator {
    private int numberOfDrivers;

    public DriverGenerator(int numberOfDrivers) {
        this.numberOfDrivers = numberOfDrivers;
    }
    public ArrayList<Driver> generateDrivers(String csvPath) {
        ArrayList<Driver> listOfDrivers = new ArrayList<Driver>();
        Random rand = new Random();


        BufferedReader br = null;
        String line = "";
        String separator = ",";

        try {
            br = new BufferedReader(new FileReader(csvPath));
            int iterator = 0;
            // while there are lines in the file and there is still space on the list of drivers
            while ( ((line = br.readLine()) != null)  &&  (iterator < this.getNumberOfDrivers()) ) {
                iterator++;
                String[] data = line.split(separator);

                // generating random date
                int year = rand.nextInt((102 - 1) + 1);     // 1900 is added by default
                int month = rand.nextInt((12 - 1) + 1) + 1;
                int day = rand.nextInt((28 - 1) + 1) + 1;
                Date birthday = new Date(year, month, day);

                // adding newly created driver to the list
                Driver newDriver = new Driver(data[0], data[1], data[2], birthday, data[4]);
                listOfDrivers.add(newDriver);
            }
            return listOfDrivers;

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                }
            }
        }

        return null;
    }


    public int getNumberOfDrivers() {
        return numberOfDrivers;
    }

    public void setNumberOfDrivers(int numberOfDrivers) {
        assert numberOfDrivers < 10000 : "currently only have 10000unique username records in a file";
        this.numberOfDrivers = numberOfDrivers;
    }
}
