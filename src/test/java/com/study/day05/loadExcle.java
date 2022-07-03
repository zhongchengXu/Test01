package com.study.day05;



import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class loadExcle {
    public static void main(String[] args)  {
        loadExcle ld = new loadExcle();
        ld.loadExcle01();
    }
    public static  Object[][] loadExcle01() {
        Object[][] data = null;
        try {
            String filepath = "src\\test\\resources\\test.xlsx";
//        创建一个wokebook
            Workbook workbook = WorkbookFactory.create(new File(filepath));

//        读取sheet
            Sheet sheet = workbook.getSheet("test01");
            data = new Object[2][2];
//        读取行row
            for (int i = 1; i < 3; i++) {
//            循环读取所有行数据
                Row row = sheet.getRow(i);
//            循环获取列数据
                for (int j = 0; j < 2; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//                将内容转化成字符串类型
                    cell.setCellType(CellType.STRING);
//                方法已经不在使用了
                    String value = cell.getStringCellValue();
                    data[i - 1][j] = value;
//                    System.out.println(value);

                }
            }
//        返回数组

        }
        catch (Exception e) {
            System.out.println("错误");
        }
        return data;
    }
}
