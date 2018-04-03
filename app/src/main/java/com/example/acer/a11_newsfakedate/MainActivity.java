package com.example.acer.a11_newsfakedate;

import android.content.Context;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.ArrayList;
//import java.util.logging.Handler;
import android.os.Handler;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    public ListView lv_test;
    Context mcontext;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            ArrayList<NewsBean> list = (ArrayList<NewsBean>) msg.obj;
            NewsAdapter adapter = new NewsAdapter(list, mcontext);
            //UI更新必须在主线程中；
            lv_test.setAdapter(adapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcontext = this;
        lv_test = (ListView) findViewById(R.id.lv_test);
        //子线程去连接网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                //这个是联网的操作
                ArrayList<NewsBean> list = NewsUtils.getAllnewsFromNetWork(mcontext);
                Message msg = Message.obtain();
                msg.obj = list;
                handler.sendMessage(msg);
            }
        }).start();


    }
}
