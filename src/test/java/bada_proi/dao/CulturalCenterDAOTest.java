package bada_proi.dao;

import bada_proi.entity.CulturalCenter;
import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CulturalCenterDAOTest {
    private CulturalCenterDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new CulturalCenterDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<CulturalCenter> culturalCenterList = dao.list();
        System.out.println(culturalCenterList);
        assertFalse(culturalCenterList.isEmpty());
    }

    @Test
    void save() {
        CulturalCenter culturalCenter = new CulturalCenter(1,"Dom Kultury Gwiazdeczka", new Date(2000, 2, 2), "123456789","dom kultury gwiazdeczka","98765213123123123124321", 55);
        dao.save(culturalCenter);

    }

    @Test
    void get() {
        int id = 1;
        CulturalCenter culturalCenter = dao.get(id);
        System.out.println(culturalCenter);
        assertNotNull(culturalCenter);
    }

    @Test
    void update() {
        CulturalCenter culturalCenter = new CulturalCenter();
        culturalCenter.setCulturalCenterId(2);
        culturalCenter.setName("Dom Kultury Rybka");
        culturalCenter.setEstablishDate(new Date(2013, 3,5));
        culturalCenter.setPhoneNumber("987654321");
        culturalCenter.setDescription("dom kultury rybka");
        culturalCenter.setAccountNumber("82174091249123740");
        culturalCenter.setAddressId(6);
        dao.update(culturalCenter);
    }

    @Test
    void delete() {
        int id = 101;
        dao.delete(id);
    }
}