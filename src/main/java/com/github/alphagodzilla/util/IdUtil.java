package com.github.alphagodzilla.util;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author AlphaGodzilla
 * @date 2022/3/8 16:44
 */
public class IdUtil {
    /**
     * 返回去除中划线的uuid
     *
     * @return uuid
     */
    public static String simpleUuid() {
        return uuid().replace("-", "");
    }

    /**
     * UUID
     *
     * @return UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成递增类型的唯一ID
     *
     * @return ID
     */
    public static long nextId() {
        return nextId(System.currentTimeMillis());
    }

    /**
     * 生成递增类型的唯一ID
     *
     * @param timestampMillis 当下时间戳
     * @return ID
     */
    public static long nextId(long timestampMillis) {
        return (timestampMillis << 20) | (ThreadLocalRandom.current().nextLong(0, 1 << 20));
    }
}
