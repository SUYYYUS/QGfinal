package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.pojo.Course;
import com.suyyyus.pojo.Question;
import com.suyyyus.pojo.Section;
import com.suyyyus.pojo.Teacher;
import com.suyyyus.service.CourseService;
import com.suyyyus.service.QuestionService;
import com.suyyyus.service.SectionService;
import com.suyyyus.service.impl.CourseServiceImpl;
import com.suyyyus.service.impl.QuestionServiceImpl;
import com.suyyyus.service.impl.SectionServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.List;

@WebServlet("/Question/*")
public class QuestionServlet extends BaseServlet{

    QuestionService questionService = new QuestionServiceImpl();

    SectionService sectionService = new SectionServiceImpl();

    CourseService courseService = new CourseServiceImpl();
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

        Course course = (Course) session.getAttribute("course");
        System.out.println(section);
        System.out.println("==========================");
        //获取题目内容
        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        Question question = JSON.parseObject(s, Question.class);

        System.out.println(question);

        question.setCourse_section_id(section.getId());
//        question.setCourse_id(section.getCourse_id());
        question.setCourse_id(course.getId());

        questionService.addQuestion(question);


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
//
        Course course = (Course) session.getAttribute("course");
        Section section = (Section) session.getAttribute("section");

        List<Question> questions = questionService.queryAllQuestionByTwoid(section.getId(), course.getId());

        String jsonString = JSON.toJSONString(questions);

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
        String _id = reader.readLine();
        int id = Integer.parseInt(_id);

//        Course course = courseService.queryByCourse_id(id);

//        Section section = sectionService.queryById(id);

        Question question = questionService.queryQuesionById(id);
        session.setAttribute("question",question);

        System.out.println("111111111111111222222222222222");
        System.out.println(question.getId());

        resp.getWriter().write("success");
        //System.out.println("User selectAll----------");
    }





}
