package com.autoapi.utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {
    //声明一个公共变量 properties
    public static Properties properties = new Properties();
    static{
        try {
            InputStream inputStream=new FileInputStream(new File("src/test/.."));
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获取文件地址公共静态方法
    public static String getExcelPath(){
        return properties.getProperty("excel.path");
    }
}
