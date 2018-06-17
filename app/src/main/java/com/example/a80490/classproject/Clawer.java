package com.example.a80490.classproject;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 唐品 on 2018/6/3 0003.
 */

public class Clawer {
    public static void run(Vector<String> names, Vector<Double> scores, Vector<Double> credits, Vector<String> types, Handler handler) throws IOException {
        if (scores == null || credits == null)
            throw new IOException();

        names.clear();
        types.clear();
        scores.clear();
        credits.clear();


        Header cookie; // 将要使用的cookie
        HttpClient httpClient = new DefaultHttpClient(); // 新建HTTP客户端
        String url = "http://202.115.47.141/loginAction.do"; // 目标地址
        HttpPost httpPost = new HttpPost(url); // POST方法
        ArrayList<NameValuePair> list = new ArrayList<NameValuePair>(); // POST参数列表
        list.add(new BasicNameValuePair("zjh", Account.ACCOUNT)); // 学号
        list.add(new BasicNameValuePair("mm", Account.PASSWORD)); // 密码

        // 开始抓取
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list)); // 将POST参数列表添加至POST方法
            HttpResponse response = httpClient.execute(httpPost); // 执行POST请求
            try {
                cookie = response.getLastHeader("Cookie"); // 获取Cookie
            } catch(Exception e) {
                // 发送Message提示登陆失败
                Message message = new Message();
                message.what = -2;
                handler.sendMessage(message);
                return;
            }
            HttpEntity entity = response.getEntity(); // 获取<body>标签内容
            String str = EntityUtils.toString(entity, "GBK"); // 以GBK方法解码

            url = "http://202.115.47.141/gradeLnAllAction.do?type=ln&oper=fa"; // 新的目标地址
            HttpGet httpGet = new HttpGet(url); // GET方法访问url
            httpGet.setHeader(cookie); // GET参数设置
            response = httpClient.execute(httpGet); // 执行GET请求
            entity = response.getEntity(); // 获取<body>标签内容
            str = EntityUtils.toString(entity, "GBK"); // 以GBK方法解码

            String pattern = "name=\"lnfaIfra\".*?\"(.*?)\"";
            Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
            Matcher matcher = r.matcher(str);
            String scoreURL = "http://202.115.47.141/";
            if (matcher.find()) {
                scoreURL = scoreURL + matcher.group(1);
            } else {
                // 发送Message提示登陆失败
                Message message = new Message();
                message.what = -2;
                handler.sendMessage(message);
                return;
            }
            httpGet = new HttpGet(scoreURL);
            httpGet.setHeader(cookie);
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            str = EntityUtils.toString(entity, "GBK");

            // 获取成绩
            pattern = "odd.*?<td.*?<td.*?<td.*?>\\s*(.*?)\\s*<.*?<td.*?<td.*?>\\s*(.*?)\\s*<.*?<td.*?>\\s*(.*?)\\s*<.*?<p.*?>(.*?)&";
            r = Pattern.compile(pattern, Pattern.DOTALL);
            matcher = r.matcher(str);
            while (matcher.find()) {
                String name = "";
                String type = "";
                double score = 0;
                double credit = 0;
                try {
                    name = matcher.group(1);
                    credit = Double.parseDouble(matcher.group(2));
                    if(matcher.group(4).equals("优秀"))
                        score=95;
                    else if(matcher.group(4).equals("良好"))
                        score=85;
                    else if(matcher.group(4).equals("中等"))
                        score=75;
                    else if(matcher.group(4).equals("及格"))
                        score=60;
                    else if(matcher.group(4).contains("不")||matcher.group(4).contains("未"))
                        score=0;
                    else
                        score = Double.parseDouble(matcher.group(4));
                    type = matcher.group(3);
                    names.add(name);
                    scores.add(score);
                    credits.add(credit);
                    types.add(type);
                } catch (Exception e) {
                    System.err.println(matcher.group(1) + "    " + matcher.group(2)+ "    " + matcher.group(3)+ "    " + matcher.group(4));
                }
            }
            // 发送Message提示该线程已执行完毕
            Message message = new Message();
            message.what = 0;
            handler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            // 发送Message提示连接是失败
            Message message = new Message();
            message.what = -1;
            handler.sendMessage(message);
        }

        
    }
}
