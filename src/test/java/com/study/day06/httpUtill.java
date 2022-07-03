package com.study.day06;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class httpUtill {
    public void doPost(String url,String canshu1,String canshu2){
//        声明请求地址
//        声明请求方式
        HttpPost post = new HttpPost();

//        声明请求参数
//        String canshu1 = "test01";
//        String canshu2 = "test02";
//        请求参数设置到请求体
        try {
            List<BasicNameValuePair> Namevaluepair = new ArrayList<BasicNameValuePair>();
            Namevaluepair.add(new BasicNameValuePair("canshu1",canshu1));
            Namevaluepair.add(new BasicNameValuePair("canshu2",canshu2));
            post.setEntity(new UrlEncodedFormEntity(Namevaluepair,"utf-8"));

//        执行请求,获得返回值
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(post);
//            获取响应状态码
            int code = response.getStatusLine().getStatusCode();
            System.out.println(code);
//            获得响应体
            String  result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void doGet(String url,String canshu3,String canshu4){
            //            声明地址
//            String url2 = "192.168.214.4";
            //        请求参数
//            String canshu3 = "参数3";
//            String canshu4 = "参数4";
            url+=("?canshu3"+canshu3 +"&canshu4"+canshu4);
            try {

            //        请求方式
            HttpGet get =new HttpGet(url);
            //        发送去请求,返回结果
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(get);

//            相应状态码
                int code = response.getStatusLine().getStatusCode();
                System.out.println(code);
//                打印结果
                String reslut = EntityUtils.toString(response.getEntity());
                System.out.println(reslut);
            } catch (ClientProtocolException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
            }


        }





}
