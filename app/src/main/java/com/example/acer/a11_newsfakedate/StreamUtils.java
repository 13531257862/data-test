package com.example.acer.a11_newsfakedate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by acer on 2018/3/16.
 */
//解析从服务器哪来的东西，并将其变成String类型
public class StreamUtils {
    public static String stermToString(InputStream in) {
        String result = "";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        try {
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
                out.flush();
            }
            result = out.toString();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
