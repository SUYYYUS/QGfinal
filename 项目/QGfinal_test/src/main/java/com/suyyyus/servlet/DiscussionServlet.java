package com.suyyyus.servlet;

import com.alibaba.fastjson.JSON;
import com.suyyyus.dao.DiscussionDao;
import com.suyyyus.dao.impl.DiscussionDaoImpl;
import com.suyyyus.pojo.Discussion;
import com.suyyyus.pojo.PageBean;
import com.suyyyus.pojo.Student;
import com.suyyyus.service.DiscussionServcie;
import com.suyyyus.service.impl.DiscussionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author CHEN SHUYU
 */
@WebServlet("/Discussion/*")
public class DiscussionServlet extends BaseServlet{

    private static final Logger logger =  LoggerFactory.getLogger(DiscussionServlet.class);

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
        //获取讨论区的所有留言
        PageBean<Discussion> pageBean = discussionService.selectDiscussionByPage(currentPage, pageSize);
        //2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
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
        //获取要删除的对象的所有id
        int[] ids = JSON.parseObject(params, int[].class);
        //进行删除
        discussionService.deleteDiscussions(ids);
        //日志记录
        for (int i = 0; i < ids.length; i++) {
            logger.info("id为" + ids[i] + "的留言被删除");
        }
        //提示操作成功
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
        System.out.println(id);
        //进行删除
        discussionService.deleteDiscussion(Integer.parseInt(id));
        //日志记录
        logger.info("id为" + id + "的留言被删除");
        //提示操作成功
        resp.getWriter().write("success");
    }
}
