package com.tomcat.ex01;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/6/25.
 */
public class Request {
    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    // read inputStream to StringBuilder
    public void parse() {
        StringBuilder request = new StringBuilder(1 << 11);
        byte[] buffer = new byte[1 << 11];

        int i = 0;
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
        if (uri == null) {
            System.out.println("Failed to get uri from requet inputStream.");
        }
    }

    // search the first and second whitespace
    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }
        }
        return StringUtils.EMPTY;
    }

    public String getUri() {
        return uri;
    }
}
