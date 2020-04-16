package main.java.Users;

import java.util.ArrayList;
import java.io.*;
import java.util.Date;

public class UserHandler {
//    ArrayList<User> listOfUsers,
    public boolean saveUsersToTxt(String outputPathWithFile) {

        try {
            FileWriter writer = new FileWriter(outputPathWithFile, true);
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write("Good Bye!");
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
//        Driver dummyDriver = new Driver("driver_nick", "Tim", "Dunkey", birthday, "12345");
        Passenger dummyPassenger1 = new Passenger("passenger_nick", "John", "Jackson", birthday);
        Passenger dummyPassenger2 = new Passenger("passenger_nick_two", "Kate", "Mellow", birthday);

        UserHandler userHandler = new UserHandler();
        userHandler.saveUsersToTxt("output_data\\out.txt");

    }
}
