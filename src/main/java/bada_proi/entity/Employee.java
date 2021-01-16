package bada_proi.entity;

import org.springframework.lang.Nullable;

import java.sql.Date;

public class Employee {
    private int employeeId;
    private String type;
    private String name;
    private String surname;
    private Date birthDate;
    @Nullable
    private String pesel;
    private String gender;
    private String phoneNumber;
    private String email;
    private Date employmentDate;
    private String accountNumber;
    private int culturalCenterId;
    private int addressId;
    @Nullable
    private Integer userId;

    public Employee() {
    }

    public Employee(int employeeId, String type, String name, String surname, Date birthDate, @Nullable String pesel, String gender, String phoneNumber, String email, Date employmentDate, String accountNumber, int culturalCenterId, int addressId) {
        this.employeeId = employeeId;
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.pesel = pesel;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employmentDate = employmentDate;
        this.accountNumber = accountNumber;
        this.culturalCenterId = culturalCenterId;
        this.addressId = addressId;
        this.userId=null;

    }


    @Nullable
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@Nullable Integer userId) {
        this.userId = userId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCulturalCenterId() {
        return culturalCenterId;
    }

    public void setCulturalCenterId(int culturalCenterId) {
        this.culturalCenterId = culturalCenterId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", pesel='" + pesel + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", employmentDate=" + employmentDate +
                ", accountNumber='" + accountNumber + '\'' +
                ", culturalCenterId=" + culturalCenterId +
                ", addressId=" + addressId +
                ", userId=" + userId +
                '}';
    }
}
