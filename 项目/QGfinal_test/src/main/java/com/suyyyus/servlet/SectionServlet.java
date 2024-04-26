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

    SectionService sectionService = new SectionServiceImpl();

    CourseService courseService = new CourseServiceImpl();

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
        //转化格式
        Section section = JSON.parseObject(s, Section.class);
        //设置它所属课程id
        section.setCourse_id(course.getId());
        //添加成功
        sectionService.addSection(section);
        //日志记录
        Teacher_logging teacher_logging = new Teacher_logging();
        teacher_logging.setTeacher_id(course.getTeacher_id());
        teacher_logging.setLogging(course.getCoursename() + "课程中新增加了章节" + section.getSectionname());
        teacherService.addLogging(teacher_logging);

        logger.info(course.getCoursename() + "课程中新增加了章节" + section.getSectionname());
        //添加章节数
        courseService.addSection_number(course);
        //获取更新数据后的课程
        Course course1 = courseService.queryByCourse_id(course.getId());
        //储存
        session.setAttribute("course",course1);
        //提示操作成功
        resp.getWriter().write("success");
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
        Course course = (Course) session.getAttribute("course");
        //获得当前课程的所有章节
        List<Section> sections = sectionService.queryAllSectionByCourse_id(course.getId());
        //2.转为JSON
        String jsonString = JSON.toJSONString(sections);
        //3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
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
        //转换数据类型
        int id = Integer.parseInt(_id);
        //获取该章节对象
        Section section = sectionService.queryById(id);
        //保存章节
        session.setAttribute("section",section);

        System.out.println(section.getId());
        //提示成功
        resp.getWriter().write("success");
    }
}
