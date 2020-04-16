package main.java.Users;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.io.*;
import java.util.Date;

import static java.lang.Integer.parseInt;

/**
 * The type Driver handler.
 */
public class DriverHandler {
    /**
     * Save Drivers from the list of Drivers to .txt file.
     *
     * @param listOfDrivers      the list of Drivers
     * @param outputPathWithFile the output path with file
     * @param shouldAppend       if true, then data will be written to the end of the file rather than the beginning. If false: file will be overwritten.
     * @param separator          the separator between columns of values
     * @return the boolean True if saved successfully, false if exception happened.
     */
    public boolean saveDriversToTxt(ArrayList<Driver> listOfDrivers, String outputPathWithFile, boolean shouldAppend, String separator) {
        try {
            FileWriter writer = new FileWriter(outputPathWithFile, shouldAppend);
            for (int i=0; i < listOfDrivers.size(); i++)
                writer.write(listOfDrivers.get(i).getUsername() + separator + listOfDrivers.get(i).getFirstName() +
                        separator + listOfDrivers.get(i).getLastName() + separator + listOfDrivers.get(i).getRidesCompleted() +
                        separator + listOfDrivers.get(i).getDriversID() + "\r\n");

            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Driver> loadDriversFromTxt(String inputPathWithFile, String separator) {
        ArrayList<Driver> loadedListOfDrivers = new ArrayList<Driver>();
        try {
            FileReader fr = new FileReader(inputPathWithFile);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while((line=br.readLine())!=null)
            {
                System.out.println(line);
                String splitLine[] = line.split(separator);
                Driver newDriver = new Driver(splitLine[0], splitLine[1], splitLine[2], splitLine[3], parseInt(splitLine[4]
            }
            fr.close();    //closes the stream and release the resources


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;

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
        driverHandler.saveDriversToTxt(listOfDrivers, "output_data\\out.txt", false, ";");
        driverHandler.loadDriversFromTxt("output_data\\out.txt",  ";");
    }
}
