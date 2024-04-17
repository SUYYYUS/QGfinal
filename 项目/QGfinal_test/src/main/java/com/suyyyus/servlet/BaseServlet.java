package com.suyyyus.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    //根据最后一段路径进行分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取路径
        String requestURI = req.getRequestURI(); // .../User/selectAll

        //截取方法名
        int index = requestURI.lastIndexOf("/");
        String methodName = requestURI.substring(index+1); // /selectAll? selectAll?

        //2.获取方法
        //获取UserServlet class对象
        //谁调用我（this所在的方法），我（this）代表谁

        Class<? extends BaseServlet> Cls = this.getClass();

        //获取Method对象
        try {
            Method method = Cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
