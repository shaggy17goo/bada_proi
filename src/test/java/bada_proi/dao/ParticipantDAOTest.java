package bada_proi.dao;

import bada_proi.entity.Participant;
import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantDAOTest {
    private ParticipantDAO dao;
    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new ParticipantDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Participant> participantList = dao.list();
        System.out.println(participantList);
        assertFalse(participantList.isEmpty());
    }

    @Test
    void save() {
        Participant participant = new Participant(105,"Paweł", "Gryka",new Date(2000, 2, 2), "1234123412","M","987654321","pawelkox69@wp.pl",7);
        dao.save(participant);

    }

    @Test
    void get() {
        int id = 1;
        Participant participant = dao.get(id);
        System.out.println(participant);
        assertNotNull(participant);
    }

    @Test
    void update() {
        Participant participant = new Participant();
        participant.setParticipantId(1);
        participant.setName("Michał");
        participant.setSurname("Wawrzyńczak");
        participant.setBirthDate(new Date(2000, 2,5));
        participant.setPesel("12111");
        participant.setGender("M");
        participant.setPhoneNumber("123456789");
        participant.setEmail("ultrakox123@o2.pl");
        participant.setAddressId(2);
        participant.setUserId(Integer.valueOf(1));
        dao.update(participant);
    }

    @Test
    void delete() {
        int id = 101;
        dao.delete(id);
    }
}