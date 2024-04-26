package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.pojo.*;
import com.suyyyus.service.StudentService;
import com.suyyyus.service.Student_answerService;
import com.suyyyus.service.Student_studyService;
import com.suyyyus.service.impl.StudentServiceImpl;
import com.suyyyus.service.impl.Student_answerServiceImpl;
import com.suyyyus.service.impl.Student_studyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;

@WebServlet("/Answer/*")
public class AnswerServlet extends BaseServlet{

    private static final Logger logger =  LoggerFactory.getLogger(AnswerServlet.class);

    Student_answerService student_answerService = new Student_answerServiceImpl();

    Student_studyService student_studyService = new Student_studyServiceImpl();

    StudentService studentService =new StudentServiceImpl();
    /**
     * 用于添加答题记录
     * @param req
     * @param resp
     * @throws Exception
     */
    public void addAnswerRecord(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //防止中文乱码
        req.setCharacterEncoding("UTF-8");
        // 从seession中拿出信息
        HttpSession session = req.getSession();
        Course course = (Course)session.getAttribute("course");
        Student student = (Student)session.getAttribute("student");
        Question question = (Question) session.getAttribute("question");
        //获取答案
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        //判断答案是否符合限制
        if(s.length() > 20)
        {
            System.out.println("答案长度过大");
            resp.getWriter().write("toolong");
            return ;
        }
        //构造新答案记录
        Answer answer  = new Answer();
        answer.setAnswer(s);
        //对比答案
        if(s.equals(question.getAnswer())){
            answer.setScore(question.getScore());
        }else {
            answer.setScore(0);
        }
        //设置其内容
        answer.setStudent_id(student.getId());
        answer.setQuestion_id(question.getId());
        answer.setCourse_id(course.getId());
        //添加答题记录
        student_answerService.addAnswerRecord(answer);
        //日志记录
        Student_logging student_logging = new Student_logging();
        student_logging.setStudent_id(student.getId());
        student_logging.setLogging("作答了" + question.getContent());
        studentService.addLogging(student_logging);
        logger.info(student.getStudentname() + "作答了" + question.getContent());

        //存储答题记录
        Student_study student_study = student_studyService.queryStudentRecordByIds(student.getId(), course.getId());
        if(student_study == null){
            Student_study student_study1 = new Student_study();
            student_study1.setStudent_id(student.getId());
            student_study1.setCourse_id(course.getId());
            student_studyService.addStudent_studyRecord(answer, student_study1);

        }else if(student_study != null){
            student_studyService.updateStudentRecord(answer, student_study);
        }

        System.out.println(answer);
        //判断答题是否正确
        if(s.equals(question.getAnswer())){
            resp.getWriter().write("right");
        }else {
            resp.getWriter().write("wrong");
        }
    }
}
