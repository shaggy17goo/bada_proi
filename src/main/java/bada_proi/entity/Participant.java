package bada_proi.entity;

import org.springframework.lang.Nullable;

public class Participant {
    private int participantId;
    private String name;
    private String surname;
    private String date; //TODO check if date type works
    @Nullable
    private String pesel;
    private Gender gender;
    private String phoneNumber;
    @Nullable
    private String email;
    private int addressId;

    public Participant() {
    }

    public Participant(int participantId, String name, String surname, String date, @Nullable String pesel, Gender gender, String phoneNumber, @Nullable String email, int addressId) {
        this.participantId = participantId;
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.pesel = pesel;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressId = addressId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Nullable
    public String getPesel() {
        return pesel;
    }

    public void setPesel(@Nullable String pesel) {
        this.pesel = pesel;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
