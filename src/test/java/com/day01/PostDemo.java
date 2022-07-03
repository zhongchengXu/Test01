package com.day01;

import com.sun.security.ntlm.Client;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class PostDemo {
    public static void main(String[] args) throws IOException {
//        填写接口地址
        String url ="192.168.214.4:9090";
//        指定接口方式为:post
        HttpPost post = new HttpPost(url);
//        准备测试数据
        String username="test01";
        String password="123456";
//        设置请求参数
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("uesrname",username));
        params.add(new BasicNameValuePair("passwrod",password));

        post.setEntity(new UrlEncodedFormEntity(params,"utf-8"));//将参数设置到请求头中,设置参数格式utf-8
//        构建请求请求客户端
        HttpClient client =HttpClients.createDefault();
//        发送请求
        HttpResponse response = client.execute(post);
//        从结果中取出相应参数
        int responsecode = response.getStatusLine().getStatusCode();
//        取出相应体结果,entityutill类中的tostring,和这个有什么区别
        String responseresult = response.getEntity().toString();



    }
}
