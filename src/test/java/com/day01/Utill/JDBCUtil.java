package com.day01.Utill;

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
        InputStream iStream ;
        try {
            iStream = new FileInputStream(new File("src/test/resources/testProperties"));
            properties.load(iStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        /*根据sql查询表数据,并以map返回key字段名,value字段值
       * */
    public static Map<String,Object> query(String sql,Object ... values){
        Map<String ,Object> columnLabelAndValues = null;
        try {
        //根据连接信息,获取数据库连接
        Connection connection = getConnectin();
        //获取Preparedstatement对象(此类对象有数据库操作方法)
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设置字段的值
            for (int i = 0; i <values.length ; i++) {
                //添加参数
                preparedStatement.setObject(i+1,values[i]);
                }
         //调用查询方法.执行查询,返回resultset结果集
         ResultSet resultSet =preparedStatement.executeQuery();
         //获取相关数据
         ResultSetMetaData metaData =resultSet.getMetaData();
         //获取查询字段数目
         int columnCount = metaData.getColumnCount();
         //从结果集查询数据
         columnLabelAndValues = new HashMap<String, Object>();
         while (resultSet.next()){
             //循环取出所有字段的值
             for (int i = 0; i <=columnCount ; i++) {
                 String columnLable = metaData.getColumnLabel(i);
                 String columnValue = resultSet.getObject(columnLable).toString();
                 columnLabelAndValues.put(columnLable,columnValue);
             }
         }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return columnLabelAndValues;
    }

    private static Connection getConnectin() {
        Connection connection = null;
        try {
        //从properties中取url
        String url = properties.getProperty("jdbc.url");
        //从properties中获取user
        String user =properties.getProperty("jdbc.username");
        //从properties中获取password
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
