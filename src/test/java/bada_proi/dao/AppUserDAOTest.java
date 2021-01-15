package bada_proi.dao;

import bada_proi.utils.ProjectConstants;
import bada_proi.entity.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.*;
class AppUserDAOTest {

    private AppUserDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new AppUserDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
    }

    @Test
    void save() {
    }

    @Test
    void get() {
        int id = 1;
        //String username = "user1";
        AppUser appUser = dao.get(id);
        System.out.println(appUser);
        assertNotNull(appUser);
    }

    @Test
    void update() {
        AppUser appUser = new AppUser();
        appUser.setUserId(1);
        appUser.setUsername("user1");
        appUser.setEncryptedPassword("$2a$10$8HS4jDGmmkBiO9m9Vck0xOFxwlVQHKtcsAa.L5RUo5jI2bBrvPwde");
        appUser.setEnabled(1);
        appUser.setParticipantId(null);
        appUser.setGuardianId(null);
        appUser.setEmployeeId(null);
        dao.update(appUser);
    }

    @Test
    void delete() {
    }
}