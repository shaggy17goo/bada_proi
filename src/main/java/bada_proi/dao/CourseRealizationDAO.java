package bada_proi.dao;

import bada_proi.entity.CourseRealization;
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
public class CourseRealizationDAO {//[DAO] Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public CourseRealizationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list of courses realizations got from database
     */
    public List<CourseRealization> list() {
        String sql = "SELECT * FROM COURSESREALIZATIONS";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CourseRealization.class));
    }

    // CRUD methods
    /** Insert */
    public void save(CourseRealization courseRealization){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("coursesRealizations").usingColumns("realizationId","price","startDate","finishDate","description","courseId","classroomId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(courseRealization);
        insertActor.execute(param);
    }
    /** Read */
    public CourseRealization get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM COURSESREALIZATIONS WHERE realizationId = " + args[0];
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(CourseRealization.class));
    }
    /** Update */
    public void update(CourseRealization courseRealization){
        String sql = "UPDATE COURSESREALIZATIONS SET price=:price, startDate=:startDate, finishDate=:finishDate, description=:description , courseId=:courseId , classroomId=:classroomId WHERE realizationId=:realizationId";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(courseRealization);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql,param);
    }
    /** Delete */
    public void delete(int id){
        String sql = "DELETE FROM COURSESREALIZATIONS WHERE realizationId = ?";
        jdbcTemplate.update(sql,id);
    }
}
