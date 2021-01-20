package bada_proi.dao;

import bada_proi.entity.Guardian;
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
public class GuardianDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;


    public GuardianDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list of guardians got from database
     */
    public List<Guardian> list() {
        String sql = "SELECT * FROM GUARDIANS";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Guardian.class));
    }

    // CRUD methods
    /** Insert */
    public void save(Guardian guardian){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("guardians").usingColumns("guardianId","name","surname","birthDate","pesel","gender","phoneNumber","email","addressId","userId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(guardian);
        insertActor.execute(param);
    }
    /** Read */
    public Guardian get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM GUARDIANS WHERE guardianId = " + args[0];
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(Guardian.class));
    }
    /** Update */
    public void update(Guardian guardian){
        String sql = "UPDATE GUARDIANS SET name=:name , surname=:surname , birthDate=:birthDate , pesel=:pesel , gender=:gender , phoneNumber=:phoneNumber , email=:email , addressId=:addressId, userId=:userId WHERE guardianId=:guardianId";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(guardian);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql,param);
    }
    /** Delete */
    public void delete(int id){
        String sql = "DELETE FROM GUARDIANS WHERE guardianId = ?";
        jdbcTemplate.update(sql,id);
    }
}
