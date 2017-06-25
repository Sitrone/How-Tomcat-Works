package com.tomcat.ex02;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/25.
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            System.out.println("Failed to process static resource.");
            e.printStackTrace();
        }
    }
}
