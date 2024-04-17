package com.suyyyus.dao.impl;

import com.suyyyus.dao.SectionDao;
import com.suyyyus.pojo.Section;
import com.suyyyus.utils.CRUDUtils;
import com.suyyyus.utils.MyConnectionPool;
import com.suyyyus.utils.TimeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class SectionDaoImpl implements SectionDao {

    static MyConnectionPool myConnectionPool = new MyConnectionPool();

    /**
     * 添加课程章节
     * @param section
     * @throws SQLException
     */
    @Override
    public void addSection(Section section) throws SQLException {
        String sql = "insert into tb_course_section (sectionname, content, course_id, create_time) values (?,?,?,?)";

        CRUDUtils.ZengShanGai(sql,section.getSectionname(), section.getContent(),section.getCourse_id(), TimeUtil.formatDateTime(LocalDateTime.now()));
    }

    /**
     * 查询课程旗下的章节
     * @param course_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Section> queryAllSectionByCourse_id(int course_id) throws Exception {
        String sql = "select * from tb_course_section where course_id = ?";

        List<Section> sections = CRUDUtils.queryAllSectionByCourse_id(sql, course_id);

        return sections;
    }

    /**
     * 通过id查找章节
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Section queryById(int id) throws SQLException {
        String sql = "select * from tb_course_section where id = ?";
        //获取连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        //执行
        ResultSet resultSet = preparedStatement.executeQuery();

        Section section = new Section();

        if(resultSet.next()){
            section.setId(resultSet.getInt("id"));
            section.setSectionname(resultSet.getString("sectionname"));
            section.setContent(resultSet.getString("content"));
            section.setCourse_id(resultSet.getInt("course_id"));
            section.setCreate_time(resultSet.getString("create_time"));

        }else {
            section = null;
        }
return section;

    }
}
