package bada_proi.dao;

import bada_proi.entity.Employee;
import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOTest {
    private EmployeeDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new EmployeeDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        /*List<Employee> employeeList = dao.list();
        System.out.println(employeeList);
        assertFalse(employeeList.isEmpty());*/
    }

    @Test
    void save() {
       /* Employee employee = new Employee(105,"WOZNY", "Paweł", "Gryka", new Date(2000, 2, 2), "1234123412","M","987654321","pawelkox69@wp.pl", new Date(2000, 2, 2), "31241421341231231221",1, 7);
        dao.save(employee);*/

    }

    @Test
    void get() {
        /*int id = 1;
        Employee employee = dao.get(id);
        System.out.println(employee);
        assertNotNull(employee);*/
    }

    @Test
    void update() {
        /*Employee employee = new Employee();
        employee.setEmployeeId(99);
        employee.setType("WOZNY");
        employee.setName("Michał");
        employee.setSurname("Wawrzyńczak");
        employee.setBirthDate(new Date(2000, 2,5));
        employee.setPesel("12111");
        employee.setGender("M");
        employee.setPhoneNumber("123456789");
        employee.setEmail("ultrakox123@o2.pl");
        employee.setEmploymentDate(new Date(2000, 2,5));
        employee.setAccountNumber("412653424415123513");
        employee.setCulturalCenterId(1);
        employee.setAddressId(2);
        employee.setUserId(Integer.valueOf(1));
        dao.update(employee);*/
    }

    @Test
    void delete() {
        /*int id = 101;
        dao.delete(id);*/
    }
}