package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.dao.CourseDao;
import com.suyyyus.dao.DiscussionDao;
import com.suyyyus.dao.StudentDao;
import com.suyyyus.dao.Student_courseDao;
import com.suyyyus.dao.impl.CourseDaoImpl;
import com.suyyyus.dao.impl.DiscussionDaoImpl;
import com.suyyyus.dao.impl.StudentDaoImpl;
import com.suyyyus.dao.impl.Student_courseDaoImpl;
import com.suyyyus.pojo.*;
import com.suyyyus.service.CourseService;
import com.suyyyus.service.DiscussionServcie;
import com.suyyyus.service.StudentService;
import com.suyyyus.service.Student_courseService;
import com.suyyyus.service.impl.CourseServiceImpl;
import com.suyyyus.service.impl.DiscussionServiceImpl;
import com.suyyyus.service.impl.StudentServiceImpl;
import com.suyyyus.service.impl.Student_courseServiceImpl;
import com.suyyyus.utils.JWTUtil;
import com.suyyyus.utils.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Student/*")
public class StudentServlet extends BaseServlet{

    private static final Logger logger =  LoggerFactory.getLogger(StudentServlet.class);

//    StudentDao studentDao = new StudentDaoImpl();
    StudentService studentService = new StudentServiceImpl();

//    CourseDao courseDao = new CourseDaoImpl();
    CourseService courseService = new CourseServiceImpl();

    Student_courseService student_courseService = new Student_courseServiceImpl();
//    Student_courseDao student_courseDao = new Student_courseDaoImpl();

//    DiscussionDao discussionDao = new DiscussionDaoImpl();
    DiscussionServcie discussionServcie = new DiscussionServiceImpl();


    /**
     * 学生登录
     * @param req
     * @param resp
     * @throws Exception
     */
    public void studentLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        // 获取session对象，存储Student
        HttpSession session = req.getSession();

        Student student = JSON.parseObject(s,Student.class);
        if(student.getStudentid().isEmpty() || student.getPassword().isEmpty()){
            resp.getWriter().write("null");
            return;
        }else {
            //设置编码格式
            resp.setContentType("text/html;charset=utf-8");
            boolean validStudentid = Validator.isValidStudentid(student.getStudentid());
            if(validStudentid == false){
                System.out.println("学好应为10位数字");
                return;
            }
            boolean validPassword = Validator.isValidStuPassword(student.getPassword());
            if(validPassword == false){
                System.out.println("密码至少为6位字符");
                return;
            }
            //判断学号与密码是否正确
            boolean b = studentService.StudentLogin(student.getStudentid(), student.getPassword());

            //都正确才能成功登录
            if(b){
                Student student1 = studentService.queryByStudentid(student.getStudentid());

                if(student.getRemember() == 1){
                    //1. 创建Cookie对象
                Cookie studentname = new Cookie("student",student1.getStudentname());
                Cookie password = new Cookie("password",student1.getPassword());
                // 设置Cookie的存活时间,7天
                studentname.setMaxAge( 60 * 60 * 24 * 7);
                password.setMaxAge( 60 * 60 * 24 * 7);
                //2. 发送
                resp.addCookie(studentname);
                resp.addCookie(password);
                }


                //生成令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put("studentname",student.getStudentname());
                claims.put("password",student.getPassword());
                String jwt = JWTUtil.generateJwt(claims);

                resp.setHeader("Authorization", "Bearer " + jwt);
                Cookie jwtCookie = new Cookie("jwt", jwt);
                jwtCookie.setHttpOnly(true);
                jwtCookie.setMaxAge(60 * 60 * 24 * 7); // 设置JWT的有效期，例如7天
                resp.addCookie(jwtCookie);

                resp.setHeader("Authorization", "Bearer " + jwt);

                session.setAttribute("student", student1);
                req.getSession().setMaxInactiveInterval(30 * 60); //会话过期时间为30分钟

                //保存日志
                Student_logging student_logging = new Student_logging();
                student_logging.setStudent_id(student1.getId());
                student_logging.setLogging("成功登录进入平台");
                studentService.addLogging(student_logging);
                logger.info(student1.getStudentname() + "同学成功登录平台");
                resp.getWriter().write("success");
                System.out.println("success");
            }else {
                resp.getWriter().write("failed");
                System.out.println("failed");
            }
            resp.setStatus(HttpServletResponse.SC_OK);
            System.out.println("Response status code: " + resp.getStatus());
        }
    }


    /**
     * 用于学生注册
     * @param req
     * @param resp
     * @throws Exception
     */
    public void studentSignup(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //post方法获取数据
        BufferedReader reader = req.getReader();
        String s = reader.readLine(); //???? s = null ????

        Student student = JSON.parseObject(s, Student.class);

        System.out.println(student);
            studentService.addStudent(student);

        Student_logging student_logging = new Student_logging();
        student_logging.setStudent_id(student.getId());
        student_logging.setLogging("成功注册开通账号");
        studentService.addLogging(student_logging);
        logger.info(student.getStudentname() + "同学成功注册");

            resp.getWriter().write("success");

    }


    /**
     * 学生登录成功后把个人信息展示出来
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getSelfInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从seession中拿出登录成功的用户信息
        HttpSession session = req.getSession();
        Object student = session.getAttribute("student");
//        System.out.println(user);
        //  JSON数据化
        String jsonString = JSON.toJSONString(student);
//        System.out.println(jsonString);
        // 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 修改学生信息
     * @param req
     * @param resp
     * @throws Exception
     */
    public void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //post方法获取数据
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        //获取session对象，储存Teacher
        HttpSession session = req.getSession();

        Student student = JSON.parseObject(s, Student.class);
        studentService.updateStudent(student);


        Student student1 = studentService.queryByStudentid(student.getStudentid());

        logger.info(student1.getStudentname() + "同学修改了自己的个人信息");
        session.setAttribute("student", student1);

        resp.getWriter().write("success");
    }

    /**
     * 报名课程
     * @param req
     * @param resp
     * @throws Exception
     */
    public void addStudent_course(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        HttpSession session = req.getSession();

        //获取目前的操作学生
        Student student = (Student) session.getAttribute("student");

        System.out.println(student);
        //post方法获取数据
        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        Course course = JSON.parseObject(s, Course.class);

        System.out.println(course);

        Student_course student_course = new Student_course();
        student_course.setStudent_id(student.getId());
        student_course.setCourse_id(course.getId());

        int i = studentService.addStudent_course(course, student_course);

        System.out.println(i);
        if(i == 1){
            //人满了
            resp.getWriter().write("full");
        }else if(i == 2){
            //超时了
            resp.getWriter().write("overtime");
        }else if(i == 3){
            //报名成功
            courseService.addRegisternumber(course);
            //先储存课程对象
            Course course1 = courseService.queryByCourse_id(course.getId());

            Student_logging student_logging = new Student_logging();
            student_logging.setStudent_id(student.getId());
            student_logging.setLogging("报名了课程：" + course1.getCoursename());
            studentService.addLogging(student_logging);

            logger.info(student.getStudentname() + "同学成功报名了" + course1.getCoursename() + "这门课程");

            session.setAttribute("course", course1);
            resp.getWriter().write("success");
        }


    }

    /**
     * 通过学生id查询课程并且保存
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

        Student student = studentService.queryById(id);
        System.out.println(student);

        session.setAttribute("student",student);

        System.out.println("111111111111111222222222222222");
        System.out.println(student.getId());

        resp.getWriter().write("success");
        //System.out.println("User selectAll----------");
    }

    /**
     * 学生发出提问
     * @param req
     * @param resp
     * @throws IOException
     * @throws SQLException
     */
    public void sendQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        //获取session对象，获得学生id课程ID
        HttpSession session = req.getSession();
        //获取学生和课程
        Student student = (Student) session.getAttribute("student");
        Course course = (Course) session.getAttribute("course");

        //防治中文乱码
        req.setCharacterEncoding("UTF-8");
        //获取学生提问内容
        BufferedReader reader = req.getReader();
        String question = reader.readLine();

        if(question.length() > 100){
            System.out.println("问题字数超出");
            resp.getWriter().write("toolong");
            return ;
        }

        Discussion discussion = new Discussion();

        discussion.setQuestion(question);
        discussion.setStudent_id(student.getId());
        discussion.setCourse_id(course.getId());
        discussion.setTeacher_id(course.getTeacher_id());

        Student_logging student_logging = new Student_logging();
        student_logging.setStudent_id(student.getId());
        student_logging.setLogging("对" + course.getCoursename() + "这门课程发出了提问");
        studentService.addLogging(student_logging);

        logger.info(student.getStudentname() + "同学对" + course.getCoursename() + "这门课程发出了提问");

        discussionServcie.addDiscussion(discussion);

        resp.getWriter().write("success");
    }


    /**
     * 获取学生的提问记录
     * @param req
     * @param resp
     * @throws SQLException
     * @throws IOException
     */
    public void getMyQuestionRecord(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        HttpSession session = req.getSession();

        //获取当前学生对象
        Student student = (Student) session.getAttribute("student");

        //获取数据
        List<Discussion> discussionList = discussionServcie.queryAllByStudent_id(student.getId());

        //转化为JSON
        String jsonString = JSON.toJSONString(discussionList);

        // 写数据
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

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        PageBean<Student> pageBean = studentService.selectStudentByPage(currentPage, pageSize);

        //2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3.写数据

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
        //System.out.println("User selectAll----------");
    }

    /**
     * 批量删除学生
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
        studentService.deleteStudents(ids);

        logger.info("管理员对学生进行了删除操作");

        resp.getWriter().write("success");
    }

    /**
     * 重置学生密码
     * @param req
     * @param resp
     * @throws IOException
     * @throws SQLException
     */
    public void resetPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        //获取对象
        BufferedReader reader = req.getReader();

        String s = reader.readLine();

        Student student = JSON.parseObject(s, Student.class);

        System.out.println(student);

        boolean b = studentService.resetPassword(student);

        if(b){
            Student_logging student_logging = new Student_logging();
            student_logging.setStudent_id(student.getId());
            student_logging.setLogging("重置了自己的密码");
            studentService.addLogging(student_logging);

            logger.info("管理员重置了" + student.getStudentname() + "同学的密码");
            resp.getWriter().write("resetsuccess");
        }
    }

    /**
     * 通过年级查询学生
     * @param req
     * @param resp
     * @throws SQLException
     * @throws IOException
     */
    public void queryByGrade(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        req.setCharacterEncoding("UTF-8");

        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        System.out.println(s);
        List<Student> studentList = studentService.queryByGrade(s);

        String jsonString = JSON.toJSONString(studentList);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 学生退出登录
     * @param req
     * @param resp
     * @throws IOException
     */
    public void getout(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        //获取当前学生对象
        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("student");

        Student_logging student_logging = new Student_logging();
        student_logging.setStudent_id(student.getId());
        student_logging.setLogging("退出在线学习平台");
        studentService.addLogging(student_logging);

        logger.info(student.getStudentname() + "退出了在线学习平台");

        session.setAttribute("student",null);
        session.setAttribute("course",null);

        resp.getWriter().write("success");
    }

    /**
     * 获得学生的历史学习记录
     * @param req
     * @param resp
     * @throws SQLException
     * @throws IOException
     */
    public void queryLoggingByid(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        //获取当前学生对象
        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("student");

        List<Student_logging> student_loggings = studentService.queryLoggingById(student.getId());

        String jsonString = JSON.toJSONString(student_loggings);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

}
