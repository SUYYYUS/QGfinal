package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.dao.DiscussionDao;
import com.suyyyus.dao.StudentDao;
import com.suyyyus.dao.TeacherDao;
import com.suyyyus.dao.impl.DiscussionDaoImpl;
import com.suyyyus.dao.impl.StudentDaoImpl;
import com.suyyyus.dao.impl.TeacherDaoImpl;
import com.suyyyus.pojo.*;
import com.suyyyus.service.CourseService;
import com.suyyyus.service.DiscussionServcie;
import com.suyyyus.service.StudentService;
import com.suyyyus.service.TeacherService;
import com.suyyyus.service.impl.CourseServiceImpl;
import com.suyyyus.service.impl.DiscussionServiceImpl;
import com.suyyyus.service.impl.StudentServiceImpl;
import com.suyyyus.service.impl.TeacherServiceImpl;
import com.suyyyus.utils.Validator;
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
import java.util.List;

@WebServlet("/Teacher/*")
public class TeacherServlet extends BaseServlet{

    private static final Logger logger =  LoggerFactory.getLogger(TeacherServlet.class);
    TeacherService teacherService = new TeacherServiceImpl();
    TeacherDao teacherDao = new TeacherDaoImpl();

    DiscussionDao discussionDao = new DiscussionDaoImpl();
    DiscussionServcie discussionServcie = new DiscussionServiceImpl();

    StudentDao studentDao = new StudentDaoImpl();
    StudentService studentService = new StudentServiceImpl();

    CourseService courseService = new CourseServiceImpl();

    /**
     * 教师登录
     * @param req
     * @param resp
     * @throws Exception
     */
    public void teacherLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        //获取session对象，储存Teacher
        HttpSession session = req.getSession();

        Teacher teacher = JSON.parseObject(s,Teacher.class);

        if(teacher.getTeacherid() == null || teacher.getPassword() == null){
            resp.getWriter().write("null");
            logger.info("有教师登录失败");
            return ;
        }else {
            //设置编码格式
            resp.setContentType("text/html;charset=utf-8");



            boolean b = teacherService.Login(teacher.getTeacherid(), teacher.getPassword());

            if(b){
                Teacher teacher1 = teacherService.queryByTeacherid(teacher.getTeacherid());

                session.setAttribute("teacher", teacher1);
                req.getSession().setMaxInactiveInterval(30 * 60); //会话过期时间为30分钟

                //保存日志
                Teacher_logging teacher_logging = new Teacher_logging();
                teacher_logging.setTeacher_id(teacher1.getId());
                teacher_logging.setLogging("成功登录平台");
                teacherService.addLogging(teacher_logging);
                logger.info(teacher1.getTeachername() + "老师成功登录");
                resp.getWriter().write("success");
            }else {
                resp.getWriter().write("failed");
                logger.info("登录失败");
            }
//            resp.setStatus(HttpServletResponse.SC_OK);
//            System.out.println(resp.getStatus());
        }
    }

    /**
     * 教师登录成功后把个人信息展示出来
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getSelfInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从seession中拿出登录成功的教师信息
        HttpSession session = req.getSession();
        Object teacher = session.getAttribute("teacher");
//        System.out.println(user);
        //  JSON数据化
        String jsonString = JSON.toJSONString(teacher);
//        System.out.println(jsonString);
        // 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }


    /**
     * 修改教师信息
     * @param req
     * @param resp
     * @throws Exception
     */
    public void updateTeacher(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //post方法获取数据
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        //获取session对象，储存Teacher
        HttpSession session = req.getSession();
        Teacher teacher = JSON.parseObject(s,Teacher.class);

        teacherService.updateInfo(teacher);

        Teacher teacher1 = teacherService.queryByTeacherid(teacher.getTeacherid());
        //保存日志里面去
        Teacher_logging teacher_logging = new Teacher_logging();
        teacher_logging.setTeacher_id(teacher1.getId());
        teacher_logging.setLogging("修改了个人信息");
        teacherService.addLogging(teacher_logging);
        logger.info(teacher1.getTeachername() + "老师修改了自己的个人信息");
        session.setAttribute("teacher", teacher1);

        resp.getWriter().write("success");
    }

    /**
     * 用于教师注册
     * @param req
     * @param resp
     * @throws Exception
     */
    public void teacherSignup(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //post方法获取数据
        BufferedReader reader = req.getReader();
        String s = reader.readLine(); //???? s = null ????


        Teacher teacher = JSON.parseObject(s,Teacher.class);

        teacherService.addTeacher(teacher);

        Teacher_logging teacher_logging = new Teacher_logging();
        teacher_logging.setTeacher_id(teacher.getId());
        teacher_logging.setLogging("成功注册账号");
        teacherService.addLogging(teacher_logging);
        logger.info(teacher.getTeachername() + "老师成功注册");

        resp.getWriter().write("success");

    }

    /**
     * 获取该老师的所有留言
     * @param req
     * @param resp
     * @throws SQLException
     * @throws IOException
     */
    public void queryMyDiscussionByTeacher_id(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        //获取session对象
        HttpSession session = req.getSession();
        //获取当前老师对象
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        List<Discussion> discussionList =  discussionServcie.queryAllByTeacher_id(teacher.getId());

        //转化为JSON
        String jsonString = JSON.toJSONString(discussionList);

        // 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 教师进行回复
     * @param req
     * @param resp
     * @throws IOException
     * @throws SQLException
     */
    public void replyStudentQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        //获取前端数据
        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        //获得信息
        Discussion discussion = JSON.parseObject(s,Discussion.class);

        Teacher teacher = teacherService.queryByid(discussion.getTeacher_id());
        Student student = studentService.queryById(discussion.getStudent_id());

        //添加回复
        discussionServcie.TeacherReply(discussion);

        Teacher_logging teacher_logging = new Teacher_logging();
        teacher_logging.setTeacher_id(teacher.getId());
        teacher_logging.setLogging("回复了" + student.getStudentname() + "的留言");
        teacherService.addLogging(teacher_logging);
        logger.info(teacher.getTeachername() + "老师回复了" + student.getStudentname() + "同学的留言");

        resp.getWriter().write("success");

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

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        PageBean<Teacher> pageBean = teacherService.selectTeacherByPage(currentPage, pageSize);

        //2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3.写数据

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
        //System.out.println("User selectAll----------");
    }

    /**
     * 批量删除教师
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
        teacherService.deleteTeachers(ids);

        logger.info("管理员对教师进行删除");

        resp.getWriter().write("success");
    }


    /**
     * 通过学院查询教师
     * @param req
     * @param resp
     * @throws IOException
     * @throws SQLException
     */
    public void queryByCollege(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        req.setCharacterEncoding("UTF-8");

        //post方法
        BufferedReader reader = req.getReader();

        String params = reader.readLine();
        System.out.println(params);

        List<Teacher> teacherList = teacherService.queryByCollege(params);

        String s = JSON.toJSONString(teacherList);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(s);
    }

    /**
     * 教师退出登录
     * @param req
     * @param resp
     * @throws IOException
     */
    public void getout(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        Teacher_logging teacher_logging = new Teacher_logging();
        teacher_logging.setTeacher_id(teacher.getId());
        teacher_logging.setLogging("退出学习平台");
        teacherService.addLogging(teacher_logging);
        logger.info(teacher.getTeachername() + "老师退出平台了");

        session.setAttribute("teacher",null);
        session.setAttribute("course",null);

        resp.getWriter().write("success");
    }

    /**
     * 获得教师的历史操作记录
     * @param req
     * @param resp
     * @throws SQLException
     * @throws IOException
     */
    public void queryLoggingByid(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        //获取当前学生对象
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        List<Teacher_logging> list = teacherService.queryLoggingById(teacher.getId());
        String jsonString = JSON.toJSONString(list);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 帮助学生报名课程
     * @param req
     * @param resp
     * @throws IOException
     * @throws SQLException
     */
    public void helpAddStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        //获取当前课程对象
        HttpSession session = req.getSession();
        Course course = (Course) session.getAttribute("course");
//        Teacher teacher  = (Teacher) session.getAttribute("teacher");

        //防止乱码
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        Student student = studentService.queryByName(s);

        if(student == null){
            //没有该学生
            resp.getWriter().write("null");
            return;
        }else {
            Student_course student_course = new Student_course();
            student_course.setCourse_id(course.getId());
            student_course.setStudent_id(student.getId());
            studentService.addStudent_course(course,student_course);

            courseService.addRegisternumber(course);

            Teacher_logging teacher_logging = new Teacher_logging();
            teacher_logging.setTeacher_id(course.getTeacher_id());
            teacher_logging.setLogging("帮助学生" + s + "报名了" + course.getCoursename() + "该课程");
            teacherService.addLogging(teacher_logging);

            logger.info(teacherService.queryByid(course.getTeacher_id()).getTeachername() + "帮" + s + "报名了" + course.getCoursename() + "该课程");

            resp.getWriter().write("success");
        }



    }

}
