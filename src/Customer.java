public class Customer {
    private String name;
    private Address address;

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Method from UML: getCustomerBlock()
    public String getCustomerBlock() {
        return name + "\n" + address.getFormattedAddress();
    }
}