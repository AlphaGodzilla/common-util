package com.github.alpha.godzilla.time;

import java.time.format.DateTimeFormatter;

/**
 * @author AlphaGodzilla
 * @date 2022/3/8 16:34
 */
public enum DateTimeFormat {
    /**
     * 常用的事件格式化类型
     */
    DATA_FORMAT_COMPACT("yyyyMMddHHmmss"),
    NORMAL_DATE_TIME_PATTERN("yyyy-MM-dd HH:mm:ss"),
    NORMAL_DATE_TIME_WITH_ZONE_PATTERN("yyyy-MM-dd HH:mm:ss Z"),
    DATA_FORMAT_MINUTE_COMPACT("yyyyMMddHHmm"),
    UTC_DATE_FORMAT("yyyy-MM-dd'T'HH:mm:ss"),
    ;

    private final DateTimeFormatter formatter;

    DateTimeFormat(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
