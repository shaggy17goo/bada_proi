package bada_proi.dao;

import bada_proi.entity.Salary;
import bada_proi.forms.CourseInfo;
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
public class SalaryDAO {//[DAO] Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public SalaryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list of salary got from database
     */
    public List<Salary> list() {
        String sql = "SELECT * FROM SALARIES";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Salary.class));
    }

    // CRUD methods
    /** Insert */
    public void save(Salary salary){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("salaries").usingColumns("salaryId","dateOfPayment","standardAmount","extraAmount","employeeId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(salary);
        insertActor.execute(param);
    }
    /** Read */
    public Salary get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM SALARIES WHERE salaryId = " + args[0];
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(Salary.class));
    }
    /** Update */
    public void update(Salary salary){
        String sql = "UPDATE SALARIES SET dateOfPayment=:dateOfPayment, standardAmount=:standardAmount, extraAmount=:extraAmount, employeeId=:employeeId WHERE salaryId=:salaryId";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(salary);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql,param);
    }
    /** Delete */
    public void delete(int id){
        String sql = "DELETE FROM SALARIES WHERE salaryId = ?";
        jdbcTemplate.update(sql,id);
    }

    public List<Salary> getSalariesByEmployeeId (int employeeId){
        String sql = "SELECT * FROM SALARIES WHERE EMPLOYEEID = " + employeeId;
        return jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Salary.class));
    }


}
