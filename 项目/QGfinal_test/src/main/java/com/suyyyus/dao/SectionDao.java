package com.suyyyus.dao;

import com.suyyyus.pojo.Course;
import com.suyyyus.pojo.Section;

import java.sql.SQLException;
import java.util.List;

public interface SectionDao {

    //添加章节
    public void addSection(Section section) throws SQLException;

    //查询某个课程的id
    public List<Section> queryAllSectionByCourse_id(int course_id) throws Exception;

    //通过章节id查找章节
    public Section queryById(int id) throws SQLException;

}
