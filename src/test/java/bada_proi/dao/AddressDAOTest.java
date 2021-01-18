package bada_proi.dao;

import bada_proi.entity.Address;
import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressDAOTest {
    private AddressDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new AddressDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Address> addressList = dao.list();
        System.out.println(addressList);
        assertFalse(addressList.isEmpty());
    }

    @Test
    void save() {
        Address address = new Address(105,"Gzinka", "Gzinka","111a",3);
        dao.save(address);

    }

    @Test
    void get() {
        /*int id = 105;
        Address address = dao.get(id);
        System.out.println(address);
        assertNotNull(address);*/
    }

    @Test
    void update() {
        /*Address address = new Address();
        address.setAddressId(42);
        address.setCity("Test update");
        address.setStreet("Kozia");
        address.setHouseNumber("231d");
        address.setPostOfficeId(7);
        dao.update(address);*/
    }

    @Test
    void getNextSeqId() {
        int id = dao.getNextSeqId();
        System.out.println(id);
        assertNotNull(id);
    }

    @Test
    void delete() {
        /*int id = 102;
        dao.delete(id);*/
    }
}