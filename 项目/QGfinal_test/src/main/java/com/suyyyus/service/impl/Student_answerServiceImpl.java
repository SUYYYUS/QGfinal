package com.suyyyus.service.impl;

import com.suyyyus.dao.Student_answerDao;
import com.suyyyus.dao.impl.Student_answerDaoImpl;
import com.suyyyus.pojo.Answer;
import com.suyyyus.service.Student_answerService;

import java.sql.SQLException;

public class Student_answerServiceImpl implements Student_answerService {

    Student_answerDao student_answerDao = new Student_answerDaoImpl();

    /**
     * 添加学生答题记录
     * @param answer
     * @throws SQLException
     */
    @Override
    public void addAnswerRecord(Answer answer) throws SQLException {
        student_answerDao.addAnswerRecord(answer);
    }
}
