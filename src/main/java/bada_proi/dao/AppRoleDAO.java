package bada_proi.dao;

import bada_proi.entity.AppRole;
import bada_proi.entity.PostOffice;
import bada_proi.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.List;

@Repository
@Transactional
public class AppRoleDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AppRoleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AppRole getRoleName(int userId) {
        Object[] args = {userId};
        String sql = "SELECT approles.roleid,approles.rolename\n" +
                "FROM (( approles_appusers\n" +
                "INNER JOIN approles ON  approles_appusers.roleid = approles.roleid)\n" +
                "INNER JOIN appusers ON  approles_appusers.userid = appusers.userid)\n" +
                "WHERE appusers.userid = " + args[0];
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(AppRole.class));
    }

    public AppRole getRoleName(String username) {
        Object[] args = {username};
        String sql = "SELECT approles.roleid,approles.rolename\n" +
                "FROM (( approles_appusers\n" +
                "INNER JOIN approles ON  approles_appusers.roleid = approles.roleid)\n" +
                "INNER JOIN appusers ON  approles_appusers.userid = appusers.userid)\n" +
                "WHERE appusers.userid = " + args[0];
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(AppRole.class));
    }
}
