package com.tomcat.ex02;

import com.tomcat.ex01.HttpServer;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Created by Administrator on 2017/6/25.
 */
public class Response implements ServletResponse{
    private static final int BUFFER_SIZE = 1 << 10;

    private Request request;

    private OutputStream output;

    private PrintWriter writer;

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
                output.write(Constants.HTTP_OK.getBytes(StandardCharsets.UTF_8));
                output.write("Content-Type: text/html\r\n".getBytes(StandardCharsets.UTF_8));
                output.write("\r\n".getBytes(StandardCharsets.UTF_8));
                int ch = 0;
                while ((ch = fis.read(bytes, 0, BUFFER_SIZE)) != -1) {
                    output.write(bytes, 0, ch);
                }
            }
        } else {
            // file not found
            String errorMessage = Constants.HTTP_NOT_FOUND +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            output.write(errorMessage.getBytes(StandardCharsets.UTF_8));
        }
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        writer = new PrintWriter(output, true);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    @Override
    public void setContentLength(int len) {

    }

    @Override
    public void setContentLengthLong(long len) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public void setBufferSize(int size) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale loc) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
