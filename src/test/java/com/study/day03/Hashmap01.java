package com.study.day03;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Hashmap01 {
    /*
    * 添加数据:put()
    * 取值:get()
    * 移除:remove()
    * 获取所有的键:keyset()
    * 获取所有的值:values()
    * 获得大小:size(),是否包含键值:constraintKey(),是否包含值:constraintValue()
    * entry --->代表一个key-value
    * */
    public static void main(String[] args) {
        HashMap<String,String> map1= new HashMap<String,String>();//声明一个hashmap对象
//        添加内容
        map1.put("张三","18");
        map1.put("李四","19");
        map1.put("王五","20");
////        取值张三
//        System.out.println(map1.get("张三"));
////        获取所有的键
//        Set<String> set1 = map1.keySet();
////        遍历取出所有值
//        for (String name:set1
//             ) {
//            System.out.println(name);
//                    }
////        遍历所有的值
//        Collection<String> values =map1.values();
//        for (String value:values
//             ) {
//            System.out.println(value);
//
//        }
//      迭代遍历所有的键值对
        Set<String> set2 = map1.keySet();
        for (String key:set2
             ) {
            System.out.println(key);
            System.out.println(map1.get(key));
        }
//        entry代表键值对
        Set<Map.Entry<String ,String>> entris = map1.entrySet();
        for (Map.Entry<String ,String > entry:entris
             ) {
            System.out.println(entry.getKey()+entry.getValue());

        }

    }



}
