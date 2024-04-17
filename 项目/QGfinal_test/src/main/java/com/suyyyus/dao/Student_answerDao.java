package com.suyyyus.dao;

import com.suyyyus.pojo.Answer;

import java.sql.SQLException;


public interface Student_answerDao {

    //增加答题记录
    public void addAnswerRecord(Answer answer) throws SQLException;

}
