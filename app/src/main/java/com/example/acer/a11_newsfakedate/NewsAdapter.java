package com.example.acer.a11_newsfakedate;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by acer on 2018/3/7.
 */

public class NewsAdapter extends BaseAdapter {
    private  ArrayList<NewsBean> list;
    private  Context context;

    public NewsAdapter(ArrayList<NewsBean> list, Context context){

        this.context = context;
        this.list = list;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView != null){
            view = convertView;
        }else{
            view = View.inflate(context,R.layout.news,null);
        }
        TextView id = (TextView)view.findViewById(R.id.id);
        TextView tem = (TextView)view.findViewById(R.id.tem);
        TextView hum = (TextView)view.findViewById(R.id.hum);
        NewsBean bean = list.get(position);
        id.setText(bean.id+"");
        tem.setText(bean.comment+"");
         hum.setText(bean.time);

        return view;
    }
}
