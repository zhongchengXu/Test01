package com.autoapi.utils;

import com.autoapi.pojo.WriterBackData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    //将用例行和用例编号对应,设置一个映射Map关系
    public static Map<String,Integer> caseIdRowNumMapping = new HashMap<String, Integer>();
    //将用例列名称和列号对应,设置一个映射Map关系
    public static Map<String,Integer> cellNameCellNumMapping = new HashMap<String, Integer>();
    //创建一个列表,将需要回写的内容封装对象,加入列表中
    public static List<WriterBackData> writerBackData = new ArrayList<WriterBackData>();

    


}
