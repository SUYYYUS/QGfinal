package com.suyyyus.service;

import com.suyyyus.pojo.Course;
import com.suyyyus.pojo.Student;
import com.suyyyus.pojo.Student_course;
import com.suyyyus.pojo.Teacher;

import java.sql.SQLException;

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
}
