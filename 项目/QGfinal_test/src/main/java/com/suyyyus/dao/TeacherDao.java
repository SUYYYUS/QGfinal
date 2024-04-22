package com.suyyyus.dao;

import com.suyyyus.pojo.Student;
import com.suyyyus.pojo.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDao {

    //登录操作
    public boolean Login(String teacherid, String password) throws SQLException;

    //添加老师
    public void addTeacher(Teacher teacher) throws SQLException;

    //通过教职工号查找老师
    public Teacher queryByTeacherid(String teacherid) throws SQLException;

    //查询所有教师信息
    public List<Teacher> queryAllTeacher() throws Exception;

    //修改教师信息
    public int updateInfo(Teacher teacher) throws SQLException;

    public List<Teacher> selectByPage(int begin, int size);

    public int selectAllCount() throws Exception;

    public void deleteTeachers(int[] id);

    //通过学院查询老师
    public List<Teacher> queryByCollege(String college) throws SQLException;


}
