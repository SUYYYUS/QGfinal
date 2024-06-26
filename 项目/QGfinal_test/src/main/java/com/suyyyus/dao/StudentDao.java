package com.suyyyus.dao;

import com.suyyyus.pojo.Student;
import com.suyyyus.pojo.Student_course;
import com.suyyyus.pojo.Student_logging;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {

    //登录操作
    public boolean Login(String studentid, String password) throws SQLException;

    //添加学生
    public void addStudent(Student student) throws SQLException;

    //通过学号查询学生
    public Student queryByStudentid(String studentid) throws SQLException;

    //通过id查找学生
    public Student queryById(int id) throws SQLException;

    //修改学生信息
    public int updateStudent(Student student) throws SQLException;

    //学生报名课程
    public boolean addStudent_course(Student_course student_course) throws SQLException;

    //分页查询学生
    public List<Student> selectByPage(int begin, int size);

    //查询学生数量
    public int selectAllCount() throws Exception;

    //批量删除学生操作
    public void deleteStudents(int[] id);

    //重置密码
    public void resetPassword(Student student) throws SQLException;

    //通过年级查找学生
    public List<Student> queryByGrade(String grade) throws SQLException;

    //添加学生日志
    public void addLogging(Student_logging student_logging) throws SQLException;

    //通过学生id查询日志情况
    public List<Student_logging> queryLoggingById(int student_id) throws SQLException;

    //通过姓名查找学生
    public Student queryByName(String studentname) throws SQLException;
}
