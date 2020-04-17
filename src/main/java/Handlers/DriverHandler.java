package main.java.Handlers;

import main.java.Users.Driver;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.*;

import static java.lang.Integer.parseInt;

/**
 * The type Driver handler. Responsible for reading/writing lists of Drivers to and from files.
 * @author Piotr Danielczyk
 * @version 1.4
 * @since 1.4
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
        System.out.println("\nAttempting to save given list of Drivers to txt file.");
        try {
            FileWriter writer = new FileWriter(outputPathWithFile, shouldAppend);
            for (int i=0; i < listOfDrivers.size(); i++)
                writer.write(driverToLine(listOfDrivers.get(i), separator));
            writer.close();
            System.out.println("List of drivers saved to " + outputPathWithFile);
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e);
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
        System.out.println("\nAttempting to load a list of Drivers from the given txt file.");
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
                } catch (java.lang.NumberFormatException e){
                    System.out.println("Error: " + e + " in line: " + line);
                    System.out.println("Make sure you are parsing to int properly.\n");
                } catch (Exception e) {
                    System.out.println("Error: " + e + " in line: " + line);
                }
            }
            fr.close();
            return loadedListOfDrivers;

        } catch (IOException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Save drivers to binary boolean.
     *
     * @param listOfDrivers      the list of Drivers
     * @param outputPathWithFile the output path with file, should have extensions included, like .dat
     * @param separator          the separator
     * @return the boolean True if saved successfully, false if exception happened.
     */
    public boolean saveDriversToBinary(ArrayList<Driver> listOfDrivers, String outputPathWithFile, String separator) {
        System.out.println("\nAttempting to save given list of Drivers to binary file.");
        try {
            FileOutputStream fos = new FileOutputStream(new File(outputPathWithFile));
            for (int i=0; i < listOfDrivers.size(); i++)
                fos.write(driverToLine(listOfDrivers.get(i), separator).getBytes());
            fos.close();
            System.out.println("List of drivers saved to " + outputPathWithFile);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Load drivers from a specified binary file to an array list of Drivers.
     *
     * @param inputPathWithFile the input path with file and file extension, like .dat
     * @return the array list of Drivers
     */
    public ArrayList<Driver> loadDriversFromBinary(String inputPathWithFile) {
        System.out.println("\nAttempting to load a list of Drivers from the given binary file.");
        ArrayList<Driver> loadedListOfDrivers = new ArrayList<>();
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
                            e.printStackTrace();
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


    public ArrayList<Driver> loadDriversFromBinaryLocked(String inputPathWithFile, String separator) {
        ArrayList<Driver> loadedListOfDrivers = new ArrayList<>();
        try {
            RandomAccessFile file = new RandomAccessFile(inputPathWithFile, "rw");
            FileChannel channel = file.getChannel();
            FileLock lock = channel.lock();
            ByteBuffer buff = ByteBuffer.allocate(1024);
            channel.read(buff);
            String fileContent = new String(buff.array(), StandardCharsets.UTF_8);
            String allLines[] = fileContent.split("\n");

            for (int i=0; i < allLines.length - 1; i++) {       // length-1 because last line is empty
                try {
                    String splitLine[] = allLines[i].split(separator);
                    Driver newDriver = new Driver(splitLine[0], splitLine[1], splitLine[2], splitLine[3], parseInt(splitLine[4]));
                    loadedListOfDrivers.add(newDriver); // create new Driver with loaded data and add it to the list

                } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: " + e + " in line " + i);
                    System.out.println("Make sure the given line contains 5 columns.\n");
                }
                catch (Exception e) {
                    System.out.println("Error: " + e + " in line " + i);
                    e.printStackTrace();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return loadedListOfDrivers;
    }


    /**
     * Returns String (line) which will be appended to the file when exporting Drivers.
     * @param driver Driver from which data is taken
     * @param separator
     * @return String which will become line in a file
     */
    private String driverToLine(Driver driver, String separator) {
        return driver.getUsername() + separator + driver.getFirstName() +
                separator + driver.getLastName() + separator + driver.getDriversID() +
                separator + driver.getRidesCompleted() + separator + "\r\n";
    }


    /**
     * The entry point of application. Presents how writing/reading from txt and binary files works.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Driver dummyDriver1 = new Driver("driver_nick", "Tim", "Dunkey", "123456");
        Driver dummyDriver2 = new Driver("other_driver_nick", "Jim", "Carter", "654321");

        ArrayList<Driver> listOfDrivers = new ArrayList<Driver>();
        listOfDrivers.add(dummyDriver1);
        listOfDrivers.add(dummyDriver2);

        DriverHandler driverHandler = new DriverHandler();
        ArrayList<Driver> listOfDriversFromBinary;
        ArrayList<Driver> listOfDriversFromBinaryLocked;
        ArrayList<Driver> listOfDriversFromTxt;

        String separator = ";";

//        driverHandler.saveDriversToTxt(listOfDrivers, "output_data\\out.txt", false, separator);
//        listOfDriversFromTxt = driverHandler.loadDriversFromTxt("output_data\\out.txt",  separator);
//        System.out.println("List of drivers loaded from txt file:");
//        for (int i=0; i<listOfDriversFromTxt.size(); i++)
//            System.out.println(listOfDriversFromTxt.get(i));

//        driverHandler.saveDriversToBinary(listOfDrivers, "output_data\\out.dat", ";");
//        listOfDriversFromBinary = driverHandler.loadDriversFromBinary("output_data\\out.dat");
//        System.out.println("List of drivers loaded from binary file:");
//        for (int i=0; i<listOfDriversFromBinary.size(); i++)
//            System.out.println(listOfDriversFromBinary.get(i));

        listOfDriversFromBinaryLocked = driverHandler.loadDriversFromBinaryLocked("output_data\\out.dat", separator);
        System.out.println("List of drivers loaded from binary file with locking:");
        for (int i=0; i<listOfDriversFromBinaryLocked.size(); i++)
            System.out.println(listOfDriversFromBinaryLocked.get(i).getUsername());
//        System.out.println(listOfDriversFromBinaryLocked.get(1).getDriversID());

    }
}