package bada_proi.forms;

import java.sql.Date;

public class CourseInfo {
    //CLASSROOM
    private int classroomId;
    //REALIZATION
    private String price;
    private Date startDate;
    private Date finishDate;
    private int realizationId;
    //COURSE
    private int courseId;
    private String courseName;
    private int maxParticipants;
    private String description;
    //EMPLOYEE
    private String employeeName;
    private String surname;
    private String email;

    public CourseInfo() {
    }
    public CourseInfo(int classroomId, String price, Date startDate, Date finishDate, int realizationId, int courseId, String courseName, int maxParticipants, String description, String employeeName, String surname, String email) {
        this.classroomId = classroomId;
        this.price = price;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.realizationId = realizationId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.maxParticipants = maxParticipants;
        this.description = description;
        this.employeeName = employeeName;
        this.surname = surname;
        this.email = email;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRealizationId() {
        return realizationId;
    }

    public void setRealizationId(int realizationId) {
        this.realizationId = realizationId;
    }
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
