package com.suyyyus.service;

import com.suyyyus.pojo.Section;

import java.sql.SQLException;
import java.util.List;

public interface SectionService {

    //添加章节
    public void addSection(Section section) throws SQLException;

    //查询课程下的章节
    public List<Section> queryAllSectionByCourse_id(int course_id) throws Exception;

    //通过id查找章节
    public Section queryById(int id) throws SQLException;

}
