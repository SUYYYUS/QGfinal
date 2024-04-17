package com.suyyyus.dao.impl;

import com.suyyyus.dao.QuestionDao;
import com.suyyyus.pojo.Course;
import com.suyyyus.pojo.Question;
import com.suyyyus.utils.CRUDUtils;
import com.suyyyus.utils.MyConnectionPool;
import com.suyyyus.utils.TimeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    static MyConnectionPool myConnectionPool = new MyConnectionPool();
    /**
     * 添加题目
     * @param question
     */
    @Override
    public void addQuestion(Question question) throws SQLException {
        String sql = "insert into tb_question (type, content, answer, score, course_section_id, course_id, create_time) values (?,?,?,?,?,?,?)";

        CRUDUtils.ZengShanGai(sql,question.getType(),question.getContent(),question.getAnswer(),
                question.getScore(),question.getCourse_section_id(),
                question.getCourse_id(), TimeUtil.formatDateTime(LocalDateTime.now()));

    }

    /**
     * 通过题目ID来寻找该题目
     * @param id
     * @return
     */
    @Override
    public Question queryQuestionById(int id) throws SQLException {
        String sql = "select * from tb_question where id = ?";
        //获取连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        //执行
        ResultSet resultSet = preparedStatement.executeQuery();

        Question question = new Question();

        if(resultSet.next()){
            question.setId(resultSet.getInt("id"));
            question.setType(resultSet.getString("type"));
            question.setContent(resultSet.getString("content"));
            question.setAnswer(resultSet.getString("answer"));
            question.setScore(resultSet.getInt("score"));
            question.setCourse_section_id(resultSet.getInt("course_section_id"));
            question.setCourse_id(resultSet.getInt("course_id"));
            question.setCreate_time(resultSet.getString("create_time"));

        } else {
            question = null;
        }
        return question;
    }

    /**
     *通过课程和章节的id查找题目
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public List<Question> queryAllQuestionByTwoid(int course_section_id, int course_id) throws Exception {
        String sql = "select * from tb_question where course_section_id = ? and course_id = ?";

        List<Question> questions = CRUDUtils.queryAllQuestionByCidAndSid(sql, course_section_id, course_id);

        return questions;
    }

}
