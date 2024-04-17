package com.suyyyus.service;

import com.suyyyus.pojo.Answer;

import java.sql.SQLException;

public interface Student_answerService {

    //记录学生答题
    public void addAnswerRecord(Answer answer) throws SQLException;

}
