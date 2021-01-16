package bada_proi.dao;

import bada_proi.entity.Salary;
import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalaryDAOTest {
    private SalaryDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new SalaryDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Salary> salaryList = dao.list();
        System.out.println(salaryList);
        assertFalse(salaryList.isEmpty());
    }

    @Test
    void save() {
        Salary salary = new Salary(105,new Date(2020,02,9), "10,10", "10,10",8);
        dao.save(salary);

    }

    @Test
    void get() {
        int id = 42;
        Salary salary = dao.get(id);
        System.out.println(salary);
        assertNotNull(salary);
    }

    @Test
    void update() {
        Salary salary = new Salary();
        salary.setSalaryId(42);
        salary.setDateOfPayment(new Date(2020,02,9));
        salary.setStandardAmount("10,10");
        salary.setExtraAmount("0,1");
        salary.setEmployeeId(7);
        dao.update(salary);
    }

    @Test
    void delete() {
        int id = 102;
        dao.delete(id);
    }
}