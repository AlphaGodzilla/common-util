package com.github.alphagodzilla.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author AlphaGodzilla
 * @date 2022/3/8 16:49
 */
public class OsUtil {
    /**
     * 获取当前主机名
     *
     * @return 主机名
     */
    public static String getHostname() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }
}
