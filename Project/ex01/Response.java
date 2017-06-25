package com.tomcat.ex01;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Administrator on 2017/6/25.
 */
public class Response {
    private static final int BUFFER_SIZE = 1 << 10;

    private Request request;

    private OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        File file = new File(HttpServer.WEB_ROOT, request.getUri());
        if (file.isFile() && file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                output.write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));
                output.write("Content-Type: text/html\r\n".getBytes(StandardCharsets.UTF_8));
                output.write("\r\n".getBytes(StandardCharsets.UTF_8));
                int ch = 0;
                while ((ch = fis.read(bytes, 0, BUFFER_SIZE)) != -1) {
                    output.write(bytes, 0, ch);
                }
            }
        } else {
            // file not found
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            output.write(errorMessage.getBytes(StandardCharsets.UTF_8));
        }
    }
}
