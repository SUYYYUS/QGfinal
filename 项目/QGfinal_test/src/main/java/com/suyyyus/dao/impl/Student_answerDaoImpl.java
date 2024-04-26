package com.suyyyus.dao.impl;

import com.suyyyus.dao.Student_answerDao;
import com.suyyyus.pojo.Answer;
import com.suyyyus.utils.CRUDUtils;
import com.suyyyus.utils.TimeUtil;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Student_answerDaoImpl implements Student_answerDao {

    /**
     * 记录学生答题记录
     * @param answer
     * @throws SQLException
     */
    @Override
    public void addAnswerRecord(Answer answer) throws SQLException {
        String sql = "insert into tb_student_answer (student_id, question_id, course_id, answer, score, answer_time) values(?,?,?,?,?,?)";
        //执行sql语句
        CRUDUtils.ZengShanGai(sql,
                answer.getStudent_id(),
                answer.getQuestion_id(),
                answer.getCourse_id(),
                answer.getAnswer(),
                answer.getScore(),
                TimeUtil.formatDateTime(LocalDateTime.now()));
    }
}
