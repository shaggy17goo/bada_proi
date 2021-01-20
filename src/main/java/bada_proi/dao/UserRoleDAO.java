package bada_proi.dao;

import bada_proi.entity.UserRole;
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
public class UserRoleDAO {//[DAO] Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserRoleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserRole> list() {
        String sql = "SELECT * FROM APPROLES_APPUSERS";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UserRole.class));
    }

    // CRUD methods
    /** Insert */
    public void save(UserRole userRole){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("approles_appusers").usingColumns("userId","roleId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(userRole);
        insertActor.execute(param);
    }
    /** Read */
    public UserRole get(int userId, int roleId){
        Object[] args = {userId, roleId};
        String sql = "SELECT * FROM APPROLES_APPUSERS WHERE (userId = " + args[0] + " AND roleId = " + args[1]+")";
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(UserRole.class));
    }

    /** Delete */
    public void delete(int userId, int roleId){
        String sql = "DELETE FROM APPROLES_APPUSERS WHERE (userId = " + userId + " AND roleId = " + roleId+")";
        jdbcTemplate.update(sql);
    }
    /** Delete */
    public void delete(int userId){
        String sql = "DELETE FROM APPROLES_APPUSERS WHERE userId = " + userId;
        jdbcTemplate.update(sql);
    }
}
