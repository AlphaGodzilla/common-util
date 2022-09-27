package com.github.alphagodzilla.net;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author AlphaGodzilla
 * @date 2022/9/27 17:45
 */
public class IpUtil {
    public static final String LOCAL_IP = "127.0.0.1";
    public static final String UNKNOWN = "unknown";
    /**
     * IP v4<br>
     * 采用分组方式便于解析地址的每一个段
     */
    public final static String IPV4 = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)$";
    public final static Pattern IP_V4_PATTERN = Pattern.compile(IPV4);

    private IpUtil() {
    }

    public static String getIp(String xForwardedFor) {
        String ipAddress = null;
        try {
            ipAddress = xForwardedFor;
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                return "";
            }
            if (ipAddress.length() > 7) {
                List<String> ips = new ArrayList<>(List.of(ipAddress.split(","))).stream()
                        .map(i -> i.replace(" ", ""))
                        .filter(i -> !"".equals(i))
                        .collect(Collectors.toList());
                if (!ips.isEmpty()) {
                    Collections.reverse(ips);
                    for (final String ip : ips) {
                        if (!isInnerIP(ip)) {
                            ipAddress = ip;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        if (ipAddress == null) {
            ipAddress = "";
        }
        return ipAddress;
    }

    public static boolean isInnerIP(String ipAddress) {
        boolean isInnerIp;
        long ipNum = ipv4ToLong(ipAddress);

        long aBegin = ipv4ToLong("10.0.0.0");
        long aEnd = ipv4ToLong("10.255.255.255");

        long bBegin = ipv4ToLong("172.16.0.0");
        long bEnd = ipv4ToLong("172.31.255.255");

        long cBegin = ipv4ToLong("192.168.0.0");
        long cEnd = ipv4ToLong("192.168.255.255");

        isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd) || LOCAL_IP.equals(ipAddress);
        return isInnerIp;
    }

    /**
     * 根据ip地址(xxx.xxx.xxx.xxx)计算出long型的数据
     * 方法别名：inet_aton
     *
     * @param strIP IP V4 地址
     * @return long值
     */
    public static long ipv4ToLong(String strIP) {
        final Matcher matcher = IP_V4_PATTERN.matcher(strIP);
        if (matcher.matches()) {
            return matchAddress(matcher);
        }
        //		Validator.validateIpv4(strIP, "Invalid IPv4 address!");
        //		final long[] ip = Convert.convert(long[].class, StrUtil.split(strIP, CharUtil.DOT));
        //		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
        throw new IllegalArgumentException("Invalid IPv4 address!");
    }


    /**
     * 将匹配到的Ipv4地址的4个分组分别处理
     *
     * @param matcher 匹配到的Ipv4正则
     * @return ipv4对应long
     */
    public static long matchAddress(Matcher matcher) {
        long addr = 0;
        for (int i = 1; i <= 4; ++i) {
            addr |= Long.parseLong(matcher.group(i)) << 8 * (4 - i);
        }
        return addr;
    }


    /**
     * 指定IP的long是否在指定范围内
     *
     * @param userIp 用户IP
     * @param begin  开始IP
     * @param end    结束IP
     * @return 是否在范围内
     */
    public static boolean isInner(long userIp, long begin, long end) {
        return (userIp >= begin) && (userIp <= end);
    }
}
