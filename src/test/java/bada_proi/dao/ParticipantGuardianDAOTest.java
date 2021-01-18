package bada_proi.dao;

import bada_proi.entity.ParticipantGuardian;
import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class ParticipantGuardianDAOTest {

    private ParticipantGuardianDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new ParticipantGuardianDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        /*List<ParticipantGuardian> participantGuardianList = dao.list();
        System.out.println(participantGuardianList);
        assertFalse(participantGuardianList.isEmpty());*/
    }

    @Test
    void save() {
        /*ParticipantGuardian participantGuardian = new ParticipantGuardian(1, 1);
        dao.save(participantGuardian);*/

    }

    @Test
    void get() {
        /*int participantId = 1;
        int guardianId = 1;
        ParticipantGuardian participantGuardian = dao.get(participantId,guardianId);
        System.out.println(participantGuardian);
        assertNotNull(participantGuardian);*/
    }

    @Test
    void delete() {
        /*int participantId = 1;
        int guardianId = 1;
        dao.delete(participantId,guardianId);*/
    }
}