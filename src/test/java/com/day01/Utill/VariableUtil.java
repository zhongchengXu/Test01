package com.day01.Utill;

import com.day01.Entities.Variable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class VariableUtil {
    //替换名称和内容映射map
    public static Map<String,String> variableNameAndValueMap = new HashMap<String, String>();
    //列表存放对象
    public static List<Variable> variables = new ArrayList<Variable>();
    static{
        //将数据封装进对象并加入列表中
        List<Variable> list = ExcellUtill.laod("src/test/resources/test01.xlsx","变量",Variable.class);
        variables.addAll(list);
        //将变量值加入map中
        loadVatiablesToMap();
    }

    private static void loadVatiablesToMap() {
        for (Variable variable:variables) {
            //获取变量的value值
            String variableName = variable.getName();
            //获取变量值
            String variableValue = variable.getValue();
            //如果value的值为空
            if (variableValue ==null || variableValue.trim().length() == 0){
                //要反射的类
                String reflectClass = variable.getReflectClass();
                //要反射的方法
                String reflectMethod = variable.getReflectMetod();
                //通过调用方法来生成数据

                try {
                    //通过放射获得类型字节码,  对应的反射类及方法需要另外设计,编写
                    Class clazz =Class.forName(reflectClass);
                    //反射对象创建类
                    Object object = clazz.newInstance();
                    //获取调用的方法
                    Method method = clazz.getMethod(reflectMethod);
                    //反射调用方法,获取方法返回值
                    variableValue =(String )method.invoke(object);
                } catch (ClassNotFoundException e) {
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


            }
            variableNameAndValueMap.put(variableName,variableValue);

        }
    }

    public static String  replaceValues(String params) {
        //取出所有变量名
        Set<String> variableNames = variableNameAndValueMap.keySet();
        for (String variableName:variableNames) {
            //判断变量名中,如果存在了变量名
            if (params.contains(variableName)){
                //替换字段
                params = params.replace(variableName,variableNameAndValueMap.get(variableName));
            }
        }
        return params;
    }
}
