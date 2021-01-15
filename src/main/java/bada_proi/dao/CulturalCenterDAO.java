package bada_proi.dao;

import bada_proi.entity.CulturalCenter;
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
public class CulturalCenterDAO {//[DAO] Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public CulturalCenterDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list of cultural centers got from database
     */
    public List<CulturalCenter> list() {
        String sql = "SELECT * FROM CULTURALCENTERS";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CulturalCenter.class));
    }

    // CRUD methods
    /** Insert */
    public void save(CulturalCenter culturalCenter){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("culturalCenters").usingColumns("culturalCenterId","name","establishDate","phoneNumber","description","accountNumber", "addressId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(culturalCenter);
        insertActor.execute(param);
    }
    /** Read */
    public CulturalCenter get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM CULTURALCENTERS WHERE culturalCenterId = " + args[0];
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(CulturalCenter.class));
    }
    /** Update */
    public void update(CulturalCenter culturalCenter){
        String sql = "UPDATE CULTURALCENTERS SET name=:name, establishDate=:establishDate" +
                "phoneNumber:=phoneNumber, description:=description, accountNumber:=accountNumber," +
                "addressId:=addressId WHERE culturalCenterId=:culturalCenterId";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(culturalCenter);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql,param);
    }
    /** Delete */
    public void delete(int id){
        String sql = "DELETE FROM CULTURALCENTERS WHERE culturalCenterId = ?";
        jdbcTemplate.update(sql,id);
    }
}
