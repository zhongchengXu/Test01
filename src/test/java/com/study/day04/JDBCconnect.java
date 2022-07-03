package com.study.day04;

import java.sql.*;

public class JDBCconnect {
    public static void query() throws SQLException {
//      1.  根据连接信息获取数据库连接
        String ip = "192.168.214.20";
        String user ="user";
        String passwrod = "passwrod";
        Connection connect = DriverManager.getConnection(ip,user,passwrod);
//        获取preparedStatement对象,用于操作sql
        String sql = "select * from table ";
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
//        设置条件字段的值,where后条件
        preparedStatement.setObject(1,"a");
//        调用执行查询方法,执行查询,返回result结果集
        ResultSet rst = preparedStatement.executeQuery();
//        从结果集查询取数据

    }



}
