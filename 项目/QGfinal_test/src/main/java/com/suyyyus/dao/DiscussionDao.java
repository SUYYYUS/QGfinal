package com.suyyyus.dao;

import com.suyyyus.pojo.Discussion;

import java.sql.SQLException;
import java.util.List;

public interface DiscussionDao {

    //学生添加评论
    public void addDiscussion(Discussion discussion) throws SQLException;

    //教师进行回复
    public void TeacherReply(Discussion discussion) throws SQLException;

    //展示某个课程的评论区
    public List<Discussion> queryAllByCourse_id(int course_id) throws SQLException;

    //展示某个老师的提问区
    public List<Discussion> queryAllByTeacher_id(int teacher_id) throws SQLException;

    //展示某个学生它的提问记录
    public List<Discussion> queryAllByStudent_id(int student_id) throws SQLException;

    //批量删除留言
    public void deleteDiscussions(int[] id);

    //查询总条数
    public int selectAllCount() throws Exception;

    //分页查询
    public List<Discussion> selectByPage(int begin, int size);

    //单个删除留言
    public void deleteDiscussion(int id);

}
