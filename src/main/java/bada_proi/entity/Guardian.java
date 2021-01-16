package bada_proi.entity;

import org.springframework.lang.Nullable;

import java.sql.Date;

public class Guardian {

    private int guardianId;
    private String name;
    private String surname;
    private Date birthDate;
    @Nullable
    private String pesel;
    private String gender;
    private String phoneNumber;
    private String email;
    private int addressId;
    @Nullable
    private Integer userId;

    public Guardian(int guardianId, String name, String surname, Date birthDate, @Nullable String pesel, String gender, String phoneNumber, String email, int addressId) {
        this.guardianId = guardianId;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.pesel = pesel;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressId = addressId;
        this.userId = null;

    }

    public Guardian() {
    }

    @Nullable
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@Nullable Integer userId) {
        this.userId = userId;
    }

    public int getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(int guardianId) {
        this.guardianId = guardianId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Nullable
    public String getPesel() {
        return pesel;
    }

    public void setPesel(@Nullable String pesel) {
        this.pesel = pesel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Guardian{" +
                "guardianId=" + guardianId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", pesel='" + pesel + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", addressId=" + addressId +
                ", userId=" + userId +
                '}';
    }
}
