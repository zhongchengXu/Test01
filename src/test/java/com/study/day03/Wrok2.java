package com.study.day03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Wrok2 {
    public static void main(String[] args) {
        Student studentA = new Student("张三","25","男");
        Student studentB = new Student("李四","23","男");
        Student studentC = new Student("王五","22","女");
        Student studentD = new Student("测试","28","男");
        Student studentE = new Student("test","11","女");
        ArrayList<Student>  class1 =new ArrayList<Student>();
        ArrayList<Student>  class2 =new ArrayList<Student>();
        class1.add(studentA);
        class1.add(studentB);
        class1.add(studentC);
        class2.add(studentD);
        class2.add(studentE);
        HashMap<String ,ArrayList<Student>> map1 = new HashMap<String, ArrayList<Student>>();
        map1.put("1801",class1);
        map1.put("1802",class2);
//        遍历方法1,获取班级号码
        Set<String> nums = map1.keySet();
//        获取班级信息
        for (String num:nums
             ) {
            ArrayList<Student> stus = map1.get(num);
            for (Student stu:stus
                 ) {
                System.out.println(stu.getName() + stu.getAge() +stu.getGender());
            }
        }
//        通过entry获取
       Set<Map.Entry<String,ArrayList<Student>>> entrs = map1.entrySet();

//        遍历entry
        for (Map.Entry<String,ArrayList<Student>> ent:entrs
             ) {
            int i=0;
            Student studet = ent.getValue().get(i);
            System.out.println(studet.getName() +studet.getAge()+studet.getGender());
            i++;

        }






    }



}
