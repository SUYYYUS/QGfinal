package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.pojo.*;
import com.suyyyus.service.CourseService;
import com.suyyyus.service.QuestionService;
import com.suyyyus.service.SectionService;
import com.suyyyus.service.TeacherService;
import com.suyyyus.service.impl.CourseServiceImpl;
import com.suyyyus.service.impl.QuestionServiceImpl;
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

@WebServlet("/Question/*")
public class QuestionServlet extends BaseServlet{

    private static final Logger logger =  LoggerFactory.getLogger(QuestionServlet.class);

    QuestionService questionService = new QuestionServiceImpl();

    SectionService sectionService = new SectionServiceImpl();

    CourseService courseService = new CourseServiceImpl();

    TeacherService teacherService = new TeacherServiceImpl();

    /**
     * 用于添加题目
     * @param req
     * @param resp
     * @throws Exception
     */
    public void addQuestion(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        // 从seession中拿出登录成功的教师信息
        HttpSession session = req.getSession();
        Section section = (Section) session.getAttribute("section");
        //获取当前课程信息
        Course course = (Course) session.getAttribute("course");
        //获取题目内容
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        //转化格式
        Question question = JSON.parseObject(s, Question.class);
        //设置这个提问的数据
        question.setCourse_section_id(section.getId());
        question.setCourse_id(course.getId());
        //添加问题
        questionService.addQuestion(question);
        //日志记录
        Teacher_logging teacher_logging = new Teacher_logging();
        teacher_logging.setTeacher_id(course.getTeacher_id());
        teacher_logging.setLogging("添加了题目在" + section.getSectionname() + "这一章节中");
        teacherService.addLogging(teacher_logging);

        logger.info("id为" + course.getTeacher_id() + "的老师在" + section.getSectionname() + "章节中添加了题目");
        //提示添加成功
        resp.getWriter().write("addquestion_success");
    }

    /**
     * 展示课程章节中的题目
     * @param req
     * @param resp
     * @throws Exception
     */
    public void showQuestion(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        // 从seession中拿出信息
        HttpSession session = req.getSession();
        Course course = (Course) session.getAttribute("course");
        Section section = (Section) session.getAttribute("section");
        //获取该课程章节中的题目集合
        List<Question> questions = questionService.queryAllQuestionByTwoid(section.getId(), course.getId());
        //JSON格式
        String jsonString = JSON.toJSONString(questions);
        //上传数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }


    /**
     * 通过问题id查询问题并且保存
     * @param req
     * @param resp
     * @throws Exception
     */
    public void queryByIdAndSession(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取session对象，储存课程
        HttpSession session = req.getSession();
        BufferedReader reader = req.getReader();
        //获取要查询的提问的id
        String _id = reader.readLine();
        //转为整数类型
        int id = Integer.parseInt(_id);
        //获取对象
        Question question = questionService.queryQuesionById(id);
        //保存当前提问
        session.setAttribute("question",question);
        System.out.println(question.getId());
        //提示成功
        resp.getWriter().write("success");
    }

    /**
     * 通过问题id查询问题并且保存
     * @param req
     * @param resp
     * @throws Exception
     */
    public void queryById(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取session对象，储存课程
        HttpSession session = req.getSession();
        BufferedReader reader = req.getReader();
        //获取id
        String _id = reader.readLine();
        int id = Integer.parseInt(_id);
        //获取问题对象
        Question question = questionService.queryQuesionById(id);
        //转化格式
        String jsonString = JSON.toJSONString(question);
        //上传数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
}
