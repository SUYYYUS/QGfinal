package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.pojo.Course;
import com.suyyyus.pojo.Student;
import com.suyyyus.pojo.Student_study;
import com.suyyyus.service.Student_studyService;
import com.suyyyus.service.impl.Student_studyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Student_study/*")
public class Student_studyServlet extends BaseServlet{

    private static final Logger logger =  LoggerFactory.getLogger(Student_studyServlet.class);

    Student_studyService student_studyService = new Student_studyServiceImpl();

    /**
     * 展示学生学习记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getStudyInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        // 从seession中拿出登录成功的用户信息
        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("student");
        Course course = (Course) session.getAttribute("course");
        //获取记录对象集
        Student_study student_study = student_studyService.queryStudentRecordByIds(student.getId(), course.getId());
        //  JSON数据化
        String jsonString = JSON.toJSONString(student_study);
        // 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 课程的学习人数
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public void getStudynumber(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        // 从seession中拿出登录成功的用户信息
        HttpSession session = req.getSession();
        //获取当前课程
        Course course = (Course) session.getAttribute("course");
        //查询人数
        int number = student_studyService.queryStudyNumber(course.getId());
        //  JSON数据化
        String jsonString = JSON.toJSONString(number);
        // 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 课程的学习人数的总平均分
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public void gettotalaverage_score(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        // 从seession中拿出登录成功的用户信息
        HttpSession session = req.getSession();
        //获取当前课程
        Course course = (Course) session.getAttribute("course");
        //得出总评分
        double number = student_studyService.querytotalaverage_score(course.getId());
        //  JSON数据化
        String jsonString = JSON.toJSONString(number);
        // 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 查询总平均正确率
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public void gettotalaccuracy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        // 从seession中拿出登录成功的用户信息
        HttpSession session = req.getSession();
        //获取当前课程
        Course course = (Course) session.getAttribute("course");
        //得出总平均正确率
        double number = student_studyService.querytotalaccuracy(course.getId());
        //  JSON数据化
        String jsonString = JSON.toJSONString(number);
        // 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
}
