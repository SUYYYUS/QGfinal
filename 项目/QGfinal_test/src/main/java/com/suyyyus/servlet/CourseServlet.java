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
        //读取数据
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        //获取课程
        Course course = JSON.parseObject(s, Course.class);
        //设置课程内容
        course.setTeacher_id(teacher.getId());
        //添加课程
        courseService.addCourse(course);
        //日志记录
        Teacher_logging teacher_logging = new Teacher_logging();
        teacher_logging.setTeacher_id(teacher.getId());
        teacher_logging.setLogging("添加了新课程");
        teacherService.addLogging(teacher_logging);

        logger.info(teacher.getTeachername() +"老师新添加了" + course.getCoursename() + "这门课程");
        System.out.println(teacher.getTeacherid());
        //提示操作成功
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
        //获取老师对象
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        //查询老师所开设的课程
        List<Course> courseList = courseService.queryAllCourseByTeacher_id(teacher.getId());
        //2.转为JSON
        String jsonString = JSON.toJSONString(courseList);
        //3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }


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
        //数据类型转换
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //进行分页查询
        PageBean<Course> pageBean = courseService.selectByPage(currentPage, pageSize);
        //2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }


    /**
     * 通过课程id查询课程
     * @param req
     * @param resp
     * @throws Exception
     */
    public void queryById(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取数据
        BufferedReader reader = req.getReader();
        String _id = reader.readLine();
        //转化int类型
        int id = Integer.parseInt(_id);
        //进行id查询
        Course course = courseService.queryByCourse_id(id);
        //2.转为JSON
        String jsonString = JSON.toJSONString(course);
        //3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 通过学科进行模糊查询
     * @param req
     * @param resp
     * @throws Exception
     */
    public void queryBySubject(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //防止中文乱码
        req.setCharacterEncoding("UTF-8");
        //获取数据
        BufferedReader reader = req.getReader();
        String subject = reader.readLine();
        //获取与该学科有关的课程
        List<Course> list = courseService.queryBySubject(subject);
        //2.转为JSON
        String jsonString = JSON.toJSONString(list);
        //3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
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
        //获取信息并转化类型
        String _id = reader.readLine();
        int id = Integer.parseInt(_id);
        //通过id查询该课程
        Course course = courseService.queryByCourse_id(id);
        //保存最新的课程信息
        session.setAttribute("course",course);
        System.out.println(course.getId());
        //提示操作成功
        resp.getWriter().write("success");
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
        //  JSON数据化
        String jsonString = JSON.toJSONString(course);
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
        //获取所有课程信息
        List<Course> courseList = courseService.queryAllCourse();
        //进行热门排行
        Collections.sort(courseList);
        //转化为JSON格式
        String jsonString = JSON.toJSONString(courseList);
        //上传数据
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
        //post方法获取数据
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        //获取要删除的课程的所有id
        int[] ids = JSON.parseObject(params, int[].class);
        //进行删除操作
        courseService.deleteCourses(ids);
        //日志记录
        logger.info("管理员对课程进行删除");
        //提示删除成功
        resp.getWriter().write("success");
    }
}




