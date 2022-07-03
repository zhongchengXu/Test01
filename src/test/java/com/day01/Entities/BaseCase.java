package com.day01.Entities;

import com.alibaba.fastjson.JSONObject;
import com.day01.Utill.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.Map;

public class BaseCase {
    public String [] cellNames = {"CaseId","ApiId","Params","PreValidateSql","PreValidateResult","AfterValidateSql","AfterValidateResult"};
    /*测试基类
     *
     * */
    @Test(dataProvider = "dataprovide")
    public void testcase1(String idFromTest, String ApiId, String params,String PreValidateSql,String PreValidateResult,
                          String AfterValidateSql,String AfterValidateResult) {
        //判断是否查询sql是否为空,不为空时,进行查询
        if (PreValidateSql !=null && PreValidateSql.trim().length()> 0){
            //在接口调用前查询结果
            String preValidateResult = DBCheckUtil.doQuery(PreValidateSql);
            //执行完成后,调用回写方法,进行数据回写
            ExcellUtill.writerBackDatas.add(new WriterBackData("用例",idFromTest,"PreValidateResult",preValidateResult));

        }

        //通过调用方法取得Url
        String url = RestUtill.getUrlByApiId(ApiId);
        //通过调用方法获取接口类型
        String type = RestUtill.getTypeByApiId(ApiId);
        //调用替换方法,替换数据
        VariableUtil.replaceValues(params);
        //json格式转化成为Map参数格式
        Map<String, String> param = (Map<String, String>) JSONObject.parse(params);
//        调用接口请求方法
        String actualResponse = HttpUtill.doServer(url, type, param);
//        System.out.println(result);
        AssertUtil.actualResponseDate(actualResponse);
        //将数据封装对象,sheetName,cellName需要写入
        ExcellUtill.writerBackDatas.add(new WriterBackData("用例", idFromTest, "实际结果", actualResponse));
        //接口调用后执行查询结果
        if (AfterValidateSql !=null && AfterValidateSql.trim().length()> 0){
            //在接口调用前查询结果
            String afterValidateResult = DBCheckUtil.doQuery(AfterValidateSql);
            ExcellUtill.writerBackDatas.add(new WriterBackData("用例",idFromTest,"AfterValidateSql",afterValidateResult));

        }

    }
    @AfterSuite//测试套件执行后,数据批量回写
    public void batchWriterBackDates(){
        ExcellUtill.batchWriterBackDatas("src/test01");
    }
}
