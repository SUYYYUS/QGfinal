package com.suyyyus.service.impl;

import com.suyyyus.dao.Student_studyDao;
import com.suyyyus.dao.impl.Student_studyDaoImpl;
import com.suyyyus.pojo.Answer;
import com.suyyyus.pojo.Student_study;
import com.suyyyus.service.Student_studyService;

import java.sql.SQLException;
import java.util.List;

public class Student_studyServiceImpl implements Student_studyService {

    Student_studyDao student_studyDao = new Student_studyDaoImpl();

    /**
     * 添加学习记录
     * @param student_study
     */
    @Override
    public void addStudent_studyRecord(Answer answer,Student_study student_study) throws SQLException {
        //更新数据
        student_study.setAnswernumber(student_study.getAnswernumber() + 1);
        student_study.setTotal_score(student_study.getTotal_score() + answer.getScore());
        student_study.setAverage_score(student_study.getTotal_score() / student_study.getAnswernumber());
        if(answer.getScore() != 0){
            student_study.setRightnumber(student_study.getRightnumber() + 1);
        }
        double acc = (double) student_study.getRightnumber() /  (double) student_study.getAnswernumber();
//        System.out.println(acc + "正确率");
        student_study.setAccuracy(acc);

        student_studyDao.addStudent_studyRecord(student_study);
    }

    /**
     * 查询学习记录
     * @param student_id
     * @param course_id
     * @return
     * @throws SQLException
     */
    @Override
    public Student_study queryStudentRecordByIds(int student_id, int course_id) throws SQLException {
        Student_study student_study = student_studyDao.queryStudentRecordByIds(student_id, course_id);
        return student_study;
    }


    /**
     * 更新学习记录
     * @param answer
     * @param student_study
     * @return
     * @throws SQLException
     */
    @Override
    public boolean updateStudentRecord(Answer answer, Student_study student_study) throws SQLException {
        //更新数据
        student_study.setAnswernumber(student_study.getAnswernumber() + 1);
        student_study.setTotal_score(student_study.getTotal_score() + answer.getScore());
        student_study.setAverage_score(student_study.getTotal_score() / student_study.getAnswernumber());
        if(answer.getScore() != 0){
            student_study.setRightnumber(student_study.getRightnumber() + 1);
        }
        double acc = (double) student_study.getRightnumber() /  (double) student_study.getAnswernumber();
//        System.out.println(acc + "正确率");
        student_study.setAccuracy(acc);
        student_studyDao.updateStudentRecord(student_study);

        return true;
    }

    /**
     * 查询学习的人数
     * @param course_id
     * @return
     */
    @Override
    public int queryStudyNumber(int course_id) throws SQLException {
        List<Student_study> student_studies = student_studyDao.queryStudentRecordByCourse_id(course_id);

        int number = student_studies.size();

        return number;
    }

    /**
     * 课程总平均分
     * @param course_id
     * @return
     * @throws SQLException
     */
    @Override
    public double querytotalaverage_score(int course_id) throws SQLException {
        List<Student_study> student_studies = student_studyDao.queryStudentRecordByCourse_id(course_id);

        double number = student_studies.size();
        double scores = 0;

        for (Student_study student_study : student_studies) {
            scores += student_study.getAverage_score();
        }

        double s = scores / number;

        System.out.println(s);

        return s;
    }

    /**
     * 查询总平均正确率
     * @param course_id
     * @return
     * @throws SQLException
     */
    @Override
    public double querytotalaccuracy(int course_id) throws SQLException {
        List<Student_study> student_studies = student_studyDao.queryStudentRecordByCourse_id(course_id);

        double number = student_studies.size();
        double scores = 0;

        for (Student_study student_study : student_studies) {
            scores += student_study.getAccuracy();
        }

        double s = scores / number;

        System.out.println(s);

        return s;
    }
}
