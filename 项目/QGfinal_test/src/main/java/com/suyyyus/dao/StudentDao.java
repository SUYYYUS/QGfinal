package com.suyyyus.dao;

import com.suyyyus.pojo.Student;
import com.suyyyus.pojo.Student_course;

import java.sql.SQLException;

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
}
