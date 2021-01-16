package bada_proi.dao;

import bada_proi.entity.Classroom;
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
public class ClassroomDAO {//[DAO] Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ClassroomDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list of classrooms got from database
     */
    public List<Classroom> list() {
        String sql = "SELECT * FROM CLASSROOMS";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Classroom.class));
    }

    // CRUD methods
    /** Insert */
    public void save(Classroom classroom){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("classrooms").usingColumns("classroomId","maxCapacity","description", "type", "culturalCenterId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(classroom);
        insertActor.execute(param);
    }
    /** Read */
    public Classroom get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM CLASSROOMS WHERE classroomId = " + args[0];
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(Classroom.class));
    }
    /** Update */
    public void update(Classroom classroom){
        String sql = "UPDATE CLASSROOMS SET maxCapacity=:maxCapacity, description=:description, type=:type, culturalCenterId=:culturalCenterId WHERE classroomId=:classroomId";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(classroom);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql,param);
    }
    /** Delete */
    public void delete(int id){
        String sql = "DELETE FROM CLASSROOMS WHERE classroomId = ?";
        jdbcTemplate.update(sql,id);
    }
}
