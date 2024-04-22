package com.suyyyus.service.impl;

import com.suyyyus.dao.AdminDao;
import com.suyyyus.dao.impl.AdminDaoImpl;
import com.suyyyus.pojo.Admin;
import com.suyyyus.pojo.Student;
import com.suyyyus.service.AdminService;
import com.suyyyus.utils.MD5Util;
import com.suyyyus.utils.MyConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {

    AdminDao adminDao = new AdminDaoImpl();

    static MyConnectionPool myConnectionPool = new MyConnectionPool();
    /**
     * 添加管理员
     * @param admin
     * @throws SQLException
     */
    @Override
    public void addAdmin(Admin admin) throws SQLException {
        String password = admin.getPassword();
        //密码加密
        String saltPassword = MD5Util.generateSaltPassword(password);
        admin.setPassword(saltPassword);
        adminDao.addAdmin(admin);
    }

    /**
     * 管理员登录
     * @param account
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public boolean AdminLogin(String account, String password) throws SQLException {
        boolean b = adminDao.AdminLogin(account, password);

        return b;
    }


    /**
     * 通过账号查找管理员
     * @param account
     * @return
     * @throws SQLException
     */
    @Override
    public Admin queryByAccount(String account) throws SQLException {
        Admin admin = adminDao.queryByAccount(account);

        return admin;
    }

}
