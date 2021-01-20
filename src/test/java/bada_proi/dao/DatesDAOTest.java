package bada_proi.dao;

import bada_proi.utils.ProjectConstants;
import bada_proi.entity.Dates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatesDAOTest {
    private DatesDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new DatesDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        /*List<Dates> datesList = dao.list();
        System.out.println(datesList);
        assertFalse(datesList.isEmpty());*/
    }

    @Test
    void save() {
        /*Dates dates = new Dates(105,new Date(2005,5,14), new Date(2007,2,4), 2);
        dao.save(dates);*/

    }

    @Test
    void get() {
        /*int id = 42;
        Dates dates = dao.get(id);
        System.out.println(dates);
        assertNotNull(dates);*/
    }

    @Test
    void update() {
        /*Dates dates = new Dates();
        dates.setDateId(42);
        dates.setStartDate(new Date(2005,5,14));
        dates.setFinishDate(new Date(2005,5,15));
        dao.update(dates);*/
    }

    @Test
    void delete() {
        /*int id = 1;
        dao.delete(id);*/
    }
}