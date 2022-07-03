package com.study.day01;

public class student {
    public String name;
    public String gender;
    public int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void showStudentname(student student){
        String name = student.getName();
        System.out.println(name);
    }
    public student(String name , String gender, int age){
    this.age = age;
    this.name = name ;
    this.gender = gender;
    }
    public student (){
        this.age = age;
        this.name = name;
        this.gender =gender;
    };
    public void showStudent(student student){
        System.out.println(student);
    }
}
