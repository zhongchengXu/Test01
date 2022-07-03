package com.study.day01;

public class caculate {
    public int number;
    public String name;



    public double add(double a, double b){
        return a+b;
    }
    public double jianfa(double a,double b){
        return a-b;
    }
    public double chengfa(double a,double b){
        return a*b;
    }
    public double chufa(double a,double b){
        return a/b;
    }
    public static void main(String [] args){
        caculate ca =new caculate();

        double c =ca.add(1,3);
        System.out.println(c);
    }
}
