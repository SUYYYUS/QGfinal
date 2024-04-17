package com.suyyyus.utils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

public class MyConnectionPool implements DataSource {
    // 数据库连接池大小
    private final static int POOL_SIZE = 10;
    // 创建容器，储存connection对象
    private static LinkedList<Connection> pool = new LinkedList<>();
    // 设置最大连接对象
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection con = null;
            try {
                con = JDBCUtil.getConnection();
                pool.add(con);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public Connection getConnection() {
        Connection con = null;
        // 判断是否还可以添加连接对象
        if(pool.size()==0) {
            for (int i = 0; i < POOL_SIZE; i++) {
                try {
                    con = JDBCUtil.getConnection();
                    pool.add(con);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        con = pool.remove(0);
        return con;
    }

    /**
     * 归还连接对象，回归连接池
     * @param con
     */
    public void backConnection(Connection con) {
        pool.add(con);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
