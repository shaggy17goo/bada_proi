package bada_proi.dao;

import bada_proi.entity.Course;
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
public class CourseDAO {//[DAO] Data Access Object – komponent dostarczający jednolity interfejs do komunikacji między aplikacją a źródłem danych

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public CourseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list of course got from database
     */
    public List<Course> list() {
        String sql = "SELECT * FROM COURSES";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Course.class));
    }

    // CRUD methods

    /**
     * Insert
     */
    public void save(Course course) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("courses").usingColumns("courseId", "name", "maxParticipants", "description", "culturalCenterId");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(course);
        insertActor.execute(param);
    }

    /**
     * Read
     */
    public Course get(int id) {
        Object[] args = {id};
        String sql = "SELECT * FROM COURSES WHERE courseId = " + args[0];
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Course.class));
    }

    /**
     * Update
     */
    public void update(Course course) {
        String sql = "UPDATE COURSES SET name=:name, maxParticipants=:maxParticipants, description=:description, culturalCenterId=:culturalCenterId WHERE courseId=:courseId";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(course);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    /**
     * Delete
     */
    public void delete(int id) {
        String sql = "DELETE FROM COURSES WHERE courseId = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<CourseInfo> getCourseInfoList(int id) {
        String sql = "SELECT COURSES.COURSEID, CLASSROOMS.classroomid, COURSESREALIZATIONS.price, COURSESREALIZATIONS.startdate, COURSESREALIZATIONS.finishdate, " +
                "COURSES.name AS coursename, COURSES.maxparticipants, COURSES.description AS courseDescription, " +
                "COURSESREALIZATIONS.description AS realizationDescription, EMPLOYEES.name AS employeeName, EMPLOYEES.surname, EMPLOYEES.email, COURSESREALIZATIONS.REALIZATIONID " +
                "FROM COURSESREALIZATIONS LEFT JOIN COURSES ON COURSESREALIZATIONS.COURSEID = COURSES.COURSEID " +
                "LEFT JOIN CLASSROOMS ON(COURSESREALIZATIONS.CLASSROOMID=CLASSROOMS.CLASSROOMID) " +
                "LEFT JOIN EMPLOYEES_REALIZATIONS ON (EMPLOYEES_REALIZATIONS.REALIZATIONID = COURSESREALIZATIONS.REALIZATIONID) " +
                "LEFT JOIN EMPLOYEES ON (EMPLOYEES.EMPLOYEEID = EMPLOYEES_REALIZATIONS.EMPLOYEEID) WHERE COURSES.COURSEID = " + id;

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CourseInfo.class));
    }

    public List<CourseInfo> getCourseInfoListFromRealizationId(int id) {
        String sql = "SELECT COURSES.COURSEID, CLASSROOMS.classroomid, COURSESREALIZATIONS.price, COURSESREALIZATIONS.startdate, COURSESREALIZATIONS.finishdate, " +
                "COURSES.name AS coursename, COURSES.maxparticipants, COURSES.description AS courseDescription, " +
                "COURSESREALIZATIONS.description AS realizationDescription, EMPLOYEES.name AS employeeName, EMPLOYEES.surname, EMPLOYEES.email, COURSESREALIZATIONS.REALIZATIONID " +
                "FROM COURSESREALIZATIONS LEFT JOIN COURSES ON COURSESREALIZATIONS.COURSEID = COURSES.COURSEID " +
                "LEFT JOIN CLASSROOMS ON(COURSESREALIZATIONS.CLASSROOMID=CLASSROOMS.CLASSROOMID) " +
                "LEFT JOIN EMPLOYEES_REALIZATIONS ON (EMPLOYEES_REALIZATIONS.REALIZATIONID = COURSESREALIZATIONS.REALIZATIONID) " +
                "LEFT JOIN EMPLOYEES ON (EMPLOYEES.EMPLOYEEID = EMPLOYEES_REALIZATIONS.EMPLOYEEID) WHERE COURSESREALIZATIONS.REALIZATIONID = " + id;

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CourseInfo.class));
    }
}
