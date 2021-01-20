package bada_proi.entity;

import org.springframework.lang.Nullable;

import java.sql.Date;

public class CulturalCenter {
    private int culturalCenterId;
    private String name;
    private Date establishDate;
    private String phoneNumber;
    private String description;
    @Nullable
    private String accountNumber;
    private int addressId;

    public CulturalCenter() {
    }

    public CulturalCenter(int culturalCenterId, String name, Date establishDate, String phoneNumber, String description, @Nullable String accountNumber, int addressId) {
        this.culturalCenterId = culturalCenterId;
        this.name = name;
        this.establishDate = establishDate;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.accountNumber = accountNumber;
        this.addressId = addressId;
    }

    public int getCulturalCenterId() {
        return culturalCenterId;
    }

    public void setCulturalCenterId(int culturalCenterId) {
        this.culturalCenterId = culturalCenterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@Nullable String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "CulturalCenter{" +
                "culturalCenterId=" + culturalCenterId +
                ", name='" + name + '\'' +
                ", establishDate=" + establishDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", addressId=" + addressId +
                '}';
    }
}
