package com.suyyyus.service.impl;

import com.suyyyus.dao.TeacherDao;
import com.suyyyus.dao.impl.TeacherDaoImpl;
import com.suyyyus.pojo.Teacher;
import com.suyyyus.service.TeacherService;

import java.sql.SQLException;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    TeacherDao teacherDao = new TeacherDaoImpl();

    /**
     * 教师登录
     * @param teacherid
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public boolean Login(String teacherid, String password) throws SQLException {
        boolean login = teacherDao.Login(teacherid, password);
        return login;
    }

    /**
     * 添加教师
     * @param teacher
     * @throws SQLException
     */
    @Override
    public void addTeacher(Teacher teacher) throws SQLException {
        teacherDao.addTeacher(teacher);
    }


    /**
     * 通过教职工号查找老师
     * @param teacherid
     * @return
     * @throws SQLException
     */
    @Override
    public Teacher queryByTeacherid(String teacherid) throws SQLException {
        Teacher teacher = teacherDao.queryByTeacherid(teacherid);
        return teacher;
    }


    /**
     * 查询所有教师信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Teacher> queryAllTeacher() throws Exception {
        List<Teacher> teachers = teacherDao.queryAllTeacher();
        return teachers;
    }

    /**
     * 修改教师信息
     * @param teacher
     * @return
     * @throws SQLException
     */
    @Override
    public int updateInfo(Teacher teacher) throws SQLException {
        int i = teacherDao.updateInfo(teacher);
        return i;
    }
}
