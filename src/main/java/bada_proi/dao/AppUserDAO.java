package bada_proi.dao;

import bada_proi.entity.AppUser;
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
public class AppUserDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AppUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list users from database
     */
    public List<AppUser> list() {
        String sql = "SELECT * FROM appUsers";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(AppUser.class));
    }

    // CRUD methods
    /** Insert */
    public void save(AppUser appUser){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("appUsers").usingColumns("userId","username","encryptedPassword", "enabled");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(appUser);
        insertActor.execute(param);
    }
    /** Read */
    public AppUser get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM appUsers WHERE userId = " + args[0];
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(AppUser.class));
    }
    public AppUser get(String username){
        Object[] args = {username};
        String sql = "SELECT * FROM appUsers WHERE username = '" + args[0]+"'";
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(AppUser.class));
    }
    /** Update */
    public void update(AppUser appUser){
        String sql = "UPDATE appUsers SET username=:username, encryptedPassword=:encryptedPassword," +
                "enabled=:enabled, WHERE userId=:userId";

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(appUser);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql,param);
    }
    /** Delete */
    public void delete(int id){
        String sql = "DELETE FROM appUsers WHERE userId = ?";
        jdbcTemplate.update(sql,id);
    }

    public int getNextSeqId() {
        String sql = "SELECT USERSSEQ12.nextVal FROM DUAL";
        Integer ID = jdbcTemplate.queryForObject(sql,new Object[]{}, Integer.class);
        int id = ID.intValue();
        System.out.println(id);
        return id;
    }

}
