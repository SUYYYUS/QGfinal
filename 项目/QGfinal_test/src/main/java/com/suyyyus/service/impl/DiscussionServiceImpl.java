package com.suyyyus.service.impl;

import com.suyyyus.dao.DiscussionDao;
import com.suyyyus.dao.impl.DiscussionDaoImpl;
import com.suyyyus.pojo.Discussion;
import com.suyyyus.pojo.PageBean;
import com.suyyyus.pojo.Student;
import com.suyyyus.service.DiscussionServcie;

import java.sql.SQLException;
import java.util.List;

public class DiscussionServiceImpl implements DiscussionServcie {

    DiscussionDao discussionDao = new DiscussionDaoImpl();

    /**
     * 添加学生提问
     * @param discussion
     */
    @Override
    public void addDiscussion(Discussion discussion) throws SQLException {
        //调用方法
        discussionDao.addDiscussion(discussion);
    }

    /**
     * 教师进行回复
     * @param discussion
     */
    @Override
    public void TeacherReply(Discussion discussion) throws SQLException {

        discussionDao.TeacherReply(discussion);

    }

    /**
     * 通过课程id查找属于他的讨论区
     * @param course_id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Discussion> queryAllByCourse_id(int course_id) throws SQLException {
        List<Discussion> discussionList = discussionDao.queryAllByCourse_id(course_id);

        return discussionList;
    }

    /**
     * 通过教师id查找关于它的一些提问回复
     * @param teacher_id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Discussion> queryAllByTeacher_id(int teacher_id) throws SQLException {
        List<Discussion> discussionList = discussionDao.queryAllByTeacher_id(teacher_id);

        return discussionList;
    }

    /**
     * 通过学生id查找一些它的提问回复
     * @param student_id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Discussion> queryAllByStudent_id(int student_id) throws SQLException {
        List<Discussion> discussionList = discussionDao.queryAllByStudent_id(student_id);

        return discussionList;
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<Discussion> selectDiscussionByPage(int currentPage, int pageSize) throws Exception {
        //开始索引
        int begin = (currentPage - 1) * pageSize;
        //页数
        int size = pageSize;

        //当前页数据
        List<Discussion> rows = discussionDao.selectByPage(begin, size);

        //查询总记录数
        int count = discussionDao.selectAllCount();

        //封装pageBean对象
        PageBean<Discussion> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(count);

        return pageBean;
    }

    /**
     * 批量删除留言
     * @param id
     */
    @Override
    public void deleteDiscussions(int[] id) {
        discussionDao.deleteDiscussions(id);
    }


    /**
     * 单个删除留言
     * @param id
     */
    @Override
    public void deleteDiscussion(int id) {
        discussionDao.deleteDiscussion(id);
    }
}
