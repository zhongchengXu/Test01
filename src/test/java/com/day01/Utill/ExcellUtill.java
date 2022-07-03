package com.day01.Utill;

import com.day01.Entities.Rest;
import com.day01.Entities.TestCase;
import com.day01.Entities.Variable;
import com.day01.Entities.WriterBackData;
import com.study.day03.Wrok2;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.sun.org.apache.xpath.internal.objects.XNull;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcellUtill {
//    public static void main(String[] args) {
//        int[] rowNum ={2,3};
//        int[] cellNum = {3,4};
//        Object[][] params = ExcellUtill.laod("src/test/resources/test01.xlsx","用例",rowNum,cellNum);
//
//        for (int i = 0; i <rowNum.length ; i++) {
//            for (int j = 0; j <cellNum.length ; j++) {
//                System.out.println(params[i][j]);
//            }
//
//        }
//    }
    //将用例行和用例编号对应,设置一个Map映射关系
    public static Map<String,Integer> caseIdRowNumMapping = new HashMap<String, Integer>();
    //将列名称和列编号对应,设置一个map映射关系
    public static Map<String,Integer> cellNameCellnumMapping = new HashMap<String, Integer>();
    //创建一个列表,将需要回写内容,加入列表中
    public static List<WriterBackData> writerBackDatas  = new ArrayList<WriterBackData>();
    //加载map数据
    static{
        loadRownumAndCellnumMapping("src/test/resources/test01.xlsx","用例");
    }
    //读取数据,封装map
    private static void loadRownumAndCellnumMapping(String filePath, String sheetName) {
        InputStream inputStream = null;
        try {
             inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            //获取第一行数据
            Row titlerow = sheet.getRow(0);
            //判断首行是否为空
            if(titlerow !=null){
                //每行列数
                int lastCellnum = titlerow.getLastCellNum();
                //循环处理标题每一列数据
                for (int i = 0; i <lastCellnum ; i++) {
                    Cell cell =titlerow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String titel = cell.getStringCellValue();
                    titel = titel.substring(0,titel.indexOf("("));
                    //获取列索引
                    int cellnum = cell.getAddress().getColumn();
                    //将列号和列名封装
                    cellNameCellnumMapping.put(titel,cellnum);
                }
            }
            //从第二行开始,获取所有行数据行号
            int lastRownum = sheet.getLastRowNum();
            for (int i = 1; i <lastRownum ; i++) {
                Row dataRow = sheet.getRow(i);
                //获取每行的第一列,用例设计时,第一列为用例编号
                Cell fristCell = dataRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                fristCell.setCellType(CellType.STRING);
                String caseId = fristCell.getStringCellValue();
                //获取行号
                int rownum = dataRow.getRowNum();
                //封装进map
                caseIdRowNumMapping.put(caseId,rownum);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //关闭流对象
        finally {
            try {
                    if (inputStream !=null){
                    inputStream.close();
                }
            }catch (IOException e) {
                    e.printStackTrace();
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
    //加载对象,将对象封装
    public static <T> List<T> laod(String filePath, String sheetName, Class<T> clazz) {
        //Class<TestCase> testCaseClass = TestCase.class;直接传入字节码
        List<T> list = new ArrayList<T>();

        try {
            Workbook workbook = WorkbookFactory.create(new File(filePath));
            Sheet sheet = workbook.getSheet(sheetName);
            //获取首行所有字段并封装进数组中
            Row firstRow = sheet.getRow(0);
            String[] fristCellNames = new String[firstRow.getLastCellNum()];
            //首先进行行判断
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                if (i == 0) {
                    //将首行封装到对象中
                    for (int j = 0; j < fristCellNames.length; j++) {
                        Cell cell = firstRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        cell.setCellType(CellType.STRING);
                        String vales = cell.getStringCellValue();
                        String cellName = vales.substring(0, vales.indexOf("("));
                        fristCellNames[j] = cellName;
                        //验证数据已经封装
                       // System.out.println(fristCellNames.length);
                    }

                } else {
                    //通过字节码,声明对象,优化后,使用T泛型来应用对象
                    T obj = clazz.newInstance();
                    for (int j = 0; j < firstRow.getLastCellNum(); j++) {
                        Row row = sheet.getRow(i);
                        Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        cell.setCellType(CellType.STRING);
                        String cellValue = cell.getStringCellValue();
                        //反射调用方法,将值封装到对象中
                        String methodName = "set" + fristCellNames[j];//角标对应的首行列名
                        //反射调用的方法
                        Method method = clazz.getMethod(methodName, String.class);
                        //调用方法
                        method.invoke(obj, cellValue);
                    }
                    /*一开始判断后返回结果
                    //判断传入字节码类型
                    if (obj instanceof TestCase){//instanceof 判断字节码类型方法
                        TestCase cs=(TestCase) obj;
                        CaseUtill.testcases.add(cs);
                    }else if (obj instanceof Rest){
                        Rest rest= (Rest)obj;
                        RestUtill.restList.add(rest);
                    }else if (obj instanceof Variable){
                        Variable variable = (Variable) obj;
                        VariableUtil.variables.add(variable);
                    }*/
                    /*优化结果
                    * 将返回值写为list返回结果,将结果作为泛型集合                    *
                    * */
                    list.add(obj);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }
    //数据回写
    public static void writerBackData(String excelPath,String sheetName,String caseId,String cellname,String result){
        InputStream inputStream =null;
        OutputStream outputStream = null;
        //读取文档内容
        try {
            inputStream = new FileInputStream(new File(excelPath));
            Workbook workbook =WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            //获取行号
            int rownum = caseIdRowNumMapping.get(caseId);
            Row row = sheet.getRow(rownum);
            //获取列号
            int cellnum = cellNameCellnumMapping.get(cellname);
            //获取列
            Cell cell = row.getCell(cellnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(result);//将值放入指定位置
            //写入文件中
            outputStream =new FileOutputStream(new File(excelPath));
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭流对象
        finally{
            try {
                if (outputStream!=null){
                    outputStream.close();
                }
                if (inputStream !=null){
                    inputStream.close();
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    //批量回写方法
    public static void batchWriterBackDatas(String excelPath){
        InputStream inputStream =null;
        OutputStream outputStream =null;

        try {
            inputStream = new FileInputStream(new File(excelPath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            //遍历回写数据列表
            for (WriterBackData writerBackData:writerBackDatas){
                //获取sheetName
                String sheetname= writerBackData.getSheetName();
                Sheet sheet= workbook.getSheet(sheetname);
                //获取caseId
                String caseId = writerBackData.getCaseId();
                int rownum = caseIdRowNumMapping.get(caseId);
                Row row = sheet.getRow(rownum);
                String cellName = writerBackData.getCellName();
                int cellnum = cellNameCellnumMapping.get(cellName);
                Cell cell= row.getCell(cellnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String result = writerBackData.getResult();
                cell.setCellValue(result);//将值赋值进入列中
                }
            //数据回写
            outputStream = new FileOutputStream(new File(excelPath));
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //关闭流对象
        finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null){
                    inputStream.close();
                }
            }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }











