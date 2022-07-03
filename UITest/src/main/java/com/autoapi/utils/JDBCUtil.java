package com.autoapi.utils;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JDBCUtil {
    //声明一个静态公共properties对象
    public static Properties properties = new Properties();
    //静态代码块解析properties
    static{
        InputStream iSrteam;
        try {
            iSrteam = new FileInputStream(new File("src/test/resources/testProperties"));
            properties.load(iSrteam);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Map<String, Object> doQuery(String sql, Object ... values){
        Map<String ,Object> columnLabelAndValue = null;

        //用户信息和地址
 
        String url = "";
        String username = "";
        String password = "";
        //创建连接
        try {
            Connection connection = getConenction();
            //创建preparedstatement,有用于执行数据库方法
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //将需要查询的参数放入preparedstatement对象中
            for (int i = 0; i <values.length ; i++) {
                //添加参数
                preparedStatement.setObject(i+1,values[i]);
            }
            //调用查询方法,执行sql,返回resultset结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            //获取相关数据\
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //获取查询字段数目
            int columnCount = resultSetMetaData.getColumnCount();
            //从结果集中获取数据
            columnLabelAndValue =new HashMap<String, Object>();
            while(resultSet.next()){
                //循环取出所有数据
                for (int i = 0; i <columnCount ; i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i);
                    String columnValue = resultSet.getObject(columnLabel).toString();
                    columnLabelAndValue.put(columnLabel,columnValue);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return columnLabelAndValue;
    }
    //获取连接方法
    private static Connection getConenction(){
        Connection connection = null;
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String pwd = properties.getProperty("jdbc.pwd");
        try {
            connection = DriverManager.getConnection(url,username,pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }



}
