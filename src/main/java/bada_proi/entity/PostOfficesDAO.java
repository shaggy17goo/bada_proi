package bada_proi.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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

    }
    /** Read */
    public PostOffice get(int id){
        return null;
    }
    /** Update */
    public void update(PostOffice postOffice){

    }
    /** Delete */
    public void delete(int id){

    }
}
