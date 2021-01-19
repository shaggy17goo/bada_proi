package bada_proi.dao;

import bada_proi.entity.Employee;
import bada_proi.entity.EmployeeRealization;
import bada_proi.entity.ParticipantRealization;
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
public class EmployeeDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;


    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list of employees got from database
     */
    public List<Employee> list() {
        String sql = "SELECT * FROM EMPLOYEES";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Employee.class));
    }

    // CRUD methods
    /** Insert */
    public void save(Employee employee){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("employees").usingColumns("employeeId","type","name","surname","birthDate","pesel","gender","phoneNumber","email","employmentDate","accountNumber","culturalCenterId","addressId","userId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        insertActor.execute(param);
    }
    /** Read */
    public Employee get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM EMPLOYEES WHERE employeeId = " + args[0];
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(Employee.class));
    }
    /** Update */
    public void update(Employee employee){
        String sql = "UPDATE EMPLOYEES SET type=:type, name=:name, surname=:surname, birthDate=:birthDate, pesel=:pesel, gender=:gender, phoneNumber=:phoneNumber, email=:email, employmentDate=:employmentDate, accountNumber=:accountNumber, culturalCenterId=:culturalCenterId, addressId=:addressId, userId=:userId WHERE employeeId=:employeeId";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql,param);
    }
    /** Delete */
    public void delete(int id){
        String sql = "DELETE FROM EMPLOYEES WHERE employeeId = ?";
        jdbcTemplate.update(sql,id);
    }


}
