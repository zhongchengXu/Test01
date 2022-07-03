package com.study.day02;

public class InterfanceStudy implements StudyInterfance {

    public void Study01() {
        System.out.println("实现抽象方法1");
    }

    public void Study02() {
        System.out.println("实现抽象方法2");
    }

    public static void main(String[] args) {
        InterfanceStudy study01 = new InterfanceStudy();
        study01.Study01();
    }
}
