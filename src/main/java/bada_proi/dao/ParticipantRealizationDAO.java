package bada_proi.dao;

import bada_proi.entity.ParticipantRealization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ParticipantRealizationDAO {//[DAO] Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ParticipantRealizationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ParticipantRealization> list() {
        String sql = "SELECT * FROM PARTICIPANTS_REALIZATIONS";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ParticipantRealization.class));
    }

    // CRUD methods
    /** Insert */
    public void save(ParticipantRealization participantRealization){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("participants_realizations").usingColumns("participantId","realizationId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(participantRealization);
        insertActor.execute(param);
    }
    /** Read */
    public ParticipantRealization get(int participantId, int realizationId){
        Object[] args = {participantId, realizationId};
        String sql = "SELECT * FROM PARTICIPANTS_REALIZATIONS WHERE (participantId = " + args[0] + " AND realizationId = " + args[1]+")";
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(ParticipantRealization.class));
    }

    /** Delete */
    public void delete(int participantId, int realizationId){
        String sql = "DELETE FROM PARTICIPANTS_REALIZATIONS WHERE (participantId = " + participantId + " AND realizationId = " + realizationId+")";
        jdbcTemplate.update(sql);
    }

    public List<ParticipantRealization> participantCourses(int participantId){
        String sql = "SELECT * FROM PARTICIPANTS_REALIZATIONS WHERE PARTICIPANTID = " + participantId;
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ParticipantRealization.class));

    }
}
