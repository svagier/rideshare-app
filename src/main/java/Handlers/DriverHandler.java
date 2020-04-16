package main.java.Handlers;

import main.java.Users.Driver;

import java.util.ArrayList;
import java.io.*;

import static java.lang.Integer.parseInt;

/**
 * The type Driver handler.
 */
public class DriverHandler {
    /**
     * Save Drivers from the list of Drivers to .txt file.
     *
     * @param listOfDrivers      the list of Drivers
     * @param outputPathWithFile the output path with file, file extensions .txt should be added
     * @param shouldAppend       if true, then data will be written to the end of the file rather than the beginning. If false: file will be overwritten.
     * @param separator          the separator between columns of values
     * @return the boolean True if saved successfully, false if exception happened.
     */
    public boolean saveDriversToTxt(ArrayList<Driver> listOfDrivers, String outputPathWithFile, boolean shouldAppend, String separator) {
        System.out.println("Attempting to save given list of Drivers to txt file.");
        try {
            FileWriter writer = new FileWriter(outputPathWithFile, shouldAppend);
            for (int i=0; i < listOfDrivers.size(); i++)
                writer.write(driverToLine(listOfDrivers.get(i), separator));
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Load drivers from a specified txt file to an array list of Drivers.
     *
     * @param inputPathWithFile the input path with file
     * @param separator         the separator
     * @return the array list of Drivers
     */
    public ArrayList<Driver> loadDriversFromTxt(String inputPathWithFile, String separator) {
        System.out.println("Attempting to load a list of Drivers from the given txt file.");
        ArrayList<Driver> loadedListOfDrivers = new ArrayList<Driver>();
        try {
            FileReader fr = new FileReader(inputPathWithFile);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while((line=br.readLine())!=null)
            {
//                System.out.println(line);
                String splitLine[] = line.split(separator);
                // we assume data is in the form as in driverToLine():
                try {
                    Driver newDriver = new Driver(splitLine[0], splitLine[1], splitLine[2], splitLine[3], parseInt(splitLine[4]));
                    loadedListOfDrivers.add(newDriver);
                } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: " + e + " in line: " + line);
                }
            }
            fr.close();
            return loadedListOfDrivers;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean saveDriversToBinary(ArrayList<Driver> listOfDrivers, String outputPathWithFile, String separator) {
        System.out.println("Attempting to save given list of Drivers to binary file.");
        try {
            FileOutputStream fos = new FileOutputStream(new File(outputPathWithFile));
            for (int i=0; i < listOfDrivers.size(); i++)
                fos.write(driverToLine(listOfDrivers.get(i), separator).getBytes());
            fos.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Driver> loadDriversFromBinary(String inputPathWithFile) {
        System.out.println("Attempting to load a list of Drivers from the given binary file.");
        ArrayList<Driver> loadedListOfDrivers = new ArrayList<Driver>();
        try {
            FileInputStream fis = new FileInputStream(new File(inputPathWithFile));
            int ch;
            int numberOfColumns = 5;
            String arr[] = new String[numberOfColumns];     // array of Strings, each String is a column of Driver
            String word = "";                   // temporary String (kind of buffer)
            int iter = 0;                       // specifies number of word (column) for each line
            while ((ch = fis.read()) != -1) {   // read one byte at a time until end of file
//                System.out.print((char) ch);
                if((char) ch != '\n') {         // if it is NOT the end of the line
                    if((char) ch != ';') {
                        word += (char) ch;      // if the char is not the separator, add it to the word String
                    }
                    else {                      // if the char is separator it means that one word (column) has ended
                        arr[iter] = word;       // add the finished word to the array
                        word = "";              // clear the String word, so that new word may be assigned to it later
                        iter++;                 // increment the iter, which indicates which column is being processed
                    }
                }
                else {                          // if it is end of the line
                    if (iter == numberOfColumns) {  // make sure that there is as many columns as there should be
                        try {                       // (if the number of columns is wrong the data is not saved)
                            Driver newDriver = new Driver(arr[0], arr[1], arr[2], arr[3], parseInt(arr[4]));
                            loadedListOfDrivers.add(newDriver); // create new Driver with loaded data and add it to the list
                        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                            System.out.println("Error: " + e + " in line for user: " + arr[0]);
                            System.out.println("Make sure you specified variable numberOfColumns properly (" + numberOfColumns + " columns).\n");
                        } catch (java.lang.NumberFormatException e) {
                            System.out.println("Error: " + e + " in line for user: " + arr[0]);
                            System.out.println("Make sure you are parsing to int properly.\n");
                        } catch (Exception e) {
                            System.out.println("Error: " + e + " in line for user: " + arr[0]);
                        }
                    }
                    iter = 0;                   // iter is 0 again, because we are starting new line with word (column) number 0
                    arr = new String[numberOfColumns];  // clear the array so that new words (columns) may be added
                }
            }
            fis.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return loadedListOfDrivers;
    }


    private String driverToLine(Driver driver, String separator) {
        return driver.getUsername() + separator + driver.getFirstName() +
                separator + driver.getLastName() + separator + driver.getDriversID() +
                separator + driver.getRidesCompleted() + separator + "\r\n";
    }


        /**
         * The entry point of application.
         *
         * @param args the input arguments
         */
    public static void main(String[] args) {
        Driver dummyDriver1 = new Driver("driver_nick", "Tim", "Dunkey", "12345");
        Driver dummyDriver2 = new Driver("other_driver_nick", "Jim", "Carter", "54321");

        ArrayList<Driver> listOfDrivers = new ArrayList<Driver>();
        listOfDrivers.add(dummyDriver1);
        listOfDrivers.add(dummyDriver2);

        DriverHandler driverHandler = new DriverHandler();
        ArrayList<Driver> listOfDriversFromBinary = new ArrayList<Driver>();
        ArrayList<Driver> listOfDriversFromTxt = new ArrayList<Driver>();

        driverHandler.saveDriversToTxt(listOfDrivers, "output_data\\out.txt", false, ";");
        listOfDriversFromTxt = driverHandler.loadDriversFromTxt("output_data\\out.txt",  ";");
        System.out.println("\nList of drivers loaded from txt file:");
        for (int i=0; i<listOfDriversFromTxt.size(); i++)
            System.out.println(listOfDriversFromTxt.get(i));
        System.out.println("\n");

        driverHandler.saveDriversToBinary(listOfDrivers, "output_data\\out.dat", ";");
        listOfDriversFromBinary = driverHandler.loadDriversFromBinary("output_data\\out.dat");
        System.out.println("\nList of drivers loaded from binary file:");
        for (int i=0; i<listOfDriversFromBinary.size(); i++)
            System.out.println(listOfDriversFromBinary.get(i));
        System.out.println("\n");

    }
}