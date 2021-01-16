package bada_proi.entity;

import java.sql.Date;

public class Salary {
    private int salaryId;
    private Date dateOfPayment;
    private String standardAmount;
    private String extraAmount;
    private int employeeId;


    public Salary(int salaryId, Date dateOfPayment, String standardAmount, String extraAmount, int employeeId) {
        this.salaryId = salaryId;
        this.dateOfPayment = dateOfPayment;
        this.standardAmount = standardAmount;
        this.extraAmount = extraAmount;
        this.employeeId = employeeId;
    }

    public Salary() {
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public String getStandardAmount() {
        return standardAmount;
    }

    public void setStandardAmount(String standardAmount) {
        this.standardAmount = standardAmount;
    }

    public String getExtraAmount() {
        return extraAmount;
    }

    public void setExtraAmount(String extraAmount) {
        this.extraAmount = extraAmount;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryId=" + salaryId +
                ", dateOfPayment=" + dateOfPayment +
                ", standardAmount='" + standardAmount + '\'' +
                ", extraAmount='" + extraAmount + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}
