package com.example.acer.a11_newsfakedate;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by acer on 2018/3/7.
 */

public class NewsUtils {
    public static String newsPath_url = "http://192.168.179.2:8080/itheima74/servlet/GetNewsServlet";
    //1.从服务器封装数据到list中返回
    public static ArrayList<NewsBean> getAllnewsFromNetWork(Context context)  {
        ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();
        try {
            URL url = new URL(newsPath_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10*1000);
            int code = conn.getResponseCode();
            if(code == 200){
                //1.获取请求到的流信息
                InputStream inputStream = conn.getInputStream();
                String result = StreamUtils.stermToString(inputStream);
                System.out.println("读取流成功");
                //2.解析获得到到数据到list集合中
                     //2.1将请求到的字符串封装成一个json对象
                JSONObject root_json = new JSONObject(result);
                    //2.2获取json中的nwess作为jsonArray对象
                JSONArray jsonArray = root_json.getJSONArray("newss");
                for(int i =0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    NewsBean bean = new NewsBean();
                    bean.id = jsonObject.getInt("id");
                    bean.comment = jsonObject.getInt("comment");
                    bean.time = jsonObject.getString("time");
                    Log.d("MainActivity","------this is the bean_time"+i+bean.time);
                    arrayList.add(bean);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return arrayList;
    }
}
