package com.tomcat.ex02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/6/25.
 */
public class HttpServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }

    private void await() {
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            System.out.println("Failed to create");
            e.printStackTrace();
            System.exit(0);
        }
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();

                // create Request and Parse
                Request request = new Request(input);
                request.parse();

                // check if the previour URI is a shutdown command
                shutdown = request.getUri().equalsIgnoreCase(SHUTDOWN_COMMAND);
                if (shutdown) {
                    break;
                }

                // create Response object
                Response response = new Response(output);
                response.setRequest(request);

                // 只支持两种形式的url
                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
