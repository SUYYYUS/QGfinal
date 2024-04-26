package com.suyyyus.dao;

import com.suyyyus.pojo.Course;
import com.suyyyus.pojo.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDao {

    //添加题目
    public void addQuestion(Question question) throws SQLException;

    //通过id查找题目
    public Question queryQuestionById(int id) throws SQLException;

    //查询课程章节中的所有问题
    public List<Question> queryAllQuestionByTwoid(int course_section_id, int course_id) throws Exception;
}
