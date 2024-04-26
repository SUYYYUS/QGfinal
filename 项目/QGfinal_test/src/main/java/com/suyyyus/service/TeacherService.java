package com.suyyyus.service;

import com.suyyyus.pojo.PageBean;
import com.suyyyus.pojo.Student_logging;
import com.suyyyus.pojo.Teacher;
import com.suyyyus.pojo.Teacher_logging;

import java.sql.SQLException;
import java.util.List;

public interface TeacherService {

    //教师登录
    public boolean Login(String teacherid, String password) throws SQLException;

    //添加教师
    public void addTeacher(Teacher teacher) throws SQLException;

    //通过教职工号查找教师
    public Teacher queryByTeacherid(String teacherid) throws SQLException;

    //通过id查找教师
    public Teacher queryByid(int id) throws SQLException;

    //查询所有教师信息
    public List<Teacher> queryAllTeacher() throws Exception;

    //修改教师信息
    public int updateInfo(Teacher teacher) throws SQLException;

    //分页查询
    public PageBean<Teacher> selectTeacherByPage(int currentPage, int pageSize) throws Exception;

    //批量删除学生
    public void deleteTeachers(int[] id);

    //通过学院查询老师
    public List<Teacher> queryByCollege(String college) throws SQLException;

    //添加教师日志
    public void addLogging(Teacher_logging teacher_logging) throws SQLException;

    //通过id查询教师日志
    public List<Teacher_logging> queryLoggingById(int teacher_id) throws SQLException;
}
