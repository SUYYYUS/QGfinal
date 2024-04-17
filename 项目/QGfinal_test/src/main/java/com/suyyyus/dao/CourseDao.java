package com.suyyyus.dao;

import com.suyyyus.pojo.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDao {

    //添加课程
    public void addCourse(Course course) throws SQLException;

    //通过课程id来寻找课程
    public Course queryByCourse_id(int course_id) throws SQLException;

    //展示所有课程
    public List<Course> queryAllCourseByTeacher_id(int teacher_id) throws Exception;

    //分页查询
    public List<Course> selectByPage(int begin, int size);

    //计算数据总条数
    public int selectAllCount() throws Exception;

    //通过学科内容进行模糊查询
    public List<Course> queryBySubject(String subject) throws Exception;

    //添加章节数
    public void addSection_number(Course course) throws SQLException;

    //增加报名人数
    public void addRegister_number(Course course) throws SQLException;

}
