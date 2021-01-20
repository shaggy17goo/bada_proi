package bada_proi.dao;

import bada_proi.entity.EmployeeRealization;
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
public class EmployeeRealizationDAO {//[DAO] Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public EmployeeRealizationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EmployeeRealization> list() {
        String sql = "SELECT * FROM EMPLOYEES_REALIZATIONS";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(EmployeeRealization.class));
    }

    // CRUD methods
    /** Insert */
    public void save(EmployeeRealization employeeRealization){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("employees_realizations").usingColumns("employeeId","realizationId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(employeeRealization);
        insertActor.execute(param);
    }
    /** Read */
    public EmployeeRealization get(int employeeId, int realizationId){
        Object[] args = {employeeId, realizationId};
        String sql = "SELECT * FROM EMPLOYEES_REALIZATIONS WHERE (employeeId = " + args[0] + " AND realizationId = " + args[1]+")";
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(EmployeeRealization.class));
    }

    /** Delete */
    public void delete(int employeeId, int realizationId){
        String sql = "DELETE FROM EMPLOYEES_REALIZATIONS WHERE (employeeId = " + employeeId + " AND realizationId = " + realizationId+")";
        jdbcTemplate.update(sql);
    }

    public List<EmployeeRealization> employeeCourses(int employeeId){
        String sql = "SELECT * FROM EMPLOYEES_REALIZATIONS WHERE EMPLOYEEID = " + employeeId;
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(EmployeeRealization.class));

    }
}
