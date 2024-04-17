package com.suyyyus.service;

import com.suyyyus.pojo.Answer;
import com.suyyyus.pojo.Student_study;

import java.sql.SQLException;

public interface Student_studyService {

    //添加学习记录
    public void addStudent_studyRecord(Answer answer,Student_study student_study) throws SQLException;

    //通过学生id和课程id查询有无该学生学习记录
    public Student_study queryStudentRecordByIds(int student_id , int course_id) throws SQLException;

    //更新数据
    public boolean updateStudentRecord (Answer answer, Student_study student_study) throws SQLException;

    //查询学习人数
    public int queryStudyNumber(int course_id) throws SQLException;

    //查询学习人数的总平均分
    public double querytotalaverage_score(int course_id) throws SQLException;

    //平均正确率
    public double querytotalaccuracy(int course_id) throws SQLException;


}
