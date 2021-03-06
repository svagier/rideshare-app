package main.java.Features;

import main.java.Serializers.AddressSerializer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Address class to specify destination and origin of rides
 * (addresses to pickup passengers, to drop them off etc)
 *
 * @author Piotr Danielczyk
 * @version 1.4
 * @since 1.0
 */
public class Address implements Cloneable, Serializable {
    private String city;
    private String country;
    private String street;
    private int buildingNumber;

    /**
     * Instantiates a new Address.
     * constructor in case there is no country - we assume Poland is default
     *
     * @param city           the city
     * @param street         the street
     * @param buildingNumber the building number on the given street
     */
    public Address(String city, String street, int buildingNumber) {
        this.city = city;
        this.country = "Poland";
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    /**
     * Instantiates a new Address.
     * constructor when the city is explicitly specified.
     *
     * @param city           the city
     * @param country        the country
     * @param street         the street name
     * @param buildingNumber the building number on the given street
     */
    public Address(String city, String country, String street, int buildingNumber) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }



    /**
     * overriding toString() method to be able to print the address in a readable form
     */
    @Override
    public String toString() {
        if (this.isAbroad()) {  // if the country is not Poland, we will print the country's name
            return getStreet() + " " + getBuildingNumber() + ", " + getCity() + ", " + getCountry();
        }
        else {  // if the country is Poland, we do not print "Poland"
            return getStreet() + " " + getBuildingNumber() + ", " + getCity();
        }
    }

    /**
     * overriding clone() method and dealing with CloneNotSupportedException exception
     */
    @Override
    public Object clone() {
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("CloneNotSupportedException exception happened");
            return new Address(this.getCity(), this.getCountry(), this.getStreet(), this.getBuildingNumber());
        }
    }


    /**
     * Is abroad boolean.
     *
     * @return true if the country is Poland, otherwise return false
     */
    public boolean isAbroad() {
        // returns true if country is different than Poland. Poland here is considered as homecountry
        if (this.getCountry().equals("Poland")) {
            return (false);
        }
        else {
            return (true);
        }
    }

    /**
     * Gets city.
     *
     * @return the city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city name
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets country name.
     *
     * @return the country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country name.
     *
     * @param country the country name
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets street name.
     *
     * @return the street name
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street name.
     *
     * @param street the street name
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets building number.
     *
     * @return the building number
     */
    public int getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * Sets building number.
     *
     * @param buildingNumber the building number
     */
    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        /**
         * Presenting how serialization and deserialization works.
         */
        // creating test addresses
        Address gdansk = new Address("Gdansk", "Poland", "Gabriela Narutowicza", 11);
        Address gdynia = new Address("Gdynia", "Poland", "Morska", 81);
        Address sopot = new Address("Sopot", "Pomorska", 5);

        ArrayList<Address> listOfAddresses = new ArrayList<Address>();
        listOfAddresses.add(gdansk);
        listOfAddresses.add(gdynia);
        listOfAddresses.add(sopot);

        System.out.println("List of addresses:");
        for (int i=0; i<listOfAddresses.size(); i++)
            System.out.println(listOfAddresses.get(i));

        String serializationPathWithFile = "output_data\\addresses.ser";

        AddressSerializer addressSerializer = new AddressSerializer();
        addressSerializer.serializeToFile(listOfAddresses, serializationPathWithFile);

        ArrayList<Address> deserializedListOfAddresses = addressSerializer.deserializeFromFile(serializationPathWithFile);
        if (deserializedListOfAddresses.size() > 0) {
            System.out.println("Deserialized list of Addresses from the file:");
            for (int i=0; i<deserializedListOfAddresses.size(); i++)
                System.out.println(deserializedListOfAddresses.get(i));
        }
    }
}
