package com.suyyyus.dao;

import com.suyyyus.pojo.Admin;

import java.sql.SQLException;

public interface AdminDao {
    //添加好管理员
    public void addAdmin(Admin admin) throws SQLException;

    //管理员登录
    public boolean AdminLogin(String account, String password) throws SQLException;

    //查询该管理员
    public Admin queryByAccount(String account) throws SQLException;


}
