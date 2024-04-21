package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.dao.DiscussionDao;
import com.suyyyus.dao.TeacherDao;
import com.suyyyus.dao.impl.DiscussionDaoImpl;
import com.suyyyus.dao.impl.TeacherDaoImpl;
import com.suyyyus.pojo.Discussion;
import com.suyyyus.pojo.Student;
import com.suyyyus.pojo.Teacher;
import com.suyyyus.service.DiscussionServcie;
import com.suyyyus.service.TeacherService;
import com.suyyyus.service.impl.DiscussionServiceImpl;
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

                resp.getWriter().write("success");
                logger.info("有教师成功登录");
            }else {
                resp.getWriter().write("failed");
                logger.info("有教师登录失败");
            }
            resp.setStatus(HttpServletResponse.SC_OK);
            System.out.println(resp.getStatus());
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
        System.out.println(1);

        Teacher teacher = JSON.parseObject(s,Teacher.class);
        System.out.println(2);


        teacherService.addTeacher(teacher);
        System.out.println(4);
        resp.getWriter().write("success");
        System.out.println(5);

        //boolean b = userService.checkUsername(user.getUsername());

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

        //添加回复
        discussionServcie.TeacherReply(discussion);


        resp.getWriter().write("success");

    }

}
