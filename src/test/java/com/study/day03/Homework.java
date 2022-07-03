package com.study.day03;

import java.util.ArrayList;

public class Homework {
    public static void main(String[] args) {
        Student student1 =new Student();
        student1.setName("张三");
        student1.setAge("18");
        student1.setGender("nan");
        Student student2 = new Student("李四","19","女");
        Student student3 = new Student("王五","20","男");
//        创建一个集合
        ArrayList<Student> list = new ArrayList<Student>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
//        验证集合长度
        System.out.println(list.size());
//        删除第三个对象
        list.remove(2);
//        修改第一个学生名字
        list.get(0).setName("朱六");
//        打印第一个学生名字
        System.out.println(list.get(0).getName());
//        遍历取出所有学生信息
        for (Student student:list
             ) {
            System.out.println(student.getName() + student.getAge() +student.getGender());
        }


    }
}
