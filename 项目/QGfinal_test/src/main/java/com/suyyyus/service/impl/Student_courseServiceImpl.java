package com.suyyyus.service.impl;

import com.suyyyus.dao.Student_courseDao;
import com.suyyyus.dao.impl.Student_courseDaoImpl;
import com.suyyyus.pojo.Student_course;
import com.suyyyus.service.Student_courseService;

import java.util.List;

public class Student_courseServiceImpl implements Student_courseService {

    Student_courseDao student_courseDao = new Student_courseDaoImpl();

    /**
     * 查询学生报名的课程
     * @param student_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Student_course> queryCourseByStudent_id(int student_id) throws Exception {
        List<Student_course> student_courses = student_courseDao.queryByStudent_id(student_id);
        //返回集合
        return student_courses;
    }

    @Override
    public List<Student_course> queryStudentByCourse_id(int course_id) throws Exception {
        List<Student_course> student_courses = student_courseDao.queryStudentByCourse_id(course_id);
        //返回集合
        return student_courses;
    }
}
