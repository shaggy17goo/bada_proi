package bada_proi.dao;

import bada_proi.entity.CourseRealization;
import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseRealizationDAOTest {
    private CourseRealizationDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new CourseRealizationDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<CourseRealization> courseRealizationList = dao.list();
        System.out.println(courseRealizationList);
        assertFalse(courseRealizationList.isEmpty());
    }

    @Test
    void save() {
        CourseRealization courseRealization = new CourseRealization(3,"22,22", new Date(2005,5,14), new Date(2007,2,4),"nakurwiamy w csa",3,9);
        dao.save(courseRealization);

    }

    @Test
    void get() {
        int id = 2;
        CourseRealization courseRealization = dao.get(id);
        System.out.println(courseRealization);
        assertNotNull(courseRealization);
    }

    @Test
    void update() {
        CourseRealization courseRealization = new CourseRealization();
        courseRealization.setRealizationId(4);
        courseRealization.setPrice("22,22");
        courseRealization.setStartDate(new Date(2019,1,4));
        courseRealization.setFinishDate(new Date(2018,2,4));
        courseRealization.setDescription("co tam byczq");
        courseRealization.setCourseId(4);
        courseRealization.setClassroomId(2);
        dao.update(courseRealization);
    }

    @Test
    void delete() {
        int id = 4;
        dao.delete(id);
    }
}