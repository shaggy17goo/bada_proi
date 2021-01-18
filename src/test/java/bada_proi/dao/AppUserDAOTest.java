package bada_proi.dao;

import bada_proi.entity.PostOffice;
import bada_proi.utils.ProjectConstants;
import bada_proi.entity.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

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
        /*List<AppUser> appUserList = dao.list();
        System.out.println(appUserList);
        assertFalse(appUserList.isEmpty());*/
    }

    @Test
    void save() {
        /*AppUser appUser = new AppUser(1,"test2","$2a$10$8HS4jDGmmkBiO9m9Vck0xOFxwlVQHKtcsAa.L5RUo5jI2bBrvPwde",0);
        dao.save(appUser);*/
    }

    @Test
    void get() {
       /* int id = 6;
        //String username = "user1";
        AppUser appUser = dao.get(id);
        System.out.println(appUser);
        assertNotNull(appUser);*/
    }

    @Test
    void update() {
        /*AppUser appUser = new AppUser();
        appUser.setUserId(1);
        appUser.setUsername("user1");
        appUser.setEncryptedPassword("$2a$10$8HS4jDGmmkBiO9m9Vck0xOFxwlVQHKtcsAa.L5RUo5jI2bBrvPwde");
        appUser.setEnabled(1);
        dao.update(appUser);*/
    }

    @Test
    void delete() {
    }
}