package com.suyyyus.dao.impl;

import com.suyyyus.dao.AdminDao;
import com.suyyyus.pojo.Admin;
import com.suyyyus.utils.CRUDUtils;
import com.suyyyus.utils.MyConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {

    static MyConnectionPool myConnectionPool = new MyConnectionPool();
    /**
     * 添加管理员
     * @param admin
     */
    @Override
    public void addAdmin(Admin admin) throws SQLException {
        String sql = "insert into tb_admin (adminname, account, password) values (?,?,?)";

        CRUDUtils.ZengShanGai(sql,admin.getAdminname(),admin.getAccount(),admin.getPassword());
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
        String sql = "select * from tb_admin where account = ?";

        boolean login = CRUDUtils.Login(sql, account, password);

        return login;
    }

    /**
     * 通过账号查找管理员
     * @param account
     * @return
     * @throws SQLException
     */
    @Override
    public Admin queryByAccount(String account) throws SQLException {
        String sql = "select * from tb_admin where account = ?";
        //获取连接池的连接
        Connection connection = myConnectionPool.getConnection();
        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, account);
        ResultSet resultSet = preparedStatement.executeQuery();

        Admin admin = new Admin();

        if(resultSet.next()){
            admin.setId(resultSet.getInt("id"));
            admin.setAdminname(resultSet.getString("adminname"));
            admin.setAccount(resultSet.getString("account"));
            admin.setPassword(resultSet.getString("password"));
        }else {
            admin = null;
        }
        return admin;
    }

}
