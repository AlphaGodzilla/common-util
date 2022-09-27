package com.github.alphagodzilla.internal;

/**
 * 程序包内部使用的字符串处理工具
 *
 * @author AlphaGodzilla
 * @date 2022/3/8 16:22
 */
public class InternalStrUtil {
    /**
     * 是否为空字符串
     *
     * @param value 待检查的字符串
     * @return true为空字符串，false为非空字符串
     */
    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
