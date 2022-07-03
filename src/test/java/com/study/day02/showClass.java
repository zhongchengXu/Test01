package com.study.day02;

public class showClass extends abstractClass{

    public void mothed01() {
        System.out.println("抽象实现001");
    }

    public void methed03(int d) {
        System.out.println(d);
    }

    public static void main(String[] args) {
        showClass day02 = new showClass();
        day02.mothed01();
        day02.methed03(5);
    }

}
