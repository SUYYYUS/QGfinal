package com.suyyyus.dao.impl;

import com.suyyyus.dao.TeacherDao;
import com.suyyyus.pojo.Teacher;
import com.suyyyus.utils.CRUDUtils;
import com.suyyyus.utils.MD5Util;
import com.suyyyus.utils.MyConnectionPool;
import com.suyyyus.utils.TimeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {

    static MyConnectionPool myConnectionPool = new MyConnectionPool();

    /**
     * 进行登录操作
     * @param teacherid 教职工号
     * @param password 密码
     * @return
     * @throws SQLException
     */
    @Override
    public boolean Login(String teacherid, String password) throws SQLException {
        String sql = "select * from tb_teacher where teacherid = ?";
        boolean b = CRUDUtils.Login(sql, teacherid, password);
        return b;
    }

    /**
     * 添加教师
     * @param teacher
     * @throws SQLException
     */
    @Override
    public void addTeacher(Teacher teacher) throws SQLException {
        //sql语句
        String sql = "insert into tb_teacher (teachername, teacherid, password, college, qq, email, description, create_time, update_time) " +
                "values (?,?,?,?,?,?,?,?,?)";
        //对密码进行加密操作
        String saltPassword = MD5Util.generateSaltPassword(teacher.getPassword());
        //添加信息
        CRUDUtils.ZengShanGai(sql,teacher.getTeachername(),teacher.getTeacherid(),saltPassword, teacher.getCollege(), teacher.getQq(), teacher.getEmail(), teacher.getDescription(), TimeUtil.formatDateTime(LocalDateTime.now()),TimeUtil.formatDateTime(LocalDateTime.now()));

    }

    /**
     * 通过教职工号查找教师
     * @param teacherid
     * @return
     * @throws SQLException
     */
    @Override
    public Teacher queryByTeacherid(String teacherid) throws SQLException {
        String sql = "select * from tb_teacher where teacherid = ?";
        //获取连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,teacherid);

        ResultSet resultSet = preparedStatement.executeQuery();

        Teacher teacher = new Teacher();

        if (resultSet.next()){
            teacher.setId(resultSet.getInt("id"));
            teacher.setTeachername(resultSet.getString("teachername"));
            teacher.setTeacherid(resultSet.getString("teacherid"));
            teacher.setPassword(resultSet.getString("password"));
            teacher.setCollege(resultSet.getString("college"));
            teacher.setQq(resultSet.getString("qq"));
            teacher.setEmail(resultSet.getString("email"));
            teacher.setDescription(resultSet.getString("description"));
            teacher.setCreate_time(resultSet.getString("create_time"));
            teacher.setUpdate_time(resultSet.getString("update_time"));
        }else {
            teacher = null;
        }
        return teacher;
    }

    /**
     * 查询所有老师信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Teacher> queryAllTeacher() throws Exception {
        String sql = "select * from tb_teacher";
        List<Teacher> teachers = CRUDUtils.queryAllTeacher(sql);

        return teachers;
    }

    /**
     * 修改教师信息
     * @param teacher
     * @return
     * @throws SQLException
     */
    @Override
    public int updateInfo(Teacher teacher) throws SQLException {
        String sql = "update tb_teacher set teachername = ?, teacherid = ?, college = ?, qq = ?, email = ?, description = ?, update_time = ? where id = ?";
//        String saltPassword = MD5Util.generateSaltPassword(teacher.getPassword());
        CRUDUtils.ZengShanGai(sql,teacher.getTeachername(),teacher.getTeacherid(),
                teacher.getCollege(),teacher.getQq(),teacher.getEmail(),
                teacher.getDescription(),TimeUtil.formatDateTime(LocalDateTime.now()),teacher.getId());
    return 0;
    }
}
