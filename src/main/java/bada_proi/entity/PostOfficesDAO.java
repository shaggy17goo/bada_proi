package bada_proi.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostOfficesDAO {//[DAO] Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public PostOfficesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list of post offices got from database
     */
    public List<PostOffice> list() {
        String sql = "SELECT * FROM POSTOFFICES";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PostOffice.class));
    }

    // CRUD methods
    /** Insert */
    public void save(PostOffice postOffice){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("postOffices").usingColumns("postOfficeId","code","city");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(postOffice);
        insertActor.execute(param);
    }
    /** Read */
    public PostOffice get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM POSTOFFICES WHERE postOfficeId = " + args[0];
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(PostOffice.class));
    }
    /** Update */
    public void update(PostOffice postOffice){
        String sql = "UPDATE POSTOFFICES SET code=:code, city=:city WHERE postOfficeId=:postOfficeId";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(postOffice);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql,param);
    }
    /** Delete */
    public void delete(int id){

    }
}
