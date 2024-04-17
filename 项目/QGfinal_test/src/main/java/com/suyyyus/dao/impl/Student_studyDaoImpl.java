package com.suyyyus.dao.impl;

import com.suyyyus.dao.Student_studyDao;
import com.suyyyus.pojo.Student_study;
import com.suyyyus.utils.CRUDUtils;
import com.suyyyus.utils.MyConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student_studyDaoImpl implements Student_studyDao {

    static MyConnectionPool myConnectionPool = new MyConnectionPool();

    /**
     * 添加学生第一次学习记录
     * @param student_study
     * @throws SQLException
     */
    @Override
    public void addStudent_studyRecord(Student_study student_study) throws SQLException {
        String sql = "insert into tb_student_study (student_id, course_id, answernumber, average_score, total_score, accuracy, rightnumber) values (?,?,?,?,?,?,?)";

        CRUDUtils.ZengShanGai(sql,student_study.getStudent_id(),student_study.getCourse_id(),student_study.getAnswernumber(),student_study.getAverage_score(),student_study.getTotal_score(),student_study.getAccuracy(),student_study.getRightnumber());

    }

    /**
     * 通过学生id和课程id查询该学生学习记录
     * @param student_id
     * @param course_id
     * @return
     * @throws SQLException
     */
    @Override
    public Student_study queryStudentRecordByIds(int student_id, int course_id) throws SQLException {
        String sql = "select * from tb_student_study where student_id = ? and course_id = ?";

        //获取连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, student_id);
        preparedStatement.setInt(2,course_id);

        //执行
        ResultSet resultSet = preparedStatement.executeQuery();

        Student_study student_study = new Student_study();

        if(resultSet.next()){
            student_study.setId(resultSet.getInt("id"));
            student_study.setStudent_id(resultSet.getInt("student_id"));
            student_study.setCourse_id(resultSet.getInt("course_id"));
            student_study.setAnswernumber(resultSet.getInt("answernumber"));
            student_study.setAverage_score(resultSet.getDouble("average_score"));
            student_study.setTotal_score(resultSet.getDouble("total_score"));
            student_study.setAccuracy(resultSet.getDouble("accuracy"));
            student_study.setRightnumber(resultSet.getInt("rightnumber"));
        }else {
            student_study = null;
        }
        return student_study;
    }

    /**
     * 每次学习答题，更新学生的学习记录
     * @param student_study
     * @return
     */
    @Override
    public boolean updateStudentRecord(Student_study student_study) throws SQLException {
        String sql = "update tb_student_study set answernumber = ?, average_score = ?, total_score = ? , accuracy = ? , rightnumber = ? where id = ?";

        CRUDUtils.ZengShanGai(sql,student_study.getAnswernumber(),student_study.getAverage_score(),student_study.getTotal_score(),student_study.getAccuracy(), student_study.getRightnumber(), student_study.getId());

        return true;
    }

    //通过课程id查找学习记录
    @Override
    public List<Student_study> queryStudentRecordByCourse_id(int course_id) throws SQLException {
        String sql = "select * from tb_student_study where course_id = ?";

        //获取连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,course_id);

        //执行
        ResultSet resultSet = preparedStatement.executeQuery();

//        Student_study student_study = new Student_study();

        List<Student_study> student_studies = new ArrayList<>();

        while(resultSet.next()){
            student_studies.add(new Student_study(resultSet.getInt("id"),resultSet.getInt("student_id"),
                    resultSet.getInt("course_id"),resultSet.getInt("answernumber"),
                    resultSet.getDouble("average_score"),resultSet.getDouble("total_score"),
                    resultSet.getDouble("accuracy"),resultSet.getInt("rightnumber")));

        }

        return student_studies;
    }
}
