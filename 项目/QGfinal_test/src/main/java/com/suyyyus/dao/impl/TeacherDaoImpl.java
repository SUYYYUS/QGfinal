package com.suyyyus.dao.impl;

import com.suyyyus.dao.TeacherDao;
import com.suyyyus.pojo.Student;
import com.suyyyus.pojo.Student_logging;
import com.suyyyus.pojo.Teacher;
import com.suyyyus.pojo.Teacher_logging;
import com.suyyyus.utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        //对密码进行加密操作
        String saltPassword = MD5Util.generateSaltPassword(teacher.getPassword());
        //sql语句
        String sql = "insert into tb_teacher (teachername, teacherid, password, college, qq, email, description, create_time, update_time) " +
                "values (?,?,?,?,?,?,?,?,?)";
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
     * 根据id查找老师
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Teacher queryByid(int id) throws SQLException {
        String sql = "select * from tb_teacher where id = ?";
        //获取连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);

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


    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Override
    public List<Teacher> selectByPage(int begin, int size) {
        String sql = "select * from tb_teacher limit  ? , ?";
        List<Teacher> list = null;
        try {
            list = CRUDUtils.selectTeacherByPage(sql,begin,size);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询学生数量
     * @return
     * @throws Exception
     */
    @Override
    public int selectAllCount() throws Exception {
        String sql = "select count(*) from tb_teacher ";
        int count = CRUDUtils.allTeacherCount(sql);
        return count;
    }

    /**
     * 批量删除学生操作
     * @param id
     */
    @Override
    public void deleteTeachers(int[] id) {
        for (int i : id) {
            try {
                String sql = "delete from tb_teacher where id = ?";
                CRUDUtils.ZengShanGai(sql,i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过学院查询教师
     * @param college
     * @return
     * @throws SQLException
     */
    @Override
    public List<Teacher> queryByCollege(String college) throws SQLException {
        String sql = "select * from tb_teacher where college = ?";
        //获取连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,college);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Teacher> teacherList = new ArrayList<>();

        while(resultSet.next()){
            teacherList.add(new Teacher(resultSet.getInt("id"),resultSet.getString("teachername"),
                    resultSet.getString("teacherid"), resultSet.getString("password"),
                    resultSet.getString("college"), resultSet.getString("qq"),
                    resultSet.getString("email"), resultSet.getString("description"),
                    resultSet.getString("create_time"), resultSet.getString("update_time")));

        }

        JDBCUtil.close(connection,preparedStatement,resultSet);

        return teacherList;
    }

    /**
     * 添加日志
     * @param teacher_logging
     * @throws SQLException
     */
    @Override
    public void addLogging(Teacher_logging teacher_logging) throws SQLException {
        String sql = "insert into tb_teacher_logging (teacher_id, logging) values (?,?)";

        CRUDUtils.ZengShanGai(sql, teacher_logging.getTeacher_id(), teacher_logging.getLogging());
    }

    /**
     * 通过id查看教师日志
     * @param teacher_id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Teacher_logging> queryLoggingById(int teacher_id) throws SQLException {
        String sql = "select * from tb_teacher_logging where teacher_id = ?";
        //获取连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,teacher_id);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Teacher_logging> list = new ArrayList<>();

        while(resultSet.next()){
            list.add(new Teacher_logging(resultSet.getInt("id"),resultSet.getInt("teacher_id"),
                    resultSet.getString("logging")));
        }
        if(list.size() == 0) {
            System.out.println("当前没有行为");
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }
    }
}
