package bada_proi.dao;

import bada_proi.entity.Guardian;
import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuardianDAOTest {
    private GuardianDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new GuardianDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Guardian> guardianList = dao.list();
        System.out.println(guardianList);
        assertFalse(guardianList.isEmpty());
    }

    @Test
    void save() {
        Guardian guardian = new Guardian(105,"Paweł", "Gryka", new Date(2000, 2, 2), "1234123412","M","987654321","pawelkox69@wp.pl",7);
        dao.save(guardian);

    }

    @Test
    void get() {
        int id = 1;
        Guardian guardian = dao.get(id);
        System.out.println(guardian);
        assertNotNull(guardian);
    }

    @Test
    void update() {
        Guardian guardian = new Guardian();
        guardian.setGuardianId(1);
        guardian.setName("Michał");
        guardian.setSurname("Wawrzyńczak");
        guardian.setBirthDate(new Date(2000, 2,5));
        guardian.setPesel("12111");
        guardian.setGender("M");
        guardian.setPhoneNumber("123456789");
        guardian.setEmail("ultrakox123@o2.pl");
        guardian.setAddressId(2);
        guardian.setUserId(Integer.valueOf(1));
        dao.update(guardian);
    }

    @Test
    void delete() {
        int id = 101;
        dao.delete(id);
    }
}