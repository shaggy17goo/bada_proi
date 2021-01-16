package bada_proi.dao;

import bada_proi.entity.ParticipantRealization;
import bada_proi.utils.ProjectConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class ParticipantRealizationDAOTest {

    private ParticipantRealizationDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(ProjectConstants.URL);
        dataSource.setUsername(ProjectConstants.USERNAME);
        dataSource.setPassword(ProjectConstants.PASSWORD);
        dataSource.setDriverClassName(ProjectConstants.DRIVERCLASSNAME);

        dao = new ParticipantRealizationDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<ParticipantRealization> participantRealizationList = dao.list();
        System.out.println(participantRealizationList);
        assertFalse(participantRealizationList.isEmpty());
    }

    @Test
    void save() {
        ParticipantRealization participantRealization = new ParticipantRealization(1, 1);
        dao.save(participantRealization);

    }

    @Test
    void get() {
        int participantId = 1;
        int realizationId = 1;
        ParticipantRealization participantRealization = dao.get(participantId,realizationId);
        System.out.println(participantRealization);
        assertNotNull(participantRealization);
    }

    @Test
    void delete() {
        int participantId = 1;
        int realizationId = 1;
        dao.delete(participantId,realizationId);
    }
}