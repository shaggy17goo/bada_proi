package bada_proi.dao;

import bada_proi.entity.UserRole;
import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class UserRoleDAOTest {

    private UserRoleDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new UserRoleDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        /*List<UserRole> userRoleList = dao.list();
        System.out.println(userRoleList);
        assertFalse(userRoleList.isEmpty());*/
    }

    @Test
    void save() {
        UserRole userRole = new UserRole(1, 4);
        dao.save(userRole);

    }

    @Test
    void get() {
        int userId = 1;
        int roleId = 4;
        UserRole userRole = dao.get(userId,roleId);
        System.out.println(userRole);
        assertNotNull(userRole);
    }

    @Test
    void delete() {
        int userId = 1;
        int roleId = 4;
        dao.delete(userId,roleId);
    }
}