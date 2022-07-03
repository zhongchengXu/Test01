package com.study.day06;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class httpTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }
    @Test(dataProvider = "params")
    public void test(String name,String age){
//        System.out.println("["+name+"]"+age);
        httpUtill utll = new httpUtill();
        String url = "192.168.31.45";
        utll.doPost(url,name,age);
    }
    @DataProvider
    public Object[][] params(){
        Object[][] params = loadExcle.loadExcle01();
       return params;
    }
}