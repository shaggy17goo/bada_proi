package bada_proi.dao;

import bada_proi.utils.ProjectConstants;
import bada_proi.entity.Classroom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassroomDAOTest {
    private ClassroomDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new ClassroomDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Classroom> classroomList = dao.list();
        System.out.println(classroomList);
        assertFalse(classroomList.isEmpty());
    }

    @Test
    void save() {
        /*Classroom classroom = new Classroom(105, 31, "Test", "EDUKACYJNA", 1);
        dao.save(classroom);*/

    }

    @Test
    void get() {
        /*int id = 7;
        Classroom classroom = dao.get(id);
        System.out.println(classroom);
        assertNotNull(classroom);*/
    }

    @Test
    void update() {
        /*Classroom classroom = new Classroom();
        classroom.setClassroomId(2);
        classroom.setMaxCapacity(8);
        classroom.setDescription("ehhhhhh");
        classroom.setType("EDUKACYJNA");
        classroom.setCulturalCenterId(1);
        dao.update(classroom);*/
    }

    @Test
    void delete() {
        /*int id = 102;
        dao.delete(id);*/
    }
}