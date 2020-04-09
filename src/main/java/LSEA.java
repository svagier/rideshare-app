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
         *
         */




        //THREADS
//        int numberOfDrivers = 10000;  //prod
        int numberOfDrivers = 100;  //test

        DriverGenerator driverGenerator = new DriverGenerator(numberOfDrivers);
        String pathToCsv = "input_data/drivers.csv";
        String imagesInputPath = "input_images/";
        String imageFormat = "jpg";
        ArrayList<Driver> listOfDrivers = driverGenerator.generateDrivers(pathToCsv, imagesInputPath, imageFormat);
//        for(int i = 0; i < listOfDrivers.size(); i++)
//            System.out.println(listOfDrivers.get(i));
        int numberOfThreads = 2;
        String outputPath = "output_images/";
        GreenifyTask greenifyTask = new GreenifyTask(listOfDrivers, outputPath, imageFormat);
//        long start = System.currentTimeMillis();
//        greenifyTask.run();
//        long end = System.currentTimeMillis();
//        long duration = end-start;
//        System.out.println("Duration: " + duration);
        MirrorifyTask mirrorifyTask = new MirrorifyTask(listOfDrivers, outputPath, imageFormat);
//        mirrorifyTask.run();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
//        executorService.execute(greenifyTask);
        executorService.submit(greenifyTask);
        executorService.submit(mirrorifyTask);
//        executorService.invokeAll(greenifyTask, mirrorifyTask);

        executorService.shutdown();
        try {
            executorService.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

    }
}