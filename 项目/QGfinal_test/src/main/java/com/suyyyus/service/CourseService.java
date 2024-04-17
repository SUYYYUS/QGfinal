package com.suyyyus.service;

import com.suyyyus.pojo.Course;
import com.suyyyus.pojo.PageBean;

import java.sql.SQLException;
import java.util.List;

public interface CourseService {

    //添加课程
    public void addCourse(Course course) throws SQLException;

    //通过教师的id来查询教师的课程
    public List<Course> queryAllCourseByTeacher_id(int teacher_id) throws Exception;

    //分页查询
    PageBean<Course> selectByPage(int begin, int size) throws Exception;

    //通过课程id查询课程
    public Course queryByCourse_id(int id) throws SQLException;

    //通过学科分类查询课程
    public List<Course> queryBySubject(String subject) throws Exception;

    //添加章节数
    public void addSection_number(Course course) throws SQLException;

    //添加报名人数
    public void addRegisternumber(Course course) throws SQLException;

}
