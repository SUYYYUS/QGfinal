package com.suyyyus.service;

import com.suyyyus.pojo.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionService {

    //添加题目
    public void addQuestion(Question question) throws SQLException;

    //通过id查找题目
    public Question queryQuesionById(int id) throws SQLException;

    //查询章节下的问题
    public List<Question> queryAllQuestionByTwoid(int course_section_id, int course_id) throws Exception;

}
