package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.dao.CourseDao;
import com.suyyyus.dao.SectionDao;
import com.suyyyus.dao.impl.CourseDaoImpl;
import com.suyyyus.dao.impl.SectionDaoImpl;
import com.suyyyus.pojo.Course;
import com.suyyyus.pojo.Section;
import com.suyyyus.pojo.Teacher;
import com.suyyyus.pojo.Teacher_logging;
import com.suyyyus.service.CourseService;
import com.suyyyus.service.SectionService;
import com.suyyyus.service.TeacherService;
import com.suyyyus.service.impl.CourseServiceImpl;
import com.suyyyus.service.impl.SectionServiceImpl;
import com.suyyyus.service.impl.TeacherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.List;

@WebServlet("/Section/*")
public class SectionServlet extends BaseServlet{

    private static final Logger logger =  LoggerFactory.getLogger(SectionServlet.class);

    SectionDao sectionDao = new SectionDaoImpl();
    SectionService sectionService = new SectionServiceImpl();

    CourseService courseService = new CourseServiceImpl();
    CourseDao courseDao = new CourseDaoImpl();

    TeacherService teacherService = new TeacherServiceImpl();
    /**
     * 用于添加章节
     * @param req
     * @param resp
     * @throws Exception
     */
    public void addSection(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 从seession中拿出课程信息
        HttpSession session = req.getSession();
        Course course = (Course) session.getAttribute("course");


        //post方法获取数据
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        System.out.println("课程id");

        Section section = JSON.parseObject(s, Section.class);
        System.out.println(course.getId());
        //设置它所属课程id
        section.setCourse_id(course.getId());

        System.out.println("==============");
        //添加成功
        sectionService.addSection(section);

        Teacher_logging teacher_logging = new Teacher_logging();
        teacher_logging.setTeacher_id(course.getTeacher_id());
        teacher_logging.setLogging(course.getCoursename() + "课程中新增加了章节" + section.getSectionname());
        teacherService.addLogging(teacher_logging);

        logger.info(course.getCoursename() + "课程中新增加了章节" + section.getSectionname());
        System.out.println(4);
        //添加章节数
        courseService.addSection_number(course);

        Course course1 = courseService.queryByCourse_id(course.getId());


        //储存
        session.setAttribute("course",course1);
        resp.getWriter().write("success");
        System.out.println(5);

    }

    /**
     * 查询课程id下开设的章节
     * @param req
     * @param resp
     * @throws Exception
     */
    public void selectAllSectionBycourse_id(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // 从seession中拿出登录成功的教师信息
        HttpSession session = req.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("teacher");
        Course course = (Course) session.getAttribute("course");

//        List<Course> courseList = courseService.queryAllCourseByTeacher_id(teacher.getId());

        List<Section> sections = sectionService.queryAllSectionByCourse_id(course.getId());
        //2.转为JSON
        String jsonString = JSON.toJSONString(sections);

        //3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
        //System.out.println("User selectAll----------");
    }

    /**
     * 通过章节id查询章节并且保存
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

//        Course course = courseService.queryByCourse_id(id);

        Section section = sectionService.queryById(id);

        session.setAttribute("section",section);

        System.out.println("111111111111111222222222222222");
        System.out.println(section.getId());

        resp.getWriter().write("success");
        //System.out.println("User selectAll----------");
    }


}
