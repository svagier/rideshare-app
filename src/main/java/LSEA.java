package main.java;

import main.java.Generators.DriverGenerator;
import main.java.Tasks.GreenifyTask;
import main.java.Tasks.MirrorifyTask;
import main.java.Users.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The main entry point of the application.
 *
 * @author Piotr Danielczyk
 * @version 1.4
 * @since 1.0
 */
public class LSEA {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // PARALLEL THREADS below
        /**
         * numberOfDrivers - how many Drivers should be created. Max number in current version is 10000, because that's
         *                   how many data rows for drivers are created in csv and how many unique images are available
         * numberOfSmallerLists - into how many sublists should be the list of all Drivers be divided into
         * numberOfThreads - //twice as many as numberOfSmallerLists, because each list uses 2 threads: Mirrorify and Greenify
         */
        int numberOfDrivers = 7500;
        int numberOfSmallerLists = 10;
        int numberOfThreads = numberOfSmallerLists * 2;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);    // executorService for 'management' of threads
        long start = System.currentTimeMillis();

        /**
         * Generating list of Drivers using DriverGenerator. Text data for generation is taken from csv
         * (username, first name, last name, driver's license id).
         * Username is unique.
         * Images are taken from specified directory. Each file's name is equal to a username, so that each user
         * has an unique photo.
         * There are currently 10000 rows in csv and 10000 photos.
         * Images are saved after modifications (Greenify and Mirrorify) to output path.
         */
        DriverGenerator driverGenerator = new DriverGenerator(numberOfDrivers);
        String pathToCsv = "input_data/drivers.csv";
        String imagesInputPath = "input_images/";
        String imageFormat = "jpg";
        ArrayList<Driver> listOfDrivers = driverGenerator.generateDrivers(pathToCsv, imagesInputPath, imageFormat);
        String outputPath = "output_images/";

        /**
         * List of drivers is split into a specified number of sublists and an adequate number of threads (Tasks) is created.
         * Duration is measured.
         */
        ArrayList<ArrayList<Driver>> splitDriverLists = driverGenerator.splitListIntoN(listOfDrivers, numberOfSmallerLists);
        GreenifyTask splitGreenifyTask;
        MirrorifyTask splitMirrorifyTask;



        for (int i = 0; i < splitDriverLists.size(); i++) {
            splitGreenifyTask = new GreenifyTask(splitDriverLists.get(i), outputPath, imageFormat);
            splitMirrorifyTask = new MirrorifyTask(splitDriverLists.get(i), outputPath, imageFormat);
            executorService.submit(splitGreenifyTask);
            executorService.submit(splitMirrorifyTask);
        }



        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println("----------------------Exception " + e);
//            executorService.shutdownNow();
        }
        long end = System.currentTimeMillis();
        long duration = end-start;
        System.out.println("Duration: " + duration + "ms.");
    }
}

/**
 * below is commented out main function for sequential version
 */
//    public static void main(String[] args) {
//        //SEQUENTIAL version
//
//        int numberOfDrivers = 7500;
//        int numberOfSmallerLists = 10;
//
//        long start = System.currentTimeMillis();
//
//        /**
//         * Generating list of Drivers using DriverGenerator. Text data for generation is taken from csv
//         * (username, first name, last name, driver's license id).
//         * Username is unique.
//         * Images are taken from specified directory. Each file's name is equal to a username, so that each user
//         * has an unique photo.
//         * There are currently 10000 rows in csv and 10000 photos.
//         * Images are saved after modifications (Greenify and Mirrorify) to output path.
//         */
//        DriverGenerator driverGenerator = new DriverGenerator(numberOfDrivers);
//        String pathToCsv = "input_data/drivers.csv";
//        String imagesInputPath = "input_images/";
//        String imageFormat = "jpg";
//        ArrayList<Driver> listOfDrivers = driverGenerator.generateDrivers(pathToCsv, imagesInputPath, imageFormat);
//        String outputPath = "output_images/";
//
//        GreenifyTask greenifyTask = new GreenifyTask(listOfDrivers, outputPath, imageFormat);
//        MirrorifyTask mirrorifyTask = new MirrorifyTask(listOfDrivers, outputPath, imageFormat);
//        greenifyTask.run();
//        mirrorifyTask.run();
//
//        long end = System.currentTimeMillis();
//        long duration = end-start;
//        System.out.println("Duration: " + duration + "ms.");
//    }
