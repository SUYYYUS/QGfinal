package com.suyyyus.service.impl;

import com.suyyyus.dao.TeacherDao;
import com.suyyyus.dao.impl.TeacherDaoImpl;
import com.suyyyus.pojo.PageBean;
import com.suyyyus.pojo.Student;
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


    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<Teacher> selectTeacherByPage(int currentPage, int pageSize) throws Exception {
        //开始索引
        int begin = (currentPage - 1) * pageSize;
        //页数
        int size = pageSize;

        //当前页数据
        List<Teacher> rows = teacherDao.selectByPage(begin, size);

        //查询总记录数
        int count = teacherDao.selectAllCount();

        //封装pageBean对象
        PageBean<Teacher> pageBean = new PageBean<>();
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
        teacherDao.deleteTeachers(id);
    }


    /**
     * 通过学院查找学生
     * @param college
     * @return
     * @throws SQLException
     */
    @Override
    public List<Teacher> queryByCollege(String college) throws SQLException {
        List<Teacher> teacherList = teacherDao.queryByCollege(college);

        return teacherList;
    }
}
