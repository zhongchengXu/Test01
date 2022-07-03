package com.study.day06;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class demo1 {

    public static void main(String[] args) throws IOException {
        //post请求
        //定义url
        String url = "192.168.31.45";
                //请求方式 post
        HttpPost post = new HttpPost(url);
        //设置请求参数
        String parameter1 = "test01" ;
        String parameter2 = "test02";
        //参数添加至请求体
        List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("parameter1",parameter1));
        nameValuePairs.add(new BasicNameValuePair("parameter2",parameter2));
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
        //发送请求
        HttpClient client =HttpClients.createDefault();
        HttpResponse response =client.execute(post);
        int code  = response.getStatusLine().getStatusCode();
//        响应体数据转化为字符串类型
        String ruslut = EntityUtils.toString(response.getEntity());

//        get请求
//        定义url
        String url1= "192.168.214.4";
//        设置请求参数
        String canshu3 = "test03";
        String canshu4 = "test04";
        url1+=("?参数3"+canshu3+"&参数4"+canshu4);
//        请求方式设置get
        HttpGet get =new HttpGet(url1);
//        发送请求
        HttpClient client1 = HttpClients.createDefault();
        HttpResponse response1 = client1.execute(get);
//        获取相应状态码
        int code1 = response1.getStatusLine().getStatusCode();
        System.out.println(code1);
//        获取相应结果
        String resulit1 = EntityUtils.toString(response1.getEntity());
        System.out.println(resulit1);
    }
}
