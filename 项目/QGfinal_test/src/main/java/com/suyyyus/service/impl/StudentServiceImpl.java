package com.suyyyus.service.impl;

import com.suyyyus.dao.StudentDao;
import com.suyyyus.dao.impl.StudentDaoImpl;
import com.suyyyus.pojo.*;
import com.suyyyus.service.StudentService;

import com.suyyyus.utils.TimeUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

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
        //返回结果
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
        //返回学生对象
        return student;
    }

    /**
     * 修改学生信息
     * @param student
     * @throws SQLException
     */
    @Override
    public void updateStudent(Student student) throws SQLException {
        //执行更新数据信息操作
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
        //返回学生对象
        return  student;
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<Student> selectStudentByPage(int currentPage, int pageSize) throws Exception {
        //开始索引
        int begin = (currentPage - 1) * pageSize;
        //页数
        int size = pageSize;

        //当前页数据
        List<Student> rows = studentDao.selectByPage(begin, size);

        //查询总记录数
        int count = studentDao.selectAllCount();

        //封装pageBean对象
        PageBean<Student> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(count);

        return pageBean;
    }

    /**
     * 批量删除学生
     * @param id
     */
    @Override
    public void deleteStudents(int[] id) {
        studentDao.deleteStudents(id);
    }

    /**
     * 重置学生账号的密码
     * @param student
     * @return
     * @throws SQLException
     */
    @Override
    public boolean resetPassword(Student student) throws SQLException {
        //重置密码
        studentDao.resetPassword(student);
        return true;
    }

    @Override
    public List<Student> queryByGrade(String grade) throws SQLException {
        List<Student> studentList = studentDao.queryByGrade(grade);

        return studentList;
    }

    /**
     * 添加日志
     * @param student_logging
     * @throws SQLException
     */
    @Override
    public void addLogging(Student_logging student_logging) throws SQLException {
        student_logging.setLogging(TimeUtil.formatDateTime(LocalDateTime.now()) + ":" + student_logging.getLogging());
        //添加历史记录
        studentDao.addLogging(student_logging);
    }

    /**
     * 通过学生id查询日志
     * @param student_id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Student_logging> queryLoggingById(int student_id) throws SQLException {
        List<Student_logging> student_loggings = studentDao.queryLoggingById(student_id);
        //返回学生的历史记录集合
        return student_loggings;
    }

    /**
     * 通过姓名查找学生
     * @param studentname
     * @return
     * @throws SQLException
     */
    @Override
    public Student queryByName(String studentname) throws SQLException {
        Student student = studentDao.queryByName(studentname);
        //返回学生对象
        return student;
    }
}
