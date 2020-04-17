package main.java.Serializers;

import main.java.Features.Address;

import java.io.*;
import java.util.ArrayList;

/**
 * The type Address serializer - enables serialization and deserialization to and from files.
 * @author Piotr Danielczyk
 * @version 1.4
 * @since 1.4
 */
public class AddressSerializer {

    /**
     * Serialize Addresses from a given list to a file.
     *
     * @param listOfAddresses    the list of Addresses
     * @param outputPathWithFile the output path with file, with included file extension
     * @return true if successfully serialized, false if error happened
     */
    public boolean serializeToFile (ArrayList<Address> listOfAddresses, String outputPathWithFile) {
        System.out.println("\nAttempting to serialize a list of Addresses to the given file.");
        try
        {
            FileOutputStream file = new FileOutputStream(outputPathWithFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(file);

            for (int i=0; i<listOfAddresses.size(); i++) {
                try {
                     outputStream.writeObject(listOfAddresses.get(i));
                }
                catch (Exception e) {
                    System.out.println("Error happened while trying to write Address number " + i +" from the list to the output stream.\n" + e);
                }
            }
            outputStream.close();
            file.close();
            System.out.println("Serialization has ended. The list has been saved to " + outputPathWithFile);
            return true;
        } catch (IOException e) {
            System.out.println("IOException has been caught.");
            e.printStackTrace();
            return false;
        }
        catch (Exception e) {
            System.out.println("Exception has been caught.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deserialize Addresses from file and return an array list of them.
     *
     * @param inputPathWithFile the output path with file, with included file extension
     * @return the array list of deserialized Addresses
     */
    public ArrayList<Address> deserializeFromFile (String inputPathWithFile) {
        System.out.println("\nAttempting to deserialize a list of Addresses from the given file.");
        ArrayList<Address> loadedListOfAddresses = new ArrayList<Address>();
        try
        {
            FileInputStream file = new FileInputStream(inputPathWithFile);
            ObjectInputStream inputStream = new ObjectInputStream(file);

            while (true) {
                try {
                    loadedListOfAddresses.add((Address)inputStream.readObject());
                } catch (EOFException e) {
                    System.out.println("End of file " + inputPathWithFile + " reached.");
                    break;
                }
                catch (ClassNotFoundException e) {
                    System.out.println("ClassNotFoundException has been caught. Make sure this is Address class.");
                    e.printStackTrace();
                }
                catch (Exception e) {
                    System.out.println("Exception happened while reading Address from file and adding it to the list.");
                    e.printStackTrace();
                }
            }

            inputStream.close();
            file.close();
            return loadedListOfAddresses;
        }

        catch (IOException e) {
            System.out.println("IOException has been caught.");
            e.printStackTrace();
            return null;
        }
        catch (Exception e) {
            System.out.println("Exception has been caught.");
            e.printStackTrace();
            return null;
        }
    }
}
