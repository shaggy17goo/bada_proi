package bada_proi.dao;

import bada_proi.entity.Participant;
import bada_proi.forms.EmployeeInfo;
import bada_proi.forms.ParticipantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EmployeeInfoDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;


    public EmployeeInfoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public EmployeeInfo get(String username){
        String sql = "SELECT EMPLOYEES.EMPLOYEEID, APPUSERS.USERID, APPUSERS.USERNAME, EMPLOYEES.TYPE, EMPLOYEES.NAME, " +
                "       EMPLOYEES.SURNAME, EMPLOYEES.BIRTHDATE, EMPLOYEES.PESEL, EMPLOYEES.GENDER, " +
                "       EMPLOYEES.PHONENUMBER, EMPLOYEES.EMAIL, EMPLOYEES.EMPLOYMENTDATE, EMPLOYEES.ACCOUNTNUMBER, " +
                "       ADDRESSES.CITY, ADDRESSES.STREET, ADDRESSES.HOUSENUMBER, POSTOFFICES.CODE, POSTOFFICES.CITY AS POSTCITY " +
                "FROM EMPLOYEES LEFT JOIN APPUSERS ON (EMPLOYEES.USERID = APPUSERS.USERID) " +
                "    LEFT JOIN ADDRESSES ON (EMPLOYEES.ADDRESSID = ADDRESSES.ADDRESSID) " +
                "    LEFT JOIN POSTOFFICES ON (ADDRESSES.POSTOFFICEID = POSTOFFICES.POSTOFFICEID) " +
                "WHERE APPUSERS.USERNAME = '" + username+"'";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(EmployeeInfo.class));
    }

    public List<EmployeeInfo> list(){
        String sql = "SELECT EMPLOYEES.EMPLOYEEID, APPUSERS.USERID, APPUSERS.USERNAME, EMPLOYEES.TYPE, EMPLOYEES.NAME, " +
                "       EMPLOYEES.SURNAME, EMPLOYEES.BIRTHDATE, EMPLOYEES.PESEL, EMPLOYEES.GENDER, " +
                "       EMPLOYEES.PHONENUMBER, EMPLOYEES.EMAIL, EMPLOYEES.EMPLOYMENTDATE, EMPLOYEES.ACCOUNTNUMBER, " +
                "       ADDRESSES.CITY, ADDRESSES.STREET, ADDRESSES.HOUSENUMBER, POSTOFFICES.CODE, POSTOFFICES.CITY AS POSTCITY " +
                "FROM EMPLOYEES LEFT JOIN APPUSERS ON (EMPLOYEES.USERID = APPUSERS.USERID) " +
                "    LEFT JOIN ADDRESSES ON (EMPLOYEES.ADDRESSID = ADDRESSES.ADDRESSID) " +
                "    LEFT JOIN POSTOFFICES ON (ADDRESSES.POSTOFFICEID = POSTOFFICES.POSTOFFICEID) ";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(EmployeeInfo.class));
    }
}
