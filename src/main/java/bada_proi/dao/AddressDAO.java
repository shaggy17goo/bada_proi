package bada_proi.dao;

import bada_proi.entity.Address;
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
public class AddressDAO {//[DAO] Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AddressDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list of address got from database
     */
    public List<Address> list() {
        String sql = "SELECT * FROM ADDRESSES";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Address.class));
    }

    // CRUD methods
    /** Insert */
    public void save(Address address){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("addresses").usingColumns("addressId","city","street","houseNumber","postOfficeId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(address);
        insertActor.execute(param);
    }
    /** Read */
    public Address get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM ADDRESSES WHERE addressId = " + args[0];
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(Address.class));
    }
    /** Update */
    public void update(Address address){
        String sql = "UPDATE ADDRESSES SET city=:city, street=:street, houseNumber=:houseNumber, postOfficeId=:postOfficeId WHERE addressId=:addressId";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(address);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql,param);
    }
    /** Delete */
    public void delete(int id){
        String sql = "DELETE FROM ADDRESSES WHERE addressId = ?";
        jdbcTemplate.update(sql,id);
    }

    public int getNextSeqId() {
        String sql = "SELECT ADRESSESSEQ8.nextVal FROM DUAL";
        Integer ID = jdbcTemplate.queryForObject(sql,new Object[]{}, Integer.class);
        int id = ID.intValue();
        System.out.println(id);
        return id;
    }
}
