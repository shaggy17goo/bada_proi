package bada_proi.forms;

import org.springframework.lang.Nullable;

import java.sql.Date;

public class ParticipantInfo {

    private int participantId;
    private int userId;
    private String username;
    private String name;
    private String surname;
    private Date birthDate;
    @Nullable
    private String pesel;
    private String gender;
    private String phoneNumber;
    @Nullable
    private String email;
    private String city;
    private String street;
    private String houseNumber;
    private String code;
    private String postCity;


    public ParticipantInfo(int participantId, int userId, String username, String name, String surname, Date birthDate, @Nullable String pesel, String gender, String phoneNumber, @Nullable String email, String city, String street, String houseNumber, String code, String postCity) {
        this.participantId = participantId;
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.pesel = pesel;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.code = code;
        this.postCity = postCity;
    }

    public ParticipantInfo() {
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
