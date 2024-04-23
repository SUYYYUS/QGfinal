package com.suyyyus.service;

import com.suyyyus.pojo.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    //学生登录
    public boolean StudentLogin(String studentid, String password) throws SQLException;

    //添加学生
    public void addStudent(Student student) throws SQLException;

    //通过学号查找学生
    public Student queryByStudentid(String studentid) throws SQLException;

    //通过id查找学生
    public Student queryById(int id) throws SQLException;

    //修改学生信息
    public void updateStudent(Student student) throws SQLException;

    //学生报名课程
    public int addStudent_course(Course course, Student_course student_course) throws SQLException;

    //发送信息
    public void sendMsg(String msg) throws IOException;

    //所有学生分页查询
    public PageBean<Student> selectStudentByPage(int currentPage, int pageSize) throws Exception;

    //批量删除学生
    public void deleteStudents(int[] id);

    //重置学生账号密码
    public boolean resetPassword(Student student) throws SQLException;

    //通过年级查找学生
    public List<Student> queryByGrade(String grade) throws SQLException;

    //添加学生日志
    public void addLogging(Student_logging student_logging) throws SQLException;

    //通过id查询学生日志
    public List<Student_logging> queryLoggingById(int student_id) throws SQLException;


}
