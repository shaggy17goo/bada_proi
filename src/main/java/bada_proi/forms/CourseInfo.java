package bada_proi.forms;

import java.sql.Date;

public class CourseInfo {
    //CLASSROOM
    private int classroomId;
    private String type;
    //REALIZATION
    private String price;
    private Date startDate;
    private Date finishDate;
    private int realizationId;
    //COURSE
    private int courseId;
    private String courseName;
    private int maxParticipants;
    private String courseDescription;
    //EMPLOYEE
    private String employeeName;
    private String surname;
    private String email;
    private String realizationDescription;


    public CourseInfo() {
    }
    public CourseInfo(int classroomId, String price, Date startDate, Date finishDate, int realizationId, int courseId, String courseName, int maxParticipants, String courseDescription, String employeeName, String surname, String email) {
        this.classroomId = classroomId;
        this.price = price;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.realizationId = realizationId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.maxParticipants = maxParticipants;
        this.courseDescription = courseDescription;
        this.employeeName = employeeName;
        this.surname = surname;
        this.email = email;
    }


    public String getRealizationDescription() {
        return realizationDescription;
    }

    public void setRealizationDescription(String realizationDescription) {
        this.realizationDescription = realizationDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
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
