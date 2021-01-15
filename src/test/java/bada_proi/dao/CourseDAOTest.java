package bada_proi.dao;

import bada_proi.entity.Course;
import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseDAOTest {
    private CourseDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new CourseDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Course> courseList = dao.list();
        System.out.println(courseList);
        assertFalse(courseList.isEmpty());
    }

    @Test
    void save() {
        Course course = new Course(105,"Zajęcia E-sportowe", 10,"nakurwiamy w csa",3);
        dao.save(course);

    }

    @Test
    void get() {
        int id = 2;
        Course course = dao.get(id);
        System.out.println(course);
        assertNotNull(course);
    }

    @Test
    void update() {
        Course course = new Course();
        course.setCourseId(4);
        course.setName("Test update");
        course.setMaxParticipants(10);
        course.setDescription("tak albo nie");
        course.setCulturalCenterId(1);
        dao.update(course);
    }

    @Test
    void delete() {
        int id = 102;
        dao.delete(id);
    }
}