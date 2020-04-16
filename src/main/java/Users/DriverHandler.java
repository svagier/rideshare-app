package main.java.Users;

import java.util.ArrayList;
import java.io.*;
import java.util.Date;

public class DriverHandler {
//    ArrayList<User> listOfDrivers,
    // boolean if true, then data will be written to the end of the file rather than the beginning. If false: file will be overwritten.
    public boolean saveDriversToTxt(ArrayList<Driver> listOfDrivers, String outputPathWithFile, boolean shouldAppend, char separator) {

        try {
            FileWriter writer = new FileWriter(outputPathWithFile, shouldAppend);
            for (int i=0; i < listOfDrivers.size(); i++)
                writer.write(listOfDrivers.get(i).getUsername() + separator + listOfDrivers.get(i).getFirstName() +
                        separator + listOfDrivers.get(i).getLastName() + separator + listOfDrivers.get(i).getDateOfBirth() +
                        separator + listOfDrivers.get(i).getRidesCompleted() + separator + "\r\n");

            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {
        // creating test users
        Date birthday = new Date(1, 2, 2000);
        Driver dummyDriver1 = new Driver("driver_nick", "Tim", "Dunkey", birthday, "12345");
        Driver dummyDriver2 = new Driver("other_driver_nick", "Jim", "Carter", birthday, "54321");

        ArrayList<Driver> listOfDrivers = new ArrayList<Driver>();
        listOfDrivers.add(dummyDriver1);
        listOfDrivers.add(dummyDriver2);


        DriverHandler driverHandler = new DriverHandler();
        driverHandler.saveDriversToTxt(listOfDrivers, "output_data\\out.txt", false, ';');

    }
}
