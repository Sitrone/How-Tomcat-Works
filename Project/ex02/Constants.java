package com.tomcat.ex02;

import java.io.File;

/**
 * Created by Administrator on 2017/6/25.
 */
public final class Constants {
    private Constants() {
        throw new AssertionError();
    }

    /**
     * web resource root path
     */
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "webroot";

    public static final String HTTP_OK = "HTTP/1.1 200 OK\n";

    public static final String HTTP_NOT_FOUND = "HTTP/1.1 404 File Not Found\n";
}
