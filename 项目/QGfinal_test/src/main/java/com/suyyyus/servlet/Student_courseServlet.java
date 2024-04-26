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
import com.suyyyus.service.CourseService;
import com.suyyyus.service.StudentService;
import com.suyyyus.service.Student_courseService;
import com.suyyyus.service.impl.CourseServiceImpl;
import com.suyyyus.service.impl.StudentServiceImpl;
import com.suyyyus.service.impl.Student_courseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Student_course/*")
public class Student_courseServlet extends BaseServlet{

    private static final Logger logger =  LoggerFactory.getLogger(Student_courseServlet.class);

    StudentService studentService = new StudentServiceImpl();

    CourseService courseService = new CourseServiceImpl();

    Student_courseService student_courseService = new Student_courseServiceImpl();

    /**
     * 展示学生选择的课程
     * @param req
     * @param resp
     * @throws Exception
     */
    public void showStudentCourse(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 从seession中拿出登录成功的用户信息
        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("student");
        //获取当前学生的id
        int student_id = student.getId();
        //查询报名的课程
        List<Student_course> student_courses = student_courseService.queryCourseByStudent_id(student_id);
        //存放所选的课程的id
        int course_id[] = new int[student_courses.size()];
        int i = 0;
        for (Student_course studentCourse : student_courses) {
            course_id[i] = studentCourse.getCourse_id();
            i++;
        }
        //创建新集合
        List<Course> courseList = new ArrayList<>();
        //遍历id转化为课程集合
        for (int i1 = 0; i1 < course_id.length; i1++) {
            Course course = courseService.queryByCourse_id(course_id[i1]);
            courseList.add(course);
        }
        //转化为JSON格式
        String jsonString = JSON.toJSONString(courseList);
        //上传数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);

    }

    /**
     * 展示选择这个课程的学生
     * @param req
     * @param resp
     * @throws Exception
     */
    public void showRegisterStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 从seession中拿出登录成功的用户信息
        HttpSession session = req.getSession();
        Course course = (Course) session.getAttribute("course");
        //获取当前学生的id
        int course_id = course.getId();
        //查询报名的课程
        List<Student_course> student_courses = student_courseService.queryStudentByCourse_id(course_id);
        //存放学生的id
        int student_id[] = new int[student_courses.size()];
        int i = 0;
        for (Student_course studentCourse : student_courses) {
            student_id[i] = studentCourse.getStudent_id();
            i++;
        }
        System.out.println("=");
        //创建新集合
        List<Student> courseList = new ArrayList<>();
        //遍历id转化为课程集合
        for (int i1 = 0; i1 < student_id.length; i1++) {
            Student student = studentService.queryById(student_id[i1]);
            courseList.add(student);
        }
        //操作完成
        System.out.println("==");
        //JSON格式
        String jsonString = JSON.toJSONString(courseList);
        //上传数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);

    }

}
