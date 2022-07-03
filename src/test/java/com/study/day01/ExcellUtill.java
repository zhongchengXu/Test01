package com.study.day01;

import jdk.nashorn.internal.codegen.types.Type;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class ExcellUtill {
    public static void main(String[] args) {
        int[] rowNum ={2,3};
        int[] cellNum = {3,4};
        Object[][] params = ExcellUtill.laod("src/test/resources/test01.xlsx","用例",rowNum,cellNum);

        for (int i = 0; i <rowNum.length ; i++) {
            for (int j = 0; j <cellNum.length ; j++) {
                System.out.println(params[i][j]);
            }

        }
    }

    public static Object[][] laod(String filePath,String sheetName,int[] rowNum,int[] cellNum) {
        Object[][] params = new Object[rowNum.length][cellNum.length];

        try {
            Workbook workbook = WorkbookFactory.create(new File(filePath));
            Sheet sheet =workbook.getSheet(sheetName);
//            获取行
            for (int i = 0; i <rowNum.length ; i++) {
                Row row=sheet.getRow(rowNum[i]-1);
//                获取列
                for (int j = 0; j <cellNum.length ; j++) {
                    Cell cell = row.getCell(cellNum[j]-1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);//后面为列值为空时,忽略空错误
                     cell.setCellType(CellType.STRING);//设置列数据类型转换为字符串
                    String velue =cell.getStringCellValue();//获取列值
//                    将列值加入数组中
                    params[i][j] = velue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return params;

    }
}
