package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.dao.CourseDao;
import com.suyyyus.dao.StudentDao;
import com.suyyyus.dao.Student_courseDao;
import com.suyyyus.dao.impl.CourseDaoImpl;
import com.suyyyus.dao.impl.StudentDaoImpl;
import com.suyyyus.dao.impl.Student_courseDaoImpl;
import com.suyyyus.pojo.Course;
import com.suyyyus.pojo.Student;
import com.suyyyus.pojo.Student_course;
import com.suyyyus.pojo.Teacher;
import com.suyyyus.service.CourseService;
import com.suyyyus.service.StudentService;
import com.suyyyus.service.Student_courseService;
import com.suyyyus.service.impl.CourseServiceImpl;
import com.suyyyus.service.impl.StudentServiceImpl;
import com.suyyyus.service.impl.Student_courseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Student/*")
public class StudentServlet extends BaseServlet{

    StudentDao studentDao = new StudentDaoImpl();
    StudentService studentService = new StudentServiceImpl();

    CourseDao courseDao = new CourseDaoImpl();
    CourseService courseService = new CourseServiceImpl();

    Student_courseService student_courseService = new Student_courseServiceImpl();
    Student_courseDao student_courseDao = new Student_courseDaoImpl();

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
            //判断学号与密码是否正确
            boolean b = studentService.StudentLogin(student.getStudentid(), student.getPassword());

            //都正确才能成功登录
            if(b){
                Student student1 = studentService.queryByStudentid(student.getStudentid());

                /*//1. 创建Cookie对象
                Cookie c_username = new Cookie("username",user.getUsername());
                Cookie c_password = new Cookie("password",user.getPassword());
                // 设置Cookie的存活时间,7天
                c_username.setMaxAge( 60 * 60 * 24 * 7);
                c_password.setMaxAge( 60 * 60 * 24 * 7);
                //2. 发送
                resp.addCookie(c_username);
                resp.addCookie(c_password);*/

                //生成令牌
//                Map<String, Object> claims = new HashMap<>();
//                claims.put("username",user1.getUsername());
//                claims.put("password",user1.getPassword());
//                String jwt = JwtUtils.generateJwt(claims);

//                resp.setHeader("Authorization", "Bearer " + jwt);
//                Cookie jwtCookie = new Cookie("jwt", jwt);
//                jwtCookie.setHttpOnly(true);
//                jwtCookie.setMaxAge(60 * 60 * 24 * 7); // 设置JWT的有效期，例如7天
//                resp.addCookie(jwtCookie);

//                resp.setHeader("Authorization", "Bearer " + jwt);

                session.setAttribute("student", student1);

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
        System.out.println(1);
        Student student = JSON.parseObject(s, Student.class);
        System.out.println(2);
        System.out.println(3);

            studentService.addStudent(student);
            System.out.println(4);
            resp.getWriter().write("success");
            System.out.println(5);

        //boolean b = userService.checkUsername(user.getUsername());


//        if(!b){
//            resp.getWriter().write("success");
//        }

        //System.out.println("User add---------------");
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
//        Teacher teacher = JSON.parseObject(s,Teacher.class);
        Student student = JSON.parseObject(s, Student.class);
        studentService.updateStudent(student);

//        Teacher teacher1 = teacherService.queryByTeacherid(teacher.getTeacherid());
        Student student1 = studentService.queryByStudentid(student.getStudentid());
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
            Course course1 = courseDao.queryByCourse_id(course.getId());
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

}
