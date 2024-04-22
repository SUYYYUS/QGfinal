package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.dao.DiscussionDao;
import com.suyyyus.dao.impl.DiscussionDaoImpl;
import com.suyyyus.pojo.Discussion;
import com.suyyyus.pojo.PageBean;
import com.suyyyus.pojo.Student;
import com.suyyyus.service.DiscussionServcie;
import com.suyyyus.service.impl.DiscussionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/Discussion/*")
public class DiscussionServlet extends BaseServlet{

    DiscussionDao discussionDao = new DiscussionDaoImpl();
    DiscussionServcie discussionService = new DiscussionServiceImpl();

    /**
     * 分页查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.调用查询
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        PageBean<Discussion> pageBean = discussionService.selectDiscussionByPage(currentPage, pageSize);

        //2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3.写数据

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
        //System.out.println("User selectAll----------");
    }

    /**
     * 批量删除评论
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //post方法
        BufferedReader reader = req.getReader();

        String params = reader.readLine();

        int[] ids = JSON.parseObject(params, int[].class);
        discussionService.deleteDiscussions(ids);

        resp.getWriter().write("success");
    }

    /**
     * 单个删除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //post方法
        BufferedReader reader = req.getReader();

        String id = reader.readLine();

        discussionService.deleteDiscussion(Integer.parseInt(id));

        resp.getWriter().write("success");
    }


}
