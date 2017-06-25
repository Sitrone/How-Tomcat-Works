package com.tomcat.ex02;

import java.nio.charset.StandardCharsets;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by Administrator on 2017/6/25.
 */
public class ServletProcessor {

    public void process(Request request, Response response) {
        String servletName = getServletName(request);

        try {
            ClassLoader loader = getServletClassLoader();
            Class<?> myClass = loader.loadClass(servletName);

            Servlet servlet = (Servlet) myClass.newInstance();
            response.getWriter().print(Constants.HTTP_OK);
            servlet.service(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     * @throws IOException
     */
    private ClassLoader getServletClassLoader() throws IOException {
        try {
            URL[] urls = new URL[1];
            URLStreamHandler urlStreamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);

            String repository = new URL("file", null, classPath.getCanonicalPath()) + File.separator;
            urls[0] = new URL(null, repository, urlStreamHandler);
            return URLClassLoader.newInstance(urls);
        } catch (IOException e) {
            throw new IOException("Failed to initialize servlet class loader.");
        }
    }

    private String getServletName(Request request) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        return servletName;
    }
}
