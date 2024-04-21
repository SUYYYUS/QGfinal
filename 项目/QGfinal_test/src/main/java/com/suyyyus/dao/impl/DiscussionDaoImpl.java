package com.suyyyus.dao.impl;

import com.suyyyus.dao.DiscussionDao;
import com.suyyyus.pojo.Discussion;
import com.suyyyus.utils.CRUDUtils;
import com.suyyyus.utils.JDBCUtil;
import com.suyyyus.utils.MyConnectionPool;
import com.suyyyus.utils.TimeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscussionDaoImpl implements DiscussionDao {

    static MyConnectionPool myConnectionPool = new MyConnectionPool();

    /**
     * 添加讨论
     * @param discussion
     * @throws SQLException
     */
    @Override
    public void addDiscussion(Discussion discussion) throws SQLException {
        String sql = "insert into tb_discussion (student_id, teacher_id, course_id, question, question_time) values (?,?,?,?,?)";

        CRUDUtils.ZengShanGai(sql,discussion.getStudent_id(), discussion.getTeacher_id(), discussion.getCourse_id(),discussion.getQuestion(), TimeUtil.formatDateTime(LocalDateTime.now()));
    }

    /**
     * 教师回复
     * @param discussion
     * @throws SQLException
     */
    @Override
    public void TeacherReply(Discussion discussion) throws SQLException {
        String sql = "update tb_discussion set reply = ? ,reply_time = ? where id = ?";

        CRUDUtils.ZengShanGai(sql,discussion.getReply(),TimeUtil.formatDateTime(LocalDateTime.now()) ,discussion.getId());
    }

    /**
     * 通过课程id寻找它的讨论区
     * @param course_id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Discussion> queryAllByCourse_id(int course_id) throws SQLException {
        String sql = "select * from tb_discussion where course_id = ?";

        Connection connection = myConnectionPool.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,course_id);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Discussion> discussionList = new ArrayList<>();

        while(resultSet.next()){
            discussionList.add(new Discussion(resultSet.getInt("id"), resultSet.getInt("student_id"),
                    resultSet.getInt("teacher_id"),resultSet.getInt("course_id"),
                    resultSet.getString("question"),resultSet.getString("reply"),
                    resultSet.getString("question_time"),resultSet.getString("reply_time")));
        }

        //释放资源
        JDBCUtil.close(connection,preparedStatement,resultSet);
        return discussionList;

    }

    /**
     * 通过教师id查找属于他的提问回复
     * @param teacher_id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Discussion> queryAllByTeacher_id(int teacher_id) throws SQLException {
        String sql = "select * from tb_discussion where teacher_id = ?";

        Connection connection = myConnectionPool.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,teacher_id);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Discussion> discussionList = new ArrayList<>();

        while(resultSet.next()){
            discussionList.add(new Discussion(resultSet.getInt("id"), resultSet.getInt("student_id"),
                    resultSet.getInt("teacher_id"),resultSet.getInt("course_id"),
                    resultSet.getString("question"),resultSet.getString("reply"),
                    resultSet.getString("question_time"),resultSet.getString("reply_time")));
        }

        //释放资源
        JDBCUtil.close(connection,preparedStatement,resultSet);
        return discussionList;
    }

    /**
     * 通过学生id查找他的提问记录
     * @param student_id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Discussion> queryAllByStudent_id(int student_id) throws SQLException {
        String sql = "select * from tb_discussion where student_id = ?";

        Connection connection = myConnectionPool.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,student_id);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Discussion> discussionList = new ArrayList<>();

        while(resultSet.next()){
            discussionList.add(new Discussion(resultSet.getInt("id"), resultSet.getInt("student_id"),
                    resultSet.getInt("teacher_id"),resultSet.getInt("course_id"),
                    resultSet.getString("question"),resultSet.getString("reply"),
                    resultSet.getString("question_time"),resultSet.getString("reply_time")));
        }

        //释放资源
        JDBCUtil.close(connection,preparedStatement,resultSet);
        return discussionList;
    }



}
