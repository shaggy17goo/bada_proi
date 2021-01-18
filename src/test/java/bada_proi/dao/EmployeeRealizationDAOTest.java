package bada_proi.dao;

import bada_proi.entity.EmployeeRealization;
import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class EmployeeRealizationDAOTest {

    private EmployeeRealizationDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new EmployeeRealizationDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        /*List<EmployeeRealization> employeeRealizationList = dao.list();
        System.out.println(employeeRealizationList);
        assertFalse(employeeRealizationList.isEmpty());*/
    }

    @Test
    void save() {
        /*EmployeeRealization employeeRealization = new EmployeeRealization(1,1);
        dao.save(employeeRealization);*/

    }

    @Test
    void get() {
        /*int employeeId = 1;
        int realizationId = 1;
        EmployeeRealization employeeRealization = dao.get(employeeId,realizationId);
        System.out.println(employeeRealization);
        assertNotNull(employeeRealization);*/
    }

    @Test
    void delete() {
        /*int employeeId = 1;
        int realizationId = 1;
        dao.delete(employeeId,realizationId);*/
    }
}