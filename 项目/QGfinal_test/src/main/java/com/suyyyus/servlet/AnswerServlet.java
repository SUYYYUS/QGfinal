package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.pojo.*;
import com.suyyyus.service.Student_answerService;
import com.suyyyus.service.Student_studyService;
import com.suyyyus.service.impl.Student_answerServiceImpl;
import com.suyyyus.service.impl.Student_studyServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;

@WebServlet("/Answer/*")
public class AnswerServlet extends BaseServlet{

    Student_answerService student_answerService = new Student_answerServiceImpl();
    Student_studyService student_studyService = new Student_studyServiceImpl();

    /**
     * 用于添加答题记录
     * @param req
     * @param resp
     * @throws Exception
     */
    public void addAnswerRecord(HttpServletRequest req, HttpServletResponse resp) throws Exception{

        req.setCharacterEncoding("UTF-8");
        // 从seession中拿出信息
        HttpSession session = req.getSession();
        Course course = (Course)session.getAttribute("course");
        Student student = (Student)session.getAttribute("student");
        Question question = (Question) session.getAttribute("question");

        //获取答案
        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        if(s.length() > 20)
        {
            System.out.println("答案长度过大");
            resp.getWriter().write("toolong");
            return ;
        }
//        Answer answer= JSON.parseObject(s, Answer.class);
        Answer answer  = new Answer();
        answer.setAnswer(s);
        //对比答案
        if(s.equals(question.getAnswer())){
            answer.setScore(question.getScore());
        }else {
            answer.setScore(0);
        }

        answer.setStudent_id(student.getId());
        answer.setQuestion_id(question.getId());
        answer.setCourse_id(course.getId());

        student_answerService.addAnswerRecord(answer);

        //存储答题记录
        Student_study student_study = student_studyService.queryStudentRecordByIds(student.getId(), course.getId());
        if(student_study == null){
            Student_study student_study1 = new Student_study();
            student_study1.setStudent_id(student.getId());
            student_study1.setCourse_id(course.getId());
            student_studyService.addStudent_studyRecord(answer, student_study1);
            System.out.println(123456);
        }else if(student_study != null){
            student_studyService.updateStudentRecord(answer, student_study);
            System.out.println(456789);
        }


        System.out.println(answer);

        if(s.equals(question.getAnswer())){
            resp.getWriter().write("right");
        }else {
            resp.getWriter().write("wrong");
        }


    }




}
