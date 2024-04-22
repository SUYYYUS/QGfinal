package com.suyyyus.service;

import com.suyyyus.pojo.Admin;

import java.sql.SQLException;

public interface AdminService {

    //添加管理员
    public void addAdmin(Admin admin) throws SQLException;

    //管理员登录
    public boolean AdminLogin(String account, String password) throws SQLException;

    public Admin queryByAccount(String account) throws SQLException;
}
