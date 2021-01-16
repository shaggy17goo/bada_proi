package bada_proi.entity;

public class Classroom {
    private int classroomId;
    private int maxCapacity;
    private String description;
    private String type;
    private int culturalCenterId;


    public Classroom(int classroomId, int maxCapacity, String description, String type, int culturalCenterId) {
        this.classroomId = classroomId;
        this.maxCapacity = maxCapacity;
        this.description = description;
        this.type = type;
        this.culturalCenterId = culturalCenterId;
    }

    public Classroom() {
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCulturalCenterId() {
        return culturalCenterId;
    }

    public void setCulturalCenterId(int culturalCenterId) {
        this.culturalCenterId = culturalCenterId;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomId=" + classroomId +
                ", maxCapacity=" + maxCapacity +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", culturalCenterId=" + culturalCenterId +
                '}';
    }
}
