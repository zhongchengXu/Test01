package com.day01.Utill;

import com.day01.Entities.Rest;

import java.util.ArrayList;
import java.util.List;

public class RestUtill {
    //准备list,将读取的对象数据封装
    public static List<Rest> restList = new ArrayList<Rest>();
    //调用封装方法,将对象封装到对象中
    static{
        List<Rest> list =ExcellUtill.laod("src/test/resources/test01.xlsx","接口",Rest.class);//读取数据封装对象
        restList.addAll(list);
    }
    //根据传入id,匹配接口数据,调出接口中的url
    public static String getUrlByApiId(String apiId){
        //循环取出所有对象
        for (Rest rest:restList) {
            String id = rest.getId();
            //根据传入id判断,调用的url
            if (id.equals(apiId)){
                String url = rest.getUrl();
                return url;
            }
        }
        return "";//预防当没有匹配上时报错,默认传入空字符串
    }
    public static String getTypeByApiId(String apiID){
        for (Rest rest:restList) {
            if (rest.getId().equals(apiID)){
                return rest.getType();
            }
        }
        return "";
    }





}
