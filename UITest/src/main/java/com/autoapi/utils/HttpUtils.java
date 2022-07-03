package com.autoapi.utils;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
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
import java.util.Map;
import java.util.Set;

public class HttpUtils {
    /**
     * 这是注释
     *
     *
     * */
    //创建一个客户端
    public static HttpClient client = HttpClients.createDefault();
    public static String doPost(String url, Map<String,String> param ){
        //创造一个post请求
        HttpPost post = new HttpPost(url);
        String result ="";
        //设置请求参数
        Set<String> keyset = param.keySet();
        //声明一个集合,存放数据
        List< NameValuePair> params = new ArrayList<NameValuePair>() ;
        //循环取出所有的数据并封装到list集合中
        for (String name:keyset) {
            String value = param.get(name);
            //将数据封装
            params.add(new BasicNameValuePair(name,value));
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(params,"utf-8"));//将请求参数加入请求中,默认格式为utf-8
            //执行请求
            HttpResponse response = client.execute(post);
            //获取响应状态码
            int statuscode = response.getStatusLine().getStatusCode();
            //获取相应体数据
            result = EntityUtils.toString(response.getEntity());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
    public static String doGet(String url,Map<String,String> parma) throws IOException {
        //参数拼接
        Set<String> names = parma.keySet();
        String result ="";
        //标识符来判断拼接参数
        int mark = 0;
        for (String name :names) {
            if (mark==0){
                url+=url +"?" + name +"="+parma.get(name);
                mark++;
            }else if (mark!=0){
                url+=url +"&" + name +"="+parma.get(name);
                mark++;
            }
        }
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        int statucode = response.getStatusLine().getStatusCode();
        result = EntityUtils.toString(response.getEntity());
        return result;
    }


}
