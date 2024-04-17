package com.suyyyus.service.impl;

import com.suyyyus.dao.SectionDao;
import com.suyyyus.dao.impl.SectionDaoImpl;
import com.suyyyus.pojo.Section;
import com.suyyyus.service.SectionService;

import java.sql.SQLException;
import java.util.List;

public class SectionServiceImpl implements SectionService {

    SectionDao sectionDao = new SectionDaoImpl();

    /**
     * 添加章节
     * @param section
     * @throws SQLException
     */
    @Override
    public void addSection(Section section) throws SQLException {
        sectionDao.addSection(section);
    }


    /**
     * 查询课程下的章节
     * @param course_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Section> queryAllSectionByCourse_id(int course_id) throws Exception {
        List<Section> sections = sectionDao.queryAllSectionByCourse_id(course_id);
        return sections;
    }

    /**
     * id
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Section queryById(int id) throws SQLException {
        Section section = sectionDao.queryById(id);

        return section;
    }
}
