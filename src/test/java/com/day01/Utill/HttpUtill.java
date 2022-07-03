package com.day01.Utill;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
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
import java.util.*;

public class HttpUtill {
        //声明一个map,用来存放cookie
        public static Map<String,String> cookie = new HashMap<String, String>();




        public static String  doPost(String urlpath, Map<String ,String > params) throws UnsupportedEncodingException {
        //        填写接口地址
        //        String url ="192.168.214.4:9090";
        //        指定接口方式为:post
        String responseresult =null;
        try {
        HttpPost post = new HttpPost(urlpath);
        //        准备测试数据
        //        String username="test01";
        //        String password="123456";
        Set<String> keyset=params.keySet();
        //        设置请求参数
        List<BasicNameValuePair> paramters = new ArrayList<BasicNameValuePair>();
        for (String name:keyset
        ) {
        String value = params.get(name);
        paramters.add(new BasicNameValuePair(name,value));
        }
        //        构建请求请求客户端
        HttpClient client = HttpClients.createDefault();
        post.setEntity(new UrlEncodedFormEntity(paramters,"utf-8"));//将参数设置到请求头中,设置参数格式utf-8
        //添加cookie到请求中
            addCookieInRequestHeaderBeforRequest(post);
        //        发送请求
        HttpResponse response = client.execute(post);
        //将cookie存入map中
            getAndStoreCookiesFromResponsHeader(response);

        //        从结果中取出相应参数
        int responsecode = response.getStatusLine().getStatusCode();
        //        取出相应体结果,entityutill类中的tostring,和这个有什么区别
        responseresult = response.getEntity().toString();


        }
        catch (ClientProtocolException clientProtocolException) {
        clientProtocolException.printStackTrace();
        } catch (IOException ioException) {
        ioException.printStackTrace();
        }
            return responseresult;

        }

    private static void addCookieInRequestHeaderBeforRequest(HttpRequest request) {
            String jsessionIdCookie = cookie.get("JSESSION");
            if (jsessionIdCookie !=null){
                request.addHeader("cookie",jsessionIdCookie);
            }




    }

    public static String  doGet(String url,Map<String ,String >params){
            String responseresult =null;
            try {

                Set<String> keyset = params.keySet();
        //            定义一个定位标识
            int mark =1;
            //      准备测试数据,将参数遍历加入地址中

            for (String name:keyset) {
                if(mark == 1){
                    url+=("?"+name +"="+params.get(name));

                }
                else {
                    url+=("&"+name+"="+params.get(name));
                }
                mark++;

            }
        //            String username = "张三";
        //            String password = "123456";
        //            url+=("?username =张三&password=123456");
        //        指定接口提交方式
            HttpGet get = new HttpGet(url);
        //        发送请求拿到接口返回数据
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = null;
                //添加cookie到请求中
                addCookieInRequestHeaderBeforRequest(get);
                response = client.execute(get);
                //将cookie存入map中
                getAndStoreCookiesFromResponsHeader(response);

                int code = response.getStatusLine().getStatusCode();
                responseresult = EntityUtils.toString(response.getEntity()) ;

                System.out.println(responseresult);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseresult;

    }
//    接口调用方法
    public static String doServer(String url,String type,Map<String ,String > params){
            String result =null;

        try {
            if (type.equals("post")){
                result = HttpUtill.doPost(url,params);
//                System.out.println(result);
            }
            else if(type.equals("get")){
               result=HttpUtill.doGet(url,params);
//                System.out.println(result);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



            return result;
    }
    //定义一个方法,响应头中封装cookie
    public static void getAndStoreCookiesFromResponsHeader(HttpResponse httpResponse){
            //从响应头中取出名字为"set-Cookie"的响应头
        Header setCookieHeader = httpResponse.getFirstHeader("Set-Cookie");
        //如果不为空,取出响应头的值
        if (setCookieHeader!=null){
            //取出响应头的值
            String cookiePairsString = setCookieHeader.getValue();
            //以";"切分为数组
            String[] cookiePairs =cookiePairsString.split(";");
            if (cookiePairs!=null){
                //遍历所有的值
                for (String cookiePair:cookiePairs) {
                    //如果包含JSESSIONID,则包含会话id这个数据
                    if (cookiePair.contains("JSESSIONID")) {
                        //保存到map
                        cookie.put("JSESSIONID",cookiePair);
                        }
                    }
                }
            }
        }
    }






