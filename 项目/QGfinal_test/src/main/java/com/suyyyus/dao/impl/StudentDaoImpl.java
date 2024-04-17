package com.suyyyus.dao.impl;

import com.suyyyus.dao.StudentDao;
import com.suyyyus.pojo.Student;
import com.suyyyus.pojo.Student_course;
import com.suyyyus.utils.CRUDUtils;
import com.suyyyus.utils.MD5Util;
import com.suyyyus.utils.MyConnectionPool;
import com.suyyyus.utils.TimeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class StudentDaoImpl implements StudentDao {

    static MyConnectionPool myConnectionPool = new MyConnectionPool();

    /**
     * 登录操作
     * @param studentid 学号
     * @param password 密码
     * @return
     * @throws SQLException
     */
    @Override
    public boolean Login(String studentid, String password) throws SQLException {
        String sql = "select * from tb_student where studentid = ?";
        boolean b = CRUDUtils.Login(sql, studentid, password);
        return b;
    }

    /**
     * 添加学生
     * @param student
     * @throws SQLException
     */
    @Override
    public void addStudent(Student student) throws SQLException {
        //sql语句
        String sql = "insert into tb_student (studentname, studentid, password, grade, qq, description, create_time, update_time) " +
                "values (?,?,?,?,?,?,?,?)";
        //对密码进行加密操作
        String saltPassword = MD5Util.generateSaltPassword(student.getPassword());
        //添加信息
        CRUDUtils.ZengShanGai(sql,student.getStudentname(),student.getStudentid(),saltPassword,student.getGrade(),student.getQq(),student.getDescription(),TimeUtil.formatDateTime(LocalDateTime.now()),TimeUtil.formatDateTime(LocalDateTime.now()));

    }

    /**
     * 通过学号查找学生
     * @param studentid
     * @return
     * @throws SQLException
     */
    @Override
    public Student queryByStudentid(String studentid) throws SQLException {
        String sql = "select * from tb_student where studentid = ?";
        //获取连接池的连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentid);
        ResultSet resultSet = preparedStatement.executeQuery();

        Student student = new Student();

        if(resultSet.next()){
            student.setId(resultSet.getInt("id"));
            student.setStudentname(resultSet.getString("studentname"));
            student.setStudentid(resultSet.getString("studentid"));
            student.setPassword(resultSet.getString("password"));
            student.setGrade(resultSet.getString("grade"));
            student.setQq(resultSet.getString("qq"));
            student.setDescription(resultSet.getString("description"));
            student.setCreate_time(resultSet.getString("create_time"));
            student.setUpdate_time(resultSet.getString("update_time"));
        }else {
            student = null;
        }
        return student;
    }

    /**
     * 通过学号查找学生
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Student queryById(int id) throws SQLException {
        String sql = "select * from tb_student where id = ?";
        //获取连接池的连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Student student = new Student();

        if(resultSet.next()){
            student.setId(resultSet.getInt("id"));
            student.setStudentname(resultSet.getString("studentname"));
            student.setStudentid(resultSet.getString("studentid"));
            student.setPassword(resultSet.getString("password"));
            student.setGrade(resultSet.getString("grade"));
            student.setQq(resultSet.getString("qq"));
            student.setDescription(resultSet.getString("description"));
            student.setCreate_time(resultSet.getString("create_time"));
            student.setUpdate_time(resultSet.getString("update_time"));
        }else {
            student = null;
        }
        return student;
    }



    /**
     * 修改学生信息
     * @param student
     * @return
     * @throws SQLException
     */
    @Override
    public int updateStudent(Student student) throws SQLException {
        String sql = "update tb_student set studentname = ?, studentid = ?, grade = ?, qq = ?, description = ?, update_time = ? where id = ?";
        CRUDUtils.ZengShanGai(sql,student.getStudentname(),student.getStudentid(),
                student.getGrade(),student.getQq(),student.getDescription(),
                TimeUtil.formatDateTime(LocalDateTime.now()),student.getId());
        return 0;

    }

    /**
     * 学生报名课程
     * @param student_course
     * @return
     * @throws SQLException
     */
    @Override
    public boolean addStudent_course(Student_course student_course) throws SQLException {
        String sql = "insert into tb_student_course (student_id, course_id, register_time, status) values (?,?,?,?)";

        CRUDUtils.ZengShanGai(sql,student_course.getStudent_id(),student_course.getCourse_id(),TimeUtil.formatDateTime(LocalDateTime.now()),1);

        return true;
    }
}
