package bada_proi.entity;

public class Course {
    private int courseId;
    private String name;
    private int maxParticipants;
    private String description;
    private int culturalCenterId;

    public Course(int courseId, String name, int maxParticipants, String description, int culturalCenterId) {
        this.courseId = courseId;
        this.name = name;
        this.maxParticipants = maxParticipants;
        this.description = description;
        this.culturalCenterId = culturalCenterId;
    }

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCulturalCenterId() {
        return culturalCenterId;
    }

    public void setCulturalCenterId(int culturalCenterId) {
        this.culturalCenterId = culturalCenterId;
    }


    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", maxParticipants=" + maxParticipants +
                ", description='" + description + '\'' +
                ", culturalCenterId=" + culturalCenterId +
                '}';
    }
}
