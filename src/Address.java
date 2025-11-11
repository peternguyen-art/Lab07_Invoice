public class Address {
    private String street;
    private String apt;
    private String city;
    private String state;
    private String zip;

    public Address(String street, String apt, String city, String state, String zip) {
        this.street = street;
        this.apt = apt;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getFormattedAddress() {
        String aptPart = apt.isEmpty() ? "" : apt + " ";
        return street + "\n" + aptPart + city + ", " + state + " " + zip;
    }
}