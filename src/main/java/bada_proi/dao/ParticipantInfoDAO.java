package bada_proi.dao;

import bada_proi.entity.Participant;
import bada_proi.forms.ParticipantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ParticipantInfoDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;


    public ParticipantInfoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public ParticipantInfo get(String username){
        String sql = "SELECT PARTICIPANTS.PARTICIPANTID, APPUSERS.USERID, APPUSERS.USERNAME, PARTICIPANTS.NAME, " +
                "    PARTICIPANTS.SURNAME, PARTICIPANTS.BIRTHDATE, PARTICIPANTS.PESEL, PARTICIPANTS.GENDER, " +
                "    PARTICIPANTS.PHONENUMBER, PARTICIPANTS.EMAIL, ADDRESSES.CITY, ADDRESSES.STREET, " +
                "    ADDRESSES.HOUSENUMBER, POSTOFFICES.CODE, POSTOFFICES.CITY AS POSTCITY " +
                "FROM PARTICIPANTS LEFT JOIN APPUSERS ON (PARTICIPANTS.USERID = APPUSERS.USERID) " +
                "    LEFT JOIN ADDRESSES ON (PARTICIPANTS.ADDRESSID = ADDRESSES.ADDRESSID) " +
                "    LEFT JOIN POSTOFFICES ON (ADDRESSES.POSTOFFICEID = POSTOFFICES.POSTOFFICEID) " +
                "WHERE APPUSERS.USERNAME = '" + username+"'";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(ParticipantInfo.class));
    }


}
