package com.day01;

import com.alibaba.fastjson.JSONObject;
import com.day01.Entities.BaseCase;
import com.day01.Entities.TestCase;
import com.day01.Entities.WriterBackData;
import com.day01.Utill.CaseUtill;
import com.day01.Utill.ExcellUtill;
import com.day01.Utill.HttpUtill;
import com.day01.Utill.RestUtill;
import org.apache.commons.math3.stat.inference.TestUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

public class testNg extends BaseCase {


    @DataProvider(name = "dataprovide")
    public  Object[][] params(){
        String [] cellnames ={"CaseId","ApiId","params"};

        Object[][] datas = CaseUtill.getCaseDateByApiId("1",cellnames);

        return datas;
    }

}
