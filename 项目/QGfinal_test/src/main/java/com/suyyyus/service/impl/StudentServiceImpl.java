package com.suyyyus.service.impl;

import com.suyyyus.dao.StudentDao;
import com.suyyyus.dao.impl.StudentDaoImpl;
import com.suyyyus.pojo.Course;
import com.suyyyus.pojo.Student;
import com.suyyyus.pojo.Student_course;
import com.suyyyus.service.StudentService;
import com.suyyyus.utils.TCP.Client;
import com.suyyyus.utils.TimeUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class StudentServiceImpl implements StudentService {

    StudentDao studentDao = new StudentDaoImpl();

    /**
     * 学生登录
     * @param studentid
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public boolean StudentLogin(String studentid, String password) throws SQLException {
        boolean login = studentDao.Login(studentid, password);
        return login;
    }

    /**
     * 添加学生
     * @param student
     * @throws SQLException
     */
    @Override
    public void addStudent(Student student) throws SQLException {
        studentDao.addStudent(student);
    }

    /**
     * 通过学号查找学生
     * @param studentid
     * @return
     */
    @Override
    public Student queryByStudentid(String studentid) throws SQLException {
        Student student = studentDao.queryByStudentid(studentid);
        return student;
    }

    /**
     * 修改学生信息
     * @param student
     * @throws SQLException
     */
    @Override
    public void updateStudent(Student student) throws SQLException {
        studentDao.updateStudent(student);
    }


    /**
     * 报名课程
     * @param course
     * @param student_course
     * @return
     * @throws SQLException
     */
    @Override
    public int addStudent_course(Course course, Student_course student_course) throws SQLException {


        //判断人数
        if(course.getRegisternumber() >= course.getLimitnumber()){
            return 1;
        }
        //判断是否已过报名时间
        else if(LocalDateTime.now().isAfter(TimeUtil.parseDateTime(course.getEnd_time()))){
            return 2;
        }else {
            boolean b = studentDao.addStudent_course(student_course);
            return 3;
        }
    }

    /**
     * 通过id查找学生
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Student queryById(int id) throws SQLException {
        Student student = studentDao.queryById(id);
        return  student;
    }


    @Override
    public void sendMsg(String msg) throws IOException {
        Client.sendMsg(msg);
    }
}
