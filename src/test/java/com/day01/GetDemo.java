package com.day01;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetDemo {
    public static void main(String[] args) throws IOException {
//        提供接口地址
        String url ="192.168.214.4";
//      准备测试数据
        String username = "张三";
        String password = "123456";
        url+=("?username =张三&password=123456");
//        指定接口提交方式
        HttpGet get = new HttpGet(url);
//        发送请求拿到接口返回数据
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);
        int code = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(response.getEntity()) ;
        System.out.println(result);





    }
}
