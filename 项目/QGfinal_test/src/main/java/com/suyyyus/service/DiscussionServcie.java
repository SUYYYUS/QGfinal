package com.suyyyus.service;

import com.suyyyus.pojo.Discussion;
import com.suyyyus.pojo.PageBean;

import java.sql.SQLException;
import java.util.List;

public interface DiscussionServcie {

    //添加学生提问
    public void addDiscussion(Discussion discussion) throws SQLException;

    //教师进行回复
    public void TeacherReply(Discussion discussion) throws SQLException;

    //展示某个课程的评论区
    public List<Discussion> queryAllByCourse_id(int course_id) throws SQLException;

    //展示某个老师的提问区
    public List<Discussion> queryAllByTeacher_id(int teacher_id) throws SQLException;

    //展示某个学生它的提问记录
    public List<Discussion> queryAllByStudent_id(int student_id) throws SQLException;

    //分页查询
    public PageBean<Discussion> selectDiscussionByPage(int currentPage, int pageSize) throws Exception;

    //批量删除课程
    public void deleteDiscussions(int[] id);

    //单个删除
    public void deleteDiscussion(int id);
}
