package com.suyyyus.service;

import com.suyyyus.pojo.Student_course;

import java.util.List;

public interface Student_courseService {

    //查询学生报名的课程
    public List<Student_course> queryCourseByStudent_id(int student_id) throws Exception;

    //查询报名该课程的学生
    public List<Student_course> queryStudentByCourse_id(int course_id) throws Exception;

}
