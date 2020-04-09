package main.java.Generators;

import main.java.Users.Driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * Generates list of drivers, based on data from .csv with random names and ids
 */
public class DriverGenerator {
    private int numberOfDrivers;

    /**
     * Instantiates a new Driver generator.
     *
     * @param numberOfDrivers the number of drivers to be generated
     */
    public DriverGenerator(int numberOfDrivers) {
        this.numberOfDrivers = numberOfDrivers;
    }

    /**
     * Generate drivers array list. Loads username, first name, last name and drivers license id from csv file.
     * The values are randomly generated, the usernames are unique.
     * It also adds profile images to each Driver object. The images are in directory specified by input path.
     * All images are unique and named with unique username, so that each Driver gets dedicated and unique photo.
     * All images are from a dataset available for use and testing. http://vis-www.cs.umass.edu/lfw/
     *
     * @param csvPath         the csv path
     * @param imagesInputPath the images input path
     * @param imageFormat     the image format
     * @return the array list
     */
    public ArrayList<Driver> generateDrivers(String csvPath, String imagesInputPath, String imageFormat) {
        ArrayList<Driver> listOfDrivers = new ArrayList<Driver>();
        Random rand = new Random();


        BufferedReader br = null;
        String line = "";
        String separator = ",";

        try {
            System.out.println("Starting to generate " + this.getNumberOfDrivers() + " drivers with photos.");
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
                // adding photo to the Driver
                // source of photos: http://vis-www.cs.umass.edu/lfw/
                newDriver.loadProfileImage(imagesInputPath, imageFormat);
            }
            System.out.println("Finished generating " + this.getNumberOfDrivers() + " drivers with photos.");
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


    /**
     * Split ArrayList of drivers into n smaller ArrayLists of Drivers.
     *
     * @param drivers the ArrayList of drivers to be split
     * @param n       how many sublists should be created
     * @return ArrayList of n small ArrayLists
     */
    public ArrayList<ArrayList<Driver>> splitListIntoN(ArrayList<Driver> drivers, int n) {
        if (n <= drivers.size()) {  // n cannot be greater than length of the list to be split
            ArrayList<ArrayList<Driver>> returnedList = new ArrayList<>();
            int sublistSize = (int)Math.floor((int)((int)drivers.size()) / n);
            System.out.println(sublistSize);
            int upperBoundary, lowerBoundary;
            for (int i = 0; i < n; i++) {
                lowerBoundary = i * sublistSize;
                if (i == (n - 1))
                    upperBoundary = drivers.size();
                else
                    upperBoundary = (i+1) * sublistSize;
                System.out.println(i + ": "  + lowerBoundary + " " + upperBoundary);
                returnedList.add(new ArrayList<Driver>(drivers.subList(lowerBoundary, upperBoundary)));
            }
            return returnedList;
        }
        return null;
    }


    /**
     * Gets number of drivers.
     *
     * @return the number of drivers
     */
    public int getNumberOfDrivers() {
        return numberOfDrivers;
    }

    /**
     * Sets number of drivers.
     *
     * @param numberOfDrivers the number of drivers
     */
    public void setNumberOfDrivers(int numberOfDrivers) {
        assert numberOfDrivers < 10000 : "currently only have 10000unique username records in a file";
        this.numberOfDrivers = numberOfDrivers;
    }
}
