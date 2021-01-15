package bada_proi.entity;

import java.sql.Date;

public class CourseRealization {
    private int realizationId;
    private String price;
    private Date startDate;
    private Date finishDate;
    private String description;
    private int courseId;
    private int classroomId;

    public CourseRealization(int realizationId, String price, Date startDate, Date finishDate, String description, int courseId, int classroomId) {
        this.realizationId = realizationId;
        this.price = price;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.description = description;
        this.courseId = courseId;
        this.classroomId = classroomId;
    }

    public CourseRealization() {
    }

    public int getRealizationId() {
        return realizationId;
    }

    public void setRealizationId(int realizationId) {
        this.realizationId = realizationId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }


    @Override
    public String toString() {
        return "CourseRealization{" +
                "realizationId=" + realizationId +
                ", price='" + price + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", description='" + description + '\'' +
                ", courseId=" + courseId +
                ", classroomId=" + classroomId +
                '}';
    }
}
