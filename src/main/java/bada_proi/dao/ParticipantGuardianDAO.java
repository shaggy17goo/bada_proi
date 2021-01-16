package bada_proi.dao;

import bada_proi.entity.ParticipantGuardian;
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
public class ParticipantGuardianDAO {//[DAO] Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ParticipantGuardianDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ParticipantGuardian> list() {
        String sql = "SELECT * FROM PARTICIPANTS_GUARDIANS";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ParticipantGuardian.class));
    }

    // CRUD methods
    /** Insert */
    public void save(ParticipantGuardian participantGuardian){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("participants_guardians").usingColumns("participantId","guardianId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(participantGuardian);
        insertActor.execute(param);
    }
    /** Read */
    public ParticipantGuardian get(int participantId, int guardianId){
        Object[] args = {participantId, guardianId};
        String sql = "SELECT * FROM PARTICIPANTS_GUARDIANS WHERE (participantId = " + args[0] + " AND guardianId = " + args[1]+")";
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(ParticipantGuardian.class));
    }

    /** Delete */
    public void delete(int participantId, int guardianId){
        String sql = "DELETE FROM PARTICIPANTS_GUARDIANS WHERE (participantId = " + participantId + " AND guardianId = " + guardianId+")";
        jdbcTemplate.update(sql);
    }
}
