package bada_proi.entity;

public class Address {
    private int addressId;
    private String city;
    private String street;
    private String houseNumber;
    private int postOfficeId;

    public Address(int addressId, String city, String street, String houseNumber, int postOfficeId) {
        this.addressId = addressId;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postOfficeId = postOfficeId;
    }

    public Address() {
    }


    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getPostOfficeId() {
        return postOfficeId;
    }

    public void setPostOfficeId(int postOfficeId) {
        this.postOfficeId = postOfficeId;
    }


    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postOfficeId=" + postOfficeId +
                '}';
    }
}
