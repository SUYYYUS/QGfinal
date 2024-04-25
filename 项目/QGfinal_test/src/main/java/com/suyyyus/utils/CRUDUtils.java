package com.suyyyus.utils;

import com.suyyyus.pojo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    private static PreparedStatement preparedStatement = null;
    private static Connection connection = null;
    private static ResultSet resultSet = null;
    static MyConnectionPool myConnectionPool = new MyConnectionPool();

    /**
     * 通用的老师与学生登录还有管理员
     * @param sql
     * @param number 学号/教职工号
     * @param password 密码
     * @return
     * @throws SQLException
     */
     public static boolean Login(String sql, String number, String password) throws SQLException {
         //获取连接
         connection = myConnectionPool.getConnection();
         //获取相应
         preparedStatement = connection.prepareStatement(sql);
         //先通过学号或者教职工号查询
         preparedStatement.setString(1,number);
         //获取resultSet
         resultSet = preparedStatement.executeQuery();

         if(resultSet.next()){
             if(MD5Util.verifySaltPassword(password,resultSet.getString("password"))){
                 JDBCUtil.close(connection, preparedStatement, resultSet);
                 return true;
             }
             else {
                 JDBCUtil.close(connection, preparedStatement, resultSet);
                 return false;
             }
         }
         else {
             JDBCUtil.close(connection, preparedStatement, resultSet);
             return false;
         }
     }


    /**
     * 通用的增删改操作
     * @param sql
     * @param params
     * @throws SQLException
     */
     public static void ZengShanGai(String sql, Object... params) throws SQLException {
         //获取连接
         connection = myConnectionPool.getConnection();
         //预编译
         preparedStatement = connection.prepareStatement(sql);
         //填充占位符
         for (int i = 0; i < params.length; i++) {
             preparedStatement.setObject(i + 1, params[i]);
         }
         int i = preparedStatement.executeUpdate();

         if(i != 0){
             System.out.println("操作成功");
             JDBCUtil.close(connection, preparedStatement);
         }else {
             System.out.println("f**k");
             JDBCUtil.close(connection, preparedStatement);
         }
     }

    /**
     * 查询所有教师信息
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Teacher> queryAllTeacher(String sql) throws Exception {
        //获取连接
        connection = myConnectionPool.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        List<Teacher> list = new ArrayList<>();

        while(resultSet.next()) {
            list.add(new Teacher(resultSet.getInt("id"),resultSet.getString("teachername"),
                    resultSet.getString("teacherid"),resultSet.getString("password"),
                    resultSet.getString("college"),resultSet.getString("qq"),resultSet.getString("email"),
                    resultSet.getString("description"),resultSet.getString("create_time"),
                    resultSet.getString("update_time")));
        }
        if(list.size() == 0) {
            System.out.println("当前没有用户");
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }
        //释放资源
    }


    /**
     * 查询当前所有课程
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Course> queryAllCourse(String sql) throws Exception {
        //获取连接
        connection = myConnectionPool.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);

        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        List<Course> list = new ArrayList<>();

        while(resultSet.next()) {
            list.add(new Course(resultSet.getInt("id"),resultSet.getString("coursename"),
                    resultSet.getString("subject"), resultSet.getString("description"),
                    resultSet.getInt("teacher_id"), resultSet.getInt("section_number"),
                    resultSet.getInt("limitnumber"),resultSet.getInt("registernumber"),
                    resultSet.getString("create_time"), resultSet.getString("end_time")));

        }
        if(list.size() == 0) {
            System.out.println("当前没有开设课程");
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }
        //释放资源
    }



    /**
     * 查询当前教师的ID查找其所开的所有课程
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Course> queryAllCourseByTeacher_id(String sql, Object...params) throws Exception {
        //获取连接
        connection = myConnectionPool.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //填充占位符
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        List<Course> list = new ArrayList<>();

        while(resultSet.next()) {
                list.add(new Course(resultSet.getInt("id"),resultSet.getString("coursename"),
                        resultSet.getString("subject"), resultSet.getString("description"),
                        resultSet.getInt("teacher_id"), resultSet.getInt("section_number"),
                        resultSet.getInt("limitnumber"),resultSet.getInt("registernumber"),
                        resultSet.getString("create_time"), resultSet.getString("end_time")));

        }
        if(list.size() == 0) {
            System.out.println("当前没有开设课程");
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }
        //释放资源
    }

    /**
     * 查询当前课程的ID查找其所拥有的所有章节
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Section> queryAllSectionByCourse_id(String sql, Object...params) throws Exception {
        //获取连接
        connection = myConnectionPool.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //填充占位符
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        List<Section> list = new ArrayList<>();

        while(resultSet.next()) {
            list.add(new Section(resultSet.getInt("id"),resultSet.getString("sectionname"),
                    resultSet.getString("content"),
                    resultSet.getInt("course_id"),
                    resultSet.getString("create_time")));

        }
        if(list.size() == 0) {
            System.out.println("当前没有对应章节");
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }
        //释放资源
    }

    /**
     * 通过课程和章节id查询对应题目
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static List<Question> queryAllQuestionByCidAndSid(String sql , Object...params) throws SQLException {
        //获取连接
        connection = myConnectionPool.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //填充占位符
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        List<Question> questions = new ArrayList<>();

        while (resultSet.next()){
            questions.add(new Question(resultSet.getInt("id"),resultSet.getString("type"),
                    resultSet.getString("content"),resultSet.getString("answer"),resultSet.getInt("score"),
                    resultSet.getInt("course_section_id"),resultSet.getInt("course_id"),resultSet.getString("create_time")));
        }
        if(questions.size() == 0) {
            System.out.println("当前没有对应题目");
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return questions;
        }
        //释放资源

    }

    /**
     * 通过学生id查询他报名的课程
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Student_course> queryAllCourseByStudent_id(String sql, Object...params) throws Exception {
        //获取连接
        connection = myConnectionPool.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //填充占位符
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        List<Student_course> list = new ArrayList<>();

        while(resultSet.next()) {
            list.add(new Student_course(resultSet.getInt("id"),
                    resultSet.getInt("student_id"), resultSet.getInt("course_id"),
                    resultSet.getString("register_time"), resultSet.getInt("status")));

        }
        if(list.size() == 0) {
            System.out.println("当前没有开设课程");
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }
        //释放资源
    }


    /**
     * 通过课程id查询报名的学生
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Student_course> queryAllStudentByCourse_id(String sql, Object...params) throws Exception {
        //获取连接
        connection = myConnectionPool.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //填充占位符
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        List<Student_course> list = new ArrayList<>();

        while(resultSet.next()) {
            list.add(new Student_course(resultSet.getInt("id"),
                    resultSet.getInt("student_id"), resultSet.getInt("course_id"),
                    resultSet.getString("register_time"), resultSet.getInt("status")));

        }
        if(list.size() == 0) {
            System.out.println("当前没有开设课程");
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }
        //释放资源
    }


    /**
     * //分页查询（主要用于课程中心）
     * 1.开始索引 (当前页码 - 1) * 每页显示的条数
     * 2.查询的条目数
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Course> selectByPage(String sql,Object... params) throws Exception {
        //获取连接
        connection = JDBCUtil.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //填充占位符
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        List<Course> list = new ArrayList<>();
        while(resultSet.next()) {
            list.add(new Course(resultSet.getInt("id"),resultSet.getString("coursename"),
                    resultSet.getString("subject"), resultSet.getString("description"),
                    resultSet.getInt("teacher_id"), resultSet.getInt("section_number"),
                    resultSet.getInt("limitnumber"),resultSet.getInt("registernumber"),
                    resultSet.getString("create_time"), resultSet.getString("end_time")));

        }
        if(list.size() == 0) {
            System.out.println("当前没有用户");
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }


    }



    /**
     * 查询总信息条数（主要用于课程中心）
     * @param sql
     * @return
     */
    public static int allCount(String sql) throws Exception {
        //获取连接
        connection = JDBCUtil.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        int count = 0;

        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;
    }

    /**
     * //分页查询（主要用于学生）
     * 1.开始索引 (当前页码 - 1) * 每页显示的条数
     * 2.查询的条目数
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Student> selectStudentByPage(String sql,Object... params) throws Exception {
        //获取连接
        connection = JDBCUtil.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //填充占位符
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

        //获取resultSet
        resultSet = preparedStatement.executeQuery();

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
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }


    }

    /**
     * 查询总信息条数（主要用于学生）
     * @param sql
     * @return
     */
    public static int allStudentCount(String sql) throws Exception {
        //获取连接
        connection = JDBCUtil.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        int count = 0;

        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;
    }

    /**
     * //分页查询（主要用于留言）
     * 1.开始索引 (当前页码 - 1) * 每页显示的条数
     * 2.查询的条目数
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Discussion> selectDiscussionByPage(String sql,Object... params) throws Exception {
        //获取连接
        connection = JDBCUtil.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //填充占位符
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        List<Discussion> list = new ArrayList<>();
        while(resultSet.next()) {
            list.add(new Discussion(resultSet.getInt("id"), resultSet.getInt("student_id"),
                    resultSet.getInt("teacher_id"), resultSet.getInt("course_id"),
                    resultSet.getString("question"), resultSet.getString("reply"),
                    resultSet.getString("question_time"), resultSet.getString("reply_time")));

        }
        if(list.size() == 0) {
            System.out.println("当前没有留言");
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }


    }

    /**
     * 查询总信息条数（主要用于学生）
     * @param sql
     * @return
     */
    public static int allDiscussionCount(String sql) throws Exception {
        //获取连接
        connection = JDBCUtil.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        int count = 0;

        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;
    }


    /**
     * //分页查询（主要用于教师）
     * 1.开始索引 (当前页码 - 1) * 每页显示的条数
     * 2.查询的条目数
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Teacher> selectTeacherByPage(String sql,Object... params) throws Exception {
        //获取连接
        connection = JDBCUtil.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //填充占位符
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        List<Teacher> list = new ArrayList<>();
        while(resultSet.next()) {
            list.add(new Teacher(resultSet.getInt("id"),resultSet.getString("teachername"),
                    resultSet.getString("teacherid"), resultSet.getString("password"),
                    resultSet.getString("college"), resultSet.getString("qq"),
                    resultSet.getString("email"), resultSet.getString("description"),
                    resultSet.getString("create_time"), resultSet.getString("update_time")));

        }
        if(list.size() == 0) {
            System.out.println("当前没有学生");
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return null;
        }else {
            JDBCUtil.close(connection,preparedStatement,resultSet);
            return list;
        }


    }

    /**
     * 查询总信息条数（主要用于学生）
     * @param sql
     * @return
     */
    public static int allTeacherCount(String sql) throws Exception {
        //获取连接
        connection = JDBCUtil.getConnection();
        //预编译
        preparedStatement = connection.prepareStatement(sql);
        //获取resultSet
        resultSet = preparedStatement.executeQuery();

        int count = 0;

        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;
    }

}
