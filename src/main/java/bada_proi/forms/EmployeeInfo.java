package bada_proi.forms;

import org.springframework.lang.Nullable;

import java.sql.Date;

public class EmployeeInfo {
    private int employeeId;
    @Nullable
    private Integer userId;
    @Nullable
    private String username;
    private String password;
    private String type;
    private String name;
    private String surname;
    private Date birthDate;
    @Nullable
    private String pesel;
    private String gender;
    private String phoneNumber;
    @Nullable
    private String email;
    private Date employmentDate;
    private String accountNumber;
    private String city;
    private String street;
    private String houseNumber;
    private String code;
    private String postCity;

    public EmployeeInfo(int employeeId, @Nullable Integer userId, @Nullable String username, String password, String type, String name, String surname, Date birthDate, @Nullable String pesel, String gender, String phoneNumber, @Nullable String email, Date employmentDate, String accountNumber, String city, String street, String houseNumber, String code, String postCity) {
        this.employeeId = employeeId;
        this.userId = userId;
        this.username = username;
        this.password = password;
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
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.code = code;
        this.postCity = postCity;
    }

    public EmployeeInfo() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Nullable
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@Nullable Integer userId) {
        this.userId = userId;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    public void setUsername(@Nullable String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPostCity() {
        return postCity;
    }

    public void setPostCity(String postCity) {
        this.postCity = postCity;
    }
}
