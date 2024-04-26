package com.suyyyus.dao;

import com.suyyyus.pojo.Student_study;

import java.sql.SQLException;
import java.util.List;

public interface Student_studyDao {

    //第一次学习要添加
    public void addStudent_studyRecord(Student_study student_study) throws SQLException;

    //通过学生id和课程id查询有无该学生学习记录
    public Student_study queryStudentRecordByIds(int student_id , int course_id) throws SQLException;

    //更新数据
    public boolean updateStudentRecord (Student_study student_study) throws SQLException;

    //通过课程id查询学生的学生情况
    public List<Student_study> queryStudentRecordByCourse_id(int course_id) throws SQLException;

}
