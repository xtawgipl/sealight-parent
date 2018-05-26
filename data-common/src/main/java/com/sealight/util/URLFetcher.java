package com.sealight.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 刚刚抓取
 *
 * @author zhangjj
 * @create 2017-12-10 13:03
 **/
public class URLFetcher {

    /** 默认重试次数 */
    public static final Integer TRY_NUM = 180;


    public static JSONObject pickDataJSON(String url) {
        String data = pickData(url);
        return JSONObject.parseObject(data);
    }

    public static String pickData(String url) {

        return pickData(url, TRY_NUM);
    }

    public static String pickDataPost(String url, Map<String, String> params) {

        return pickDataPost(url, params, TRY_NUM);
    }

    /**
     * 爬取网页信息
     */
    public static String pickData(String url, Integer tryNum) {
        if(url == null){
            System.err.println("url 是空的!");
            return null;
        }
        System.out.println(Thread.currentThread().getName() + " (" + (TRY_NUM - tryNum + 1) + ") : " + url);
        url = url.replaceAll(" ", "%20");
        System.out.println("请求 url = " + url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(20000).setConnectionRequestTimeout(20000)
                .setSocketTimeout(20000).build();
        try {
            HttpGet httpget = new HttpGet(url);
            httpget.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            if(tryNum-- > 0){
                try {
                    Thread.sleep(1500L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                return pickData(url, tryNum);
            }else{
                System.err.println("errorUrl : "  + " (" + (TRY_NUM - tryNum + 1) + ") : " + url);
                e.printStackTrace();
            }
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static String pickDataPost(String url, Map<String, String> params, Integer tryNum) {
        if(url == null){
            System.err.println("url 是空的!");
            return null;
        }
        System.out.println(Thread.currentThread().getName() + " (" + (TRY_NUM - tryNum + 1) + ") : " + url);
        url = url.replaceAll(" ", "%20");
        System.out.println("请求 url = " + url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(20000).setConnectionRequestTimeout(20000)
                .setSocketTimeout(20000).build();
        try {
            HttpPost httppost = new HttpPost(url);
            httppost.setConfig(requestConfig);

            List<NameValuePair> nvps = new ArrayList<>();
            for(Map.Entry<String, String> entry : params.entrySet()){
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httppost.setEntity(new UrlEncodedFormEntity(nvps));

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            if(tryNum-- > 0){
                try {
                    Thread.sleep(1500L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                return pickData(url, tryNum);
            }else{
                System.err.println("errorUrl : "  + " (" + (TRY_NUM - tryNum + 1) + ") : " + url);
                e.printStackTrace();
            }
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("brand", "Harley Davidson");
        params.put("model", "1200 Custom XL1200C");
        params.put("year", "2011");
        params.put("reg", "dot");
        params.put("dist", "jws");
        params.put("submit", "Find Products");
        params.put("screensize", "1200");
        String html = URLFetcher.pickDataPost("http://my.jwspeaker.com/garage/garage.php", params);
        System.out.println(html);
    }
}
