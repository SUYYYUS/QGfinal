package com.suyyyus.service.impl;

import com.suyyyus.dao.QuestionDao;
import com.suyyyus.dao.impl.QuestionDaoImpl;
import com.suyyyus.pojo.Question;
import com.suyyyus.service.QuestionService;

import java.sql.SQLException;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    QuestionDao questionDao = new QuestionDaoImpl();

    /**
     * 添加题目
     * @param question
     * @throws SQLException
     */
    @Override
    public void addQuestion(Question question) throws SQLException {
        questionDao.addQuestion(question);
    }

    @Override
    public Question queryQuesionById(int id) throws SQLException {
        Question question = questionDao.queryQuestionById(id);
        return question;
    }

    @Override
    public List<Question> queryAllQuestionByTwoid(int course_section_id, int course_id) throws Exception {
        List<Question> questions = questionDao.queryAllQuestionByTwoid(course_section_id, course_id);
        return questions;
    }
}
