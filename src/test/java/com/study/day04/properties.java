package com.study.day04;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class properties {
    /*
    * properties文件解析
    * */

//  xml文件解析
    public void reader(String filePath) throws FileNotFoundException, DocumentException {
        File file1 = new File(filePath);
//        输入流对象
        FileInputStream inputStream = new FileInputStream(file1);
//        解析文件
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        document.getRootElement();
    }




}
