package bada_proi.dao;

import bada_proi.entity.Dates;
import bada_proi.entity.Salary;
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
public class DatesDAO {//[DAO] Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public DatesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list of dates got from database
     */
    public List<Dates> list() {
        String sql = "SELECT * FROM DATES";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Dates.class));
    }

    // CRUD methods
    /** Insert */
    public void save(Dates dates){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("dates").usingColumns("dateId","startDate","finishDate", "realizationId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(dates);
        insertActor.execute(param);
    }
    /** Read */
    public Dates get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM DATES WHERE dateId = " + args[0];
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(Dates.class));
    }
    /** Update */
    public void update(Dates dates){
        String sql = "UPDATE DATES SET startDate=:startDate, finishDate=:finishDate, realizationId=:realizationId WHERE dateId=:dateId";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(dates);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql,param);
    }
    /** Delete */
    public void delete(int id){
        String sql = "DELETE FROM DATES WHERE dateId = ?";
        jdbcTemplate.update(sql,id);
    }

    public List<Dates> getDatesByRealizationId (int realizationId){
        String sql = "SELECT * FROM DATES WHERE REALIZATIONID = " + realizationId;
        return jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Dates.class));
    }
}
