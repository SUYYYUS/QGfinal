package com.suyyyus.dao.impl;

import com.suyyyus.dao.CourseDao;
import com.suyyyus.pojo.Course;
import com.suyyyus.utils.CRUDUtils;
import com.suyyyus.utils.JDBCUtil;
import com.suyyyus.utils.MyConnectionPool;
import com.suyyyus.utils.TimeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    static MyConnectionPool myConnectionPool = new MyConnectionPool();

    /**
     * 添加课程
     * @param course
     * @throws SQLException
     */
    @Override
    public void addCourse(Course course) throws SQLException {
        String sql = "insert into tb_course (coursename, subject, description, teacher_id, limitnumber, create_time, end_time) " +
                "values (?,?,?,?,?,?,?)";

        CRUDUtils.ZengShanGai(sql,course.getCoursename(), course.getSubject(),course.getDescription(),course.getTeacher_id(),course.getLimitnumber(), TimeUtil.formatDateTime(LocalDateTime.now()),TimeUtil.formatDateTime(LocalDateTime.now().plus(7, ChronoUnit.DAYS)));
    }


    /**
     * 通过课程ID来寻找该课程
     * @param course_id
     * @return
     */
    @Override
    public Course queryByCourse_id(int course_id) throws SQLException {
        String sql = "select * from tb_course where id = ?";
        //获取连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, course_id);
        //执行
        ResultSet resultSet = preparedStatement.executeQuery();

        Course course = new Course();

        if(resultSet.next()){
            course.setId(resultSet.getInt("id"));
            course.setCoursename(resultSet.getString("coursename"));
            course.setSubject(resultSet.getString("subject"));
            course.setDescription(resultSet.getString("description"));
            course.setTeacher_id(resultSet.getInt("teacher_id"));
            course.setSection_number(resultSet.getInt("section_number"));
            course.setLimitnumber(resultSet.getInt("limitnumber"));
            course.setRegisternumber(resultSet.getInt("registernumber"));
            course.setCreate_time(resultSet.getString("create_time"));
            course.setEnd_time(resultSet.getString("end_time"));
        } else {
            course = null;
        }
        return course;
    }


    /**
     *通过老师的id查找他所开设的课程
     * @param teacher_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Course> queryAllCourseByTeacher_id(int teacher_id) throws Exception {
        String sql = "select * from tb_course where teacher_id = ?";

        List<Course> courses = CRUDUtils.queryAllCourseByTeacher_id(sql, teacher_id);

        return courses;
    }


    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Override
    public List<Course> selectByPage(int begin, int size) {
        String sql = "select * from tb_course limit  ? , ?";
        List<Course> list = null;
        try {
            list = CRUDUtils.selectByPage(sql,begin,size);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询课程数量
     * @return
     * @throws Exception
     */
    @Override
    public int selectAllCount() throws Exception {
        String sql = "select count(*) from tb_course ";
        int count = CRUDUtils.allCount(sql);
        return count;
    }


    /**
     * 通过学科分类查询
     * @param subject
     * @return
     * @throws Exception
     */
    @Override
    public List<Course> queryBySubject(String subject) throws Exception {
        String sql = "select * from tb_course where subject like ? ";

        subject = ("%" + subject + "%");
        //获取连接
        Connection connection = JDBCUtil.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //重要的一行
        preparedStatement.setString(1,subject);
        //获取resultSet
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Course> list = new ArrayList<Course>();


        while (resultSet.next()) {

            list.add(new Course(resultSet.getInt("id"),
            resultSet.getString("coursename"),
            resultSet.getString("subject"),
            resultSet.getString("description"),
            resultSet.getInt("teacher_id"),
            resultSet.getInt("section_number"),
            resultSet.getInt("limitnumber"),
            resultSet.getInt("registernumber"),
            resultSet.getString("create_time"),
            resultSet.getString("end_time")));
        }
        if (list.size() == 0) {
            System.out.println("查询失败");
            JDBCUtil.close(connection, preparedStatement, resultSet);
            return null;
        } else {
            JDBCUtil.close(connection, preparedStatement, resultSet);
            return list;
        }

    }


    /**
     * 添加章节数
     * @param course
     */
    @Override
    public void addSection_number(Course course) throws SQLException {
        String sql = "update tb_course set section_number = ? where id = ?";
        CRUDUtils.ZengShanGai(sql,course.getSection_number()+1,course.getId());

    }

    /**
     * 增加报名人数
     * @param course
     * @throws SQLException
     */
    @Override
    public void addRegister_number(Course course) throws SQLException {
        String sql = "update tb_course set registernumber = ? where id = ?";
        CRUDUtils.ZengShanGai(sql,course.getRegisternumber()+1, course.getId());
    }
}
