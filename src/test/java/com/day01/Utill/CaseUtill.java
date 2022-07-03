package com.day01.Utill;

import com.day01.Entities.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CaseUtill {
        //声明一个集合,封装所有测试类
       public static List<TestCase>  testcases = new ArrayList<TestCase>();
       //读取所有文件测试数据,并封装到类中
        static{
            List<TestCase> list = ExcellUtill.laod("src/test/resources/test01.xlsx","用例",TestCase.class);
            testcases.addAll(list);
         }



       //创建一个方法,传入值,返回二维数组来传输数据
    public static Object[][] getCaseDateByApiId(String apiId,String [] cellnames) {
        Class<TestCase> clazz = TestCase.class;
        //保存指定接口编号的对象集合
        List<TestCase> caseList = new ArrayList<TestCase>();
            for (TestCase cases : testcases) {
                    //根据传入apiId和对象中的apiId属性对比,取出参数
                    if (cases.getId().equals(apiId)) {
                        caseList.add(cases);
                    }
                }
                //将传入的列名取出数据,封装到数组中
        Object[][] datas = new Object[caseList.size()][cellnames.length];
            for (int i = 0; i < caseList.size(); i++) {
                TestCase cs = caseList.get(i);
                    for (int j = 0; j < cellnames.length; j++) {
                    //反射获取方法名
                        String methodname = "get" + cellnames[j];
                    //反射方法
                        Method method = null;
                        try {
                            method = clazz.getMethod(methodname);

                            //调用方法
                            String value = (String) method.invoke(cs);
                            datas[i][j] = value;
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
            }

        return datas;
    }
}
