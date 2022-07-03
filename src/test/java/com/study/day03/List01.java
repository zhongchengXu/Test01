package com.study.day03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class List01 {


    public static <Hashset> void main(String[] args) {
        String nameA = "张三";
        //判断字符是否相等
//        System.out.println(name.equals("q"));
////        判断字符串中是否包含某个内容
//        System.out.println(name.contains("三"));
////        截取字段
//        System.out.println(name.substring(1));
////        获取字符串长度
//        System.out.println(name.length());
////        按照正则切分字符
////        name.split()
        String nameB = "李四";
        String nameC = "王五";
        String nameD = "赵六";
//        ArrayList<String> lit = new ArrayList<String>();
//        lit.add(nameA);
//        lit.add(nameB);
//        lit.add(nameC);
//        lit.add(nameD);
//        for (String name : lit
//        ) {
//            if (name != "王五") {
//                System.out.println(name);
//
//            }
//            else {
//                System.out.println("其他");
//            }
//            }
//        lit.set(1,"修改2");
//        System.out.println(lit.get(1));
//        lit.remove(2);
//        System.out.println(lit.size());
        HashSet<String> set1 =new HashSet<String>();
        set1.add(nameA);
        set1.add(nameB);
        set1.add(nameC);
        set1.add(nameD);
        System.out.println(set1.size());
//        获取set中的所有元素
        Object[]  set2 = set1.toArray();
        for (Object name:set2
             ) {
            System.out.println(name);

        }
        }
    }