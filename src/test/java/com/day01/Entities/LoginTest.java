package com.day01.Entities;

import com.day01.Utill.CaseUtill;
import org.testng.annotations.DataProvider;

public class LoginTest extends BaseCase{
    @DataProvider(name = "dataprovide")
    public  Object[][] params() {

        Object[][] datas = CaseUtill.getCaseDateByApiId("2", cellNames);

        return datas;
    }
}
