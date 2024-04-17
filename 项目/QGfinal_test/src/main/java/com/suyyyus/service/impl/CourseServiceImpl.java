package com.suyyyus.service.impl;

import com.suyyyus.dao.CourseDao;
import com.suyyyus.dao.impl.CourseDaoImpl;
import com.suyyyus.pojo.Course;
import com.suyyyus.pojo.PageBean;
import com.suyyyus.service.CourseService;

import java.sql.SQLException;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    CourseDao courseDao = new CourseDaoImpl();

    /**
     * 添加课程
     * @param course
     * @throws SQLException
     */
    @Override
    public void addCourse(Course course) throws SQLException {
        courseDao.addCourse(course);
    }


    /**
     * 通过教师id查询其开设的课程
     * @param teacher_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Course> queryAllCourseByTeacher_id(int teacher_id) throws Exception {
        List<Course> courseList = courseDao.queryAllCourseByTeacher_id(teacher_id);

        return courseList;
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<Course> selectByPage(int currentPage, int pageSize) throws Exception {
        //开始索引
        int begin = (currentPage - 1) * pageSize;
        //页数
        int size = pageSize;

        //当前页数据
        List<Course> rows = courseDao.selectByPage(begin, size);

        //查询总记录数
        int count = courseDao.selectAllCount();

        //封装pageBean对象
        PageBean<Course> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(count);

        return pageBean;
    }

    /**
     * 通过id查找课程
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Course queryByCourse_id(int id) throws SQLException {
        Course course = courseDao.queryByCourse_id(id);

        return course;
    }

    /**
     * 通过学科分类查询课程
     * @param subject
     * @return
     * @throws Exception
     */
    @Override
    public List<Course> queryBySubject(String subject) throws Exception {
        List<Course> courseList = courseDao.queryBySubject(subject);

        return courseList;

    }

    /**
     * 添加课程的章节数
     * @param course
     * @throws SQLException
     */
    @Override
    public void addSection_number(Course course) throws SQLException {
        courseDao.addSection_number(course);
    }


    /**
     * 增加报名人数
     * @param course
     * @throws SQLException
     */
    @Override
    public void addRegisternumber(Course course) throws SQLException {
        courseDao.addRegister_number(course);
    }
}
