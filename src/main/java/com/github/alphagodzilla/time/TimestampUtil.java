package com.github.alphagodzilla.time;

import com.github.alphagodzilla.internal.InternalStrUtil;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author AlphaGodzilla
 * @date 2022/3/8 16:21
 */
public class TimestampUtil {
    /**
     * 返回当前系统时间的时间戳
     *
     * @return 当前时间
     */
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp zero() {
        return new Timestamp(0);
    }

    /**
     * 从指定时间减去一段时间
     *
     * @param from     指定时刻
     * @param n        减去的时间数
     * @param timeUnit 时间单位
     * @return 减去一段时间的时间戳
     */
    public static Timestamp minus(Timestamp from, long n, TimeUnit timeUnit) {
        long timestamp = from.getTime();
        final long difference = TimeUnit.MILLISECONDS.convert(n, timeUnit);
        long targetTimestamp = timestamp - difference;
        return new Timestamp(targetTimestamp <= 0 ? 0 : targetTimestamp);
    }

    /**
     * 当前时间减去一段时间
     *
     * @param n        减去的时间数
     * @param timeUnit 时间单位
     * @return 减去一段时间的时间戳
     */
    public static Timestamp minus(long n, TimeUnit timeUnit) {
        return minus(now(), n, timeUnit);
    }

    public static Timestamp plus(Duration duration) {
        return plus(duration.toMillis(), TimeUnit.MILLISECONDS);
    }

    public static Timestamp plus(Timestamp from, Duration duration) {
        return plus(from, duration.toMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 从指定时间加上一段时间
     *
     * @param from     指定时间
     * @param n        增加的时间数
     * @param timeUnit 时间单位
     * @return 加上一段时间的时间戳
     */
    public static Timestamp plus(Timestamp from, long n, TimeUnit timeUnit) {
        final long difference = TimeUnit.MILLISECONDS.convert(n, timeUnit);
        return new Timestamp(from.getTime() + difference);
    }

    public static Timestamp plus(long from, long n, TimeUnit timeUnit) {
        final long difference = TimeUnit.MILLISECONDS.convert(n, timeUnit);
        return new Timestamp(from + difference);
    }

    /**
     * 当前时间加上一度时间
     *
     * @param n        增加的时间数
     * @param timeUnit 时间单位
     * @return 加上一段时间的时间戳
     */
    public static Timestamp plus(long n, TimeUnit timeUnit) {
        return plus(now(), n, timeUnit);
    }

    public static Timestamp tomorrowBegin(ZoneId zoneId) {
        return fromInstant(ZonedDateTime.now(zoneId).plusDays(1).truncatedTo(ChronoUnit.DAYS).toInstant());
    }

    /**
     * 从LocalDateTime对象转换成Timestamp对象
     *
     * @param time LocalDateTime
     * @return 时间戳
     */
    public static Timestamp fromLocalDateTime(LocalDateTime time) {
        return Timestamp.valueOf(time);
    }

    /**
     * 从Date对象转换成Timestamp对象
     *
     * @param time Date对象
     * @return 时间戳
     */
    public static Timestamp fromDate(Date time) {
        return new Timestamp(time.getTime());
    }

    /**
     * 从毫秒值转换为Timestamp对象
     *
     * @param millis 时间戳毫秒值
     * @return 时间戳
     */
    public static Timestamp fromMillis(long millis) {
        return new Timestamp(millis);
    }

    public static Timestamp fromMillis(String millis) {
        return fromMillis(Long.parseLong(millis));
    }

    public static Timestamp fromSeconds(long seconds) {
        return new Timestamp(seconds * 1000);
    }

    public static Timestamp fromSeconds(String seconds) {
        return fromSeconds(Long.parseLong(seconds));
    }

    /**
     * 从Instant对象转换成Timestamp对象
     *
     * @param instant Instant对象
     * @return 时间戳
     */
    public static Timestamp fromInstant(Instant instant) {
        return new Timestamp(instant.toEpochMilli());
    }

    /**
     * 从字符串解析为Timestamp对象。接收的时间字符串格式（yyyy-MM-dd HH:mm:ss），时区使用系统默认时区进行解析
     *
     * @param value 时间字符串
     * @return 时间戳
     */
    public static Timestamp parse(String value) {
        if (InternalStrUtil.isEmpty(value)) {
            return null;
        }
        final LocalDateTime localDateTime = LocalDateTime.parse(value, DateTimeFormat.NORMAL_DATE_TIME.getFormatter());
        final Instant instant = ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant();
        return Timestamp.from(instant);
    }

    /**
     * 格式化成UTC格式的时间字符串
     *
     * @param timestamp 时间戳对象
     * @return UTC格式的时间字符串，格式（yyyy-MM-dd'T'HH:mm:ss）
     */
    public static String utcFormat(Timestamp timestamp) {
        Instant instant = timestamp.toInstant();
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
        return zonedDateTime.format(DateTimeFormat.UTC_DATE_TIME.getFormatter());
    }

    /**
     * 格式化成本地时区的时间字符串
     *
     * @param timestamp 时间戳对象
     * @return 本地时区的时间字符串，格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String localFormat(Timestamp timestamp) {
        return localFormat(timestamp, DateTimeFormat.NORMAL_DATE_TIME.getFormatter());
    }

    /**
     * 格式化成本地时区的时间字符串
     *
     * @param timestamp 时间戳对象
     * @param pattern   格式化模板
     * @return 本地时区的时间字符串
     */
    public static String localFormat(Timestamp timestamp, DateTimeFormatter pattern) {
        if (timestamp == null) {
            return null;
        }
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return localDateTime.format(pattern);
    }

    public static String formatWithZone(Timestamp timestamp, DateTimeFormatter pattern, ZoneId zone) {
        if (timestamp == null) {
            return null;
        }
        ZonedDateTime zonedDateTime = timestamp.toInstant().atZone(zone);
        return zonedDateTime.format(pattern);
    }

    /**
     * 格式化成带时区显示的时间字符串
     *
     * @param timestamp 时间戳对象
     * @param zone      时区
     * @return 带时区显示的时间字符串，格式（yyyy-MM-dd HH:mm:ss Z）
     */
    public static String formatWithZone(Timestamp timestamp, ZoneId zone) {
        if (timestamp == null) {
            return null;
        }
        ZonedDateTime zonedDateTime = timestamp.toInstant().atZone(zone);
        return zonedDateTime.format(DateTimeFormat.NORMAL_DATE_TIME_WITH_ZONE.getFormatter());
    }
}
