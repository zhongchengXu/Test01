package com.day01.Utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties properties =new Properties();
    static{
        try {
            InputStream inputStream = new FileInputStream(new File("src/test/test.properties"));
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getExcelPath(){
        return properties.getProperty("eccel.path");
    }

}
