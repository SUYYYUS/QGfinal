package com.suyyyus.dao.impl;

import com.suyyyus.dao.Student_courseDao;
import com.suyyyus.pojo.Student_course;
import com.suyyyus.utils.CRUDUtils;

import java.util.List;

public class Student_courseDaoImpl implements Student_courseDao {

    /**
     * 查询学生所报名的课程
     * @param student_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Student_course> queryByStudent_id(int student_id) throws Exception {
        String sql = "select * from tb_student_course where student_id = ?";
        //执行sql语句
        List<Student_course> student_courses = CRUDUtils.queryAllCourseByStudent_id(sql, student_id);
        //返回集合
        return student_courses;
    }

    /**
     * 通过课程id查询报名的学生
     * @param course_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Student_course> queryStudentByCourse_id(int course_id) throws Exception {
        String sql = "select * from tb_student_course where course_id = ?";
        //执行sql语句
        List<Student_course> student_courses = CRUDUtils.queryAllStudentByCourse_id(sql, course_id);
        //返回集合
        return  student_courses;
    }
}
