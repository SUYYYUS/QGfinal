package com.suyyyus.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 会话创建时不需要做任何操作
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 会话销毁时清除 session 存储的用户信息
        se.getSession().setAttribute("student", null);
        se.getSession().setAttribute("teacher", null);
        se.getSession().setAttribute("course", null);

    }
}
