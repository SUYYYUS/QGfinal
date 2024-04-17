package com.suyyyus.dao;

import com.suyyyus.pojo.Student_course;

import java.util.List;

public interface Student_courseDao {

    //通过学生id查询课程
    public List<Student_course> queryByStudent_id(int student_id) throws Exception;

    //通过课程id查询学生
    public List<Student_course> queryStudentByCourse_id(int course_id) throws Exception;
}
