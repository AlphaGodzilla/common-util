package com.github.alpha.godzilla.time;

import com.github.alpha.godzilla.embedded.EmbeddedStrUtil;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author AlphaGodzilla
 * @date 2022/3/8 16:21
 */
public class TimestampUtil {
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp minus(Timestamp from, long n, TimeUnit timeUnit) {
        long timestamp = from.getTime();
        final long difference = TimeUnit.MILLISECONDS.convert(n, timeUnit);
        long targetTimestamp = timestamp - difference;
        return new Timestamp(targetTimestamp <= 0 ? 0 : targetTimestamp);
    }

    public static Timestamp minus(long n, TimeUnit timeUnit) {
        final long now = System.currentTimeMillis();
        final long difference = TimeUnit.MILLISECONDS.convert(n, timeUnit);
        long targetTimestamp = now - difference;
        return new Timestamp(targetTimestamp <= 0 ? 0 : targetTimestamp);
    }

    public static Timestamp plus(long from, long n, TimeUnit timeUnit) {
        final long difference = TimeUnit.MILLISECONDS.convert(n, timeUnit);
        return new Timestamp(from + difference);
    }

    public static Timestamp plus(long n, TimeUnit timeUnit) {
        final long now = System.currentTimeMillis();
        final long difference = TimeUnit.MILLISECONDS.convert(n, timeUnit);
        return new Timestamp(now + difference);
    }

    public static Timestamp fromLocalDateTime(LocalDateTime time) {
        return Timestamp.valueOf(time);
    }

    public static Timestamp fromDate(Date time) {
        return new Timestamp(time.getTime());
    }

    public static Timestamp fromMillis(long millis) {
        return new Timestamp(millis);
    }

    public static Timestamp fromInstant(Instant instant) {
        return new Timestamp(instant.toEpochMilli());
    }

    public static Timestamp parse(String value) {
        if (EmbeddedStrUtil.isEmpty(value)) {
            return null;
        }
        final LocalDateTime localDateTime = LocalDateTime.parse(value, DateTimeFormat.NORMAL_DATE_TIME_PATTERN.getFormatter());
        final Instant instant = ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant();
        return Timestamp.from(instant);
    }

    public static String utcFormat(Timestamp timestamp) {
        Instant instant = timestamp.toInstant();
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
        return zonedDateTime.format(DateTimeFormat.UTC_DATE_FORMAT.getFormatter());
    }

    public static String localFormat(Timestamp timestamp) {
        return localFormat(timestamp, DateTimeFormat.NORMAL_DATE_TIME_PATTERN.getFormatter());
    }

    public static String localFormat(Timestamp timestamp, DateTimeFormatter pattern) {
        if (timestamp == null) {
            return null;
        }
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return localDateTime.format(pattern);
    }

    public static String formatWithZone(Timestamp timestamp, ZoneId zone) {
        if (timestamp == null) {
            return null;
        }
        ZonedDateTime zonedDateTime = timestamp.toInstant().atZone(zone);
        return zonedDateTime.format(DateTimeFormat.NORMAL_DATE_TIME_WITH_ZONE_PATTERN.getFormatter());
    }
}
