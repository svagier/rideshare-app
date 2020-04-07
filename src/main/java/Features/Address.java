package main.java.Features;

/**
 * Address class to specify destination and origin of rides
 * (addresses to pickup passengers, to drop them off etc)
 *
 * @author Piotr Danielczyk
 * @version 1.0
 * @since 1.0
 */
public class Address implements Cloneable {
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
    public Address(String city, String street, int buildingNumber){
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
    public Address(String city, String country, String street, int buildingNumber){
        this.city = city;
        this.country = country;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        /**
         * generating various addresses
         */
        Address gdansk = new Address("Gdansk", "Poland", "Gabriela Narutowicza", 11);
        Address gdynia = new Address("Gdynia", "Poland", "Morska", 81);
        Address berlin = new Address("Berlin", "Germany", "Weissestrasse", 21);
        Address brest = new Address("Brest", "Belarus", "Sovetskaya", 143);
        System.out.println("Examples of addresses:");
        System.out.println(gdansk);
        System.out.println(gdynia);
        System.out.println(berlin);
        System.out.println(brest);
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

    @Override
    protected Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }

    /**
     * Is abroad boolean.
     *
     * @return true if the country is Poland, otherwise return false
     */
    public boolean isAbroad() {
        // returns true if country is different than Poland. Poland here is considered as homecountry
        if (this.country == "Poland") {
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

}
