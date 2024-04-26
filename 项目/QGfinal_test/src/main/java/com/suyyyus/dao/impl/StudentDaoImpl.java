package com.suyyyus.dao.impl;

import com.suyyyus.dao.StudentDao;
import com.suyyyus.pojo.Course;
import com.suyyyus.pojo.Student;
import com.suyyyus.pojo.Student_course;
import com.suyyyus.pojo.Student_logging;
import com.suyyyus.utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    static MyConnectionPool myConnectionPool = new MyConnectionPool();

    /**
     * 登录操作
     * @param studentid 学号
     * @param password 密码
     * @return
     * @throws SQLException
     */
    @Override
    public boolean Login(String studentid, String password) throws SQLException {
        String sql = "select * from tb_student where studentid = ?";
        //执行sql语句
        boolean b = CRUDUtils.Login(sql, studentid, password);
        return b;
    }

    /**
     * 添加学生
     * @param student
     * @throws SQLException
     */
    @Override
    public void addStudent(Student student) throws SQLException {
        //对密码进行加密操作
        String saltPassword = MD5Util.generateSaltPassword(student.getPassword());

        //sql语句
        String sql = "insert into tb_student (studentname, studentid, password, grade, qq, description, create_time, update_time) " +
                "values (?,?,?,?,?,?,?,?)";
        //执行sql语句
        //添加信息
        CRUDUtils.ZengShanGai(sql,
                student.getStudentname(),
                student.getStudentid(),
                saltPassword,
                student.getGrade(),
                student.getQq(),
                student.getDescription(),
                TimeUtil.formatDateTime(LocalDateTime.now()),
                TimeUtil.formatDateTime(LocalDateTime.now()));
    }

    /**
     * 通过学号查找学生
     * @param studentid
     * @return
     * @throws SQLException
     */
    @Override
    public Student queryByStudentid(String studentid) throws SQLException {
        String sql = "select * from tb_student where studentid = ?";
        //获取连接池的连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentid);
        ResultSet resultSet = preparedStatement.executeQuery();

        Student student = new Student();

        if(resultSet.next()){
            student.setId(resultSet.getInt("id"));
            student.setStudentname(resultSet.getString("studentname"));
            student.setStudentid(resultSet.getString("studentid"));
            student.setPassword(resultSet.getString("password"));
            student.setGrade(resultSet.getString("grade"));
            student.setQq(resultSet.getString("qq"));
            student.setDescription(resultSet.getString("description"));
            student.setCreate_time(resultSet.getString("create_time"));
            student.setUpdate_time(resultSet.getString("update_time"));
        }else {
            student = null;
        }
        //返回数据
        return student;
    }

    /**
     * 通过id查找学生
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Student queryById(int id) throws SQLException {
        String sql = "select * from tb_student where id = ?";
        //获取连接池的连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Student student = new Student();

        if(resultSet.next()){
            student.setId(resultSet.getInt("id"));
            student.setStudentname(resultSet.getString("studentname"));
            student.setStudentid(resultSet.getString("studentid"));
            student.setPassword(resultSet.getString("password"));
            student.setGrade(resultSet.getString("grade"));
            student.setQq(resultSet.getString("qq"));
            student.setDescription(resultSet.getString("description"));
            student.setCreate_time(resultSet.getString("create_time"));
            student.setUpdate_time(resultSet.getString("update_time"));
        }else {
            student = null;
        }
        //返回数据
        return student;
    }



    /**
     * 修改学生信息
     * @param student
     * @return
     * @throws SQLException
     */
    @Override
    public int updateStudent(Student student) throws SQLException {
        String sql = "update tb_student set studentname = ?, studentid = ?, grade = ?, qq = ?, description = ?, update_time = ? where id = ?";
        //执行sql语句
        CRUDUtils.ZengShanGai(sql,
                student.getStudentname(),
                student.getStudentid(),
                student.getGrade(),
                student.getQq(),
                student.getDescription(),
                TimeUtil.formatDateTime(LocalDateTime.now()),
                student.getId());
        return 0;
    }

    /**
     * 学生报名课程
     * @param student_course
     * @return
     * @throws SQLException
     */
    @Override
    public boolean addStudent_course(Student_course student_course) throws SQLException {
        String sql = "insert into tb_student_course (student_id, course_id, register_time, status) values (?,?,?,?)";
        //执行sql语句
        CRUDUtils.ZengShanGai(sql,student_course.getStudent_id(),student_course.getCourse_id(),TimeUtil.formatDateTime(LocalDateTime.now()),1);
        //执行成功操作
        return true;
    }

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Override
    public List<Student> selectByPage(int begin, int size) {
        String sql = "select * from tb_student limit  ? , ?";
        List<Student> list = null;
        try {
            //执行sql语句
            list = CRUDUtils.selectStudentByPage(sql,begin,size);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回数据
        return list;
    }

    /**
     * 查询学生数量
     * @return
     * @throws Exception
     */
    @Override
    public int selectAllCount() throws Exception {
        String sql = "select count(*) from tb_student ";
        //执行sql语句
        int count = CRUDUtils.allStudentCount(sql);
        //返回数据
        return count;
    }

    /**
     * 批量删除学生操作
     * @param id
     */
    @Override
    public void deleteStudents(int[] id) {
        //遍历数组
        for (int i : id) {
            try {
                String sql = "delete from tb_student where id = ?";
                //执行sql语句
                CRUDUtils.ZengShanGai(sql,i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 重置学生账号密码
     * @param student
     * @throws SQLException
     */
    @Override
    public void resetPassword(Student student) throws SQLException {
        String password = MD5Util.generateSaltPassword("123456");

        String sql = "update tb_student set password = ? where id = ?";
        //执行sql语句
        CRUDUtils.ZengShanGai(sql, password, student.getId());
    }


    /**
     * 通过年级查找学生
     * @param grade
     * @return
     * @throws SQLException
     */
    @Override
    public List<Student> queryByGrade(String grade) throws SQLException {
        String sql = "select * from tb_student where grade = ?";
        //获取连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,grade);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Student> list = new ArrayList<>();

        while(resultSet.next()) {
            list.add(new Student(resultSet.getInt("id"),resultSet.getString("studentname"),
                    resultSet.getString("studentid"), resultSet.getString("password"),
                    resultSet.getString("grade"), resultSet.getString("qq"),
                    resultSet.getString("description"), resultSet.getString("create_time"),
                    resultSet.getString("update_time")));

        }
        if(list.size() == 0) {
            System.out.println("当前没有学生");
            //释放资源
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            //释放资源
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }
    }

    /**
     * 添加日志
     * @param student_logging
     * @throws SQLException
     */
    @Override
    public void addLogging(Student_logging student_logging) throws SQLException {
        String sql = "insert into tb_student_logging (student_id, logging) values (?,?)";
        //执行sql语句
        CRUDUtils.ZengShanGai(sql, student_logging.getStudent_id(), student_logging.getLogging());
    }

    /**
     * 通过id查询学生的日志
     * @param student_id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Student_logging> queryLoggingById(int student_id) throws SQLException {
        String sql = "select * from tb_student_logging where student_id = ?";
        //获取连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,student_id);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Student_logging> list = new ArrayList<>();

        while(resultSet.next()){
            list.add(new Student_logging(resultSet.getInt("id"),resultSet.getInt("student_id"),
                    resultSet.getString("logging")));
        }
        if(list.size() == 0) {
            System.out.println("当前没有行为");
            //释放资源
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            //释放资源
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }

    }


    /**
     * 通过姓名查找学生
     * @param studentname
     * @return
     * @throws SQLException
     */
    @Override
    public Student queryByName(String studentname) throws SQLException {
        String sql = "select * from tb_student where studentname = ?";
        //获取连接池的连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentname);
        ResultSet resultSet = preparedStatement.executeQuery();

        Student student = new Student();

        if(resultSet.next()){
            student.setId(resultSet.getInt("id"));
            student.setStudentname(resultSet.getString("studentname"));
            student.setStudentid(resultSet.getString("studentid"));
            student.setPassword(resultSet.getString("password"));
            student.setGrade(resultSet.getString("grade"));
            student.setQq(resultSet.getString("qq"));
            student.setDescription(resultSet.getString("description"));
            student.setCreate_time(resultSet.getString("create_time"));
            student.setUpdate_time(resultSet.getString("update_time"));
        }else {
            student = null;
        }
        //返回学生对象
        return student;
    }
}
