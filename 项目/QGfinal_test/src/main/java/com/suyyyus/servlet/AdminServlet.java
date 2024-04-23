package com.suyyyus.servlet;


import com.alibaba.fastjson.JSON;
import com.suyyyus.dao.AdminDao;
import com.suyyyus.dao.impl.AdminDaoImpl;
import com.suyyyus.pojo.Admin;
import com.suyyyus.pojo.Student;
import com.suyyyus.service.AdminService;
import com.suyyyus.service.impl.AdminServiceImpl;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Admin/*")
public class AdminServlet extends BaseServlet{

    private static final Logger logger =  LoggerFactory.getLogger(AdminServlet.class);

    AdminDao adminDao = new AdminDaoImpl();
    AdminService adminService = new AdminServiceImpl();

    /**
     * 管理员登录
     * @param req
     * @param resp
     * @throws Exception
     */
    public void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        // 获取session对象，存储Student
        HttpSession session = req.getSession();

        Admin admin = JSON.parseObject(s,Admin.class);
        if(admin.getAccount().isEmpty() || admin.getPassword().isEmpty()){
            resp.getWriter().write("null");
            return;
        }else {
            //设置编码格式
            resp.setContentType("text/html;charset=utf-8");
            boolean validStudentid = Validator.isValidStudentid(admin.getAccount());
            if(validStudentid == false){
                System.out.println("账号应为10位数字");
                return;
            }
            boolean validPassword = Validator.isValidStuPassword(admin.getPassword());
            if(validPassword == false){
                System.out.println("密码至少为6位字符");
                return;
            }
            //判断学号与密码是否正确
            boolean b = adminService.AdminLogin(admin.getAccount(), admin.getPassword());

            //都正确才能成功登录
            if(b){
                Admin admin1 = adminService.queryByAccount(admin.getAccount());


                session.setAttribute("admin", admin1);

                logger.info("管理员成功登录平台");
                resp.getWriter().write("success");
                System.out.println("adminlogin_success");
            }else {
                resp.getWriter().write("failed");
                System.out.println("adminlogin_failed");
            }
            resp.setStatus(HttpServletResponse.SC_OK);
            System.out.println("Response status code: " + resp.getStatus());
        }
    }

    /**
     * 获取管理员信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getSelfInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从seession中拿出登录成功的用户信息
        HttpSession session = req.getSession();
        Object admin = session.getAttribute("admin");
//        System.out.println(user);
        //  JSON数据化
        String jsonString = JSON.toJSONString(admin);
//        System.out.println(jsonString);
        // 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }


}
