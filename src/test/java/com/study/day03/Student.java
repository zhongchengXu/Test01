package com.study.day03;

public class Student {
    public String Name;
    public String age;
    public String gender;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public Student(){

    };
    public Student(String name,String age,String gender){
        this.Name = name;
        this.age =age;
        this.gender =gender;
    }
}
