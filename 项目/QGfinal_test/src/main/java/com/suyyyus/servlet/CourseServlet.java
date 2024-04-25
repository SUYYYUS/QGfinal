package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.dao.DiscussionDao;
import com.suyyyus.dao.impl.DiscussionDaoImpl;
import com.suyyyus.pojo.*;
import com.suyyyus.service.CourseService;
import com.suyyyus.service.DiscussionServcie;
import com.suyyyus.service.TeacherService;
import com.suyyyus.service.impl.CourseServiceImpl;
import com.suyyyus.service.impl.DiscussionServiceImpl;
import com.suyyyus.service.impl.TeacherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@WebServlet("/Course/*")
public class CourseServlet extends BaseServlet{

    private static final Logger logger =  LoggerFactory.getLogger(CourseServlet.class);

    CourseService courseService = new CourseServiceImpl();

    DiscussionDao discussionDao = new DiscussionDaoImpl();
    DiscussionServcie discussionServcie = new DiscussionServiceImpl();

    TeacherService teacherService  = new TeacherServiceImpl();

    /**
     * 用于添加课程
     * @param req
     * @param resp
     * @throws Exception
     */
    public void addCourse(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        // 从seession中拿出登录成功的教师信息
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        Course course = JSON.parseObject(s, Course.class);

        course.setTeacher_id(teacher.getId());

        courseService.addCourse(course);

        Teacher_logging teacher_logging = new Teacher_logging();
        teacher_logging.setTeacher_id(teacher.getId());
        teacher_logging.setLogging("添加了新课程");
        teacherService.addLogging(teacher_logging);

        logger.info(teacher.getTeachername() +"老师新添加了" + course.getCoursename() + "这门课程");
        System.out.println(teacher.getTeacherid());

        resp.getWriter().write("addcourse_success");
    }


    /**
     * 查询教师id下开设的课程
     * @param req
     * @param resp
     * @throws Exception
     */
    public void selectAllCourseByteacher_id(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // 从seession中拿出登录成功的教师信息
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        List<Course> courseList = courseService.queryAllCourseByTeacher_id(teacher.getId());

        //2.转为JSON
        String jsonString = JSON.toJSONString(courseList);

        //3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
        //System.out.println("User selectAll----------");
    }

//    *
//     * 查询平台所有课程
//     * @param req
//     * @param resp
//     * @throws Exception
//    public void selectAllCourse(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//
//        // 从seession中拿出登录成功的教师信息
////        HttpSession session = req.getSession();
////        Teacher teacher = (Teacher) session.getAttribute("teacher");
//
////        List<Course> courseList = courseService.queryAllCourseByTeacher_id(teacher.getId());
//
//        List<Course> courseList = courseService.queryAllCourse();
//        //2.转为JSON
//        String jsonString = JSON.toJSONString(courseList);
//
//        //3.写数据
//        resp.setContentType("text/json;charset=utf-8");
//        resp.getWriter().write(jsonString);
//        //System.out.println("User selectAll----------");
//    }

    /**
     * 分页查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.调用查询
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        PageBean<Course> pageBean = courseService.selectByPage(currentPage, pageSize);

        //2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3.写数据

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
        //System.out.println("User selectAll----------");
    }


    /**
     * 通过课程id查询课程
     * @param req
     * @param resp
     * @throws Exception
     */
    public void queryById(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.调用查询
        //String _id = req.getParameter("id");


        BufferedReader reader = req.getReader();
        String _id = reader.readLine();
        int id = Integer.parseInt(_id);

        Course course = courseService.queryByCourse_id(id);

        //2.转为JSON
        String jsonString = JSON.toJSONString(course);
        //3.写数据

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
        //System.out.println("User selectAll----------");
    }

    /**
     * 通过学科进行模糊查询
     * @param req
     * @param resp
     * @throws Exception
     */
    public void queryBySubject(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.调用查询

        req.setCharacterEncoding("UTF-8");

        BufferedReader reader = req.getReader();
        String subject = reader.readLine();

//        System.out.println(subject);
        List<Course> list = courseService.queryBySubject(subject);

        //2.转为JSON
        String jsonString = JSON.toJSONString(list);
        //3.写数据

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
        //System.out.println("User selectAll----------");
    }

    /**
     * 通过课程id查询课程并且保存
     * @param req
     * @param resp
     * @throws Exception
     */
    public void queryByIdAndSession(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //获取session对象，储存课程
        HttpSession session = req.getSession();
        BufferedReader reader = req.getReader();
        String _id = reader.readLine();
        int id = Integer.parseInt(_id);

        Course course = courseService.queryByCourse_id(id);

        session.setAttribute("course",course);

        System.out.println("111111111111111222222222222222");
        System.out.println(course.getId());

        resp.getWriter().write("success");
        //System.out.println("User selectAll----------");
    }

    /**
     * 展示当前课程信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getSelfInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从seession中拿出课程信息
        HttpSession session = req.getSession();
        Object course = session.getAttribute("course");
//        System.out.println(user);
        //  JSON数据化
        String jsonString = JSON.toJSONString(course);
//        System.out.println(jsonString);
        // 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 通过课程id展示该课程的讨论区
     * @param req
     * @param resp
     * @throws SQLException
     * @throws IOException
     */
    public void showCourseDiscussionForum(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        //获取session对象
        HttpSession session = req.getSession();
        //获取课程对象
        Course course = (Course) session.getAttribute("course");

        //获取讨论区信息
        List<Discussion> discussionList = discussionServcie.queryAllByCourse_id(course.getId());
        //转化为JSON格式
        String jsonString = JSON.toJSONString(discussionList);

        //上传数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);

    }

    /**
     * 展示热门榜单
     * @param req
     * @param resp
     * @throws Exception
     */
    public void hitCourse(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Course> courseList = courseService.queryAllCourse();

        Collections.sort(courseList);

        String jsonString = JSON.toJSONString(courseList);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 批量删除课程
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //post方法
        BufferedReader reader = req.getReader();

        String params = reader.readLine();

        int[] ids = JSON.parseObject(params, int[].class);
        courseService.deleteCourses(ids);

        logger.info("管理员对课程进行删除");

        resp.getWriter().write("success");
    }


}




