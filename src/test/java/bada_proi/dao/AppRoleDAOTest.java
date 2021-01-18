package bada_proi.dao;

import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.*;

class AppRoleDAOTest {
    private AppRoleDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new AppRoleDAO(new JdbcTemplate(dataSource));
    }
    @Test
    void getRoleName() {
    }

    @Test
    void testGetRoleName() {
    }

    @Test
    void getRoleId() {
        //System.out.println(dao.getRoleId("ROLE_EMPLOYEE"));
    }
}