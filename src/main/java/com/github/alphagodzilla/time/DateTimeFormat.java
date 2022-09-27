package com.github.alphagodzilla.time;

import java.time.format.DateTimeFormatter;

/**
 * 常用的事件格式化类型
 *
 * @author AlphaGodzilla
 * @date 2022/3/8 16:34
 */
public enum DateTimeFormat {
    /*
     * */
    DATA_FORMAT_COMPACT("yyyyMMdd"),
    DATA_TIME_FORMAT_MINUTE_COMPACT("yyyyMMddHHmm"),
    DATA_TIME_FORMAT_COMPACT("yyyyMMddHHmmss"),
    DATE_TIME_MILLS_WITH_ZONE_COMPACT("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
    DATE_TIME_WITH_ZONE_COMPACT("yyyyMMdd'T'HHmmss'Z'"),

    NORMAL_DATE_TIME("yyyy-MM-dd HH:mm:ss"),
    NORMAL_DATE_TIME_WITH_ZONE("yyyy-MM-dd HH:mm:ss Z"),
    UTC_DATE_TIME("yyyy-MM-dd'T'HH:mm:ss"),
    ;

    private final DateTimeFormatter formatter;

    DateTimeFormat(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
