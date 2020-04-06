package Features;

/**
 * Address class to specify destination and origin of rides
 * (addresses to pickup passengers, to drop them off etc)
 */

public class Address {
    private String city;
    private String country;
    private String street;
    private int buildingNumber;

    /**
     * constructor in case there is no country - we assume Poland is default
     */
   public Address(String city, String street, int buildingNumber){
        this.city = city;
        this.country = "Poland";
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public Address(String city, String country, String street, int buildingNumber){
        this.city = city;
        this.country = country;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

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

    public boolean isAbroad() {
        // returns true if country is different than Poland. Poland here is considered as homecountry
        if (this.country == "Poland") {
            return (false);
        }
        else {
            return (true);
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

}
