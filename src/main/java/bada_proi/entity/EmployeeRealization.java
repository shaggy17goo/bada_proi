package bada_proi.entity;

public class EmployeeRealization {
    private int employeeId;
    private int realizationId;

    public EmployeeRealization(int employeeId, int realizationId) {
        this.employeeId = employeeId;
        this.realizationId = realizationId;
    }

    public EmployeeRealization() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getRealizationId() {
        return realizationId;
    }

    public void setRealizationId(int realizationId) {
        this.realizationId = realizationId;
    }

    @Override
    public String toString() {
        return "EmployeeRealization{" +
                "employeeId=" + employeeId +
                ", realizationId=" + realizationId +
                '}';
    }
}
