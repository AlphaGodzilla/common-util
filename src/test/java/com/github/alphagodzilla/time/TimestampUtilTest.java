package com.github.alphagodzilla.time;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.github.alphagodzilla.time.DateTimeFormat.*;
import static org.junit.jupiter.api.Assertions.*;

class TimestampUtilTest {

    @Test
    void now() {
        long now = System.currentTimeMillis();
        // 程序运行也需要时间，所以除以100抹除因程序运行导致的测试异常问题
        assertEquals(now / 100, TimestampUtil.now().getTime() / 100);
    }

    @Test
    void minusFrom() {
        long ts = 1647571019386L;
        Timestamp timestamp = new Timestamp(ts);
        Timestamp minusTimestamp = TimestampUtil.minus(timestamp, 1, TimeUnit.MINUTES);
        assertEquals(ts - TimeUnit.MINUTES.toMillis(1), minusTimestamp.getTime());
    }

    @Test
    void minus() {
        Timestamp timestamp = TimestampUtil.minus(1, TimeUnit.SECONDS);
        long expect = (System.currentTimeMillis() - 1000) / 100;
        assertEquals(expect, timestamp.getTime() / 100);
    }

    @Test
    void plusFrom() {
        long ts = 1647571019386L;
        Timestamp timestamp = new Timestamp(ts);
        Timestamp plusTimestamp = TimestampUtil.plus(timestamp, 1, TimeUnit.MINUTES);
        assertEquals(ts + TimeUnit.MINUTES.toMillis(1), plusTimestamp.getTime());
    }

    @Test
    void plus() {
        Timestamp timestamp = TimestampUtil.plus(1, TimeUnit.SECONDS);
        long expect = (System.currentTimeMillis() + 1000) / 100;
        assertEquals(expect, timestamp.getTime() / 100);
    }

    @Test
    void fromLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = TimestampUtil.fromLocalDateTime(now);
        assertEquals(
                now.atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli(),
                timestamp.getTime()
        );
    }

    @Test
    void fromDate() {
        Date date = new Date();
        Timestamp timestamp = TimestampUtil.fromDate(date);
        assertEquals(date.getTime(), timestamp.getTime());
    }

    @Test
    void fromMillis() {
        long ts = 1647571019386L;
        Timestamp timestamp = TimestampUtil.fromMillis(ts);
        assertEquals(ts, timestamp.getTime());
    }

    @Test
    void fromInstant() {
        Instant now = Instant.now();
        Timestamp timestamp = TimestampUtil.fromInstant(now);
        assertEquals(now.toEpochMilli(), timestamp.getTime());
    }

    @Test
    void parse() {
        assertNull(TimestampUtil.parse(null));
        assertNull(TimestampUtil.parse(""));
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        String format = now.format(NORMAL_DATE_TIME.getFormatter());
        Timestamp timestamp = TimestampUtil.parse(format);
        assertNotNull(timestamp);
        assertEquals(timestamp.getTime() / 10000, now.toInstant().toEpochMilli() / 10000);
    }

    @Test
    void utcFormat() {
        String utcTime = "2022-03-01T12:00:00";
        long ts = LocalDateTime.parse(utcTime, UTC_DATE_TIME.getFormatter()).toInstant(ZoneOffset.UTC).toEpochMilli();
        String format = TimestampUtil.utcFormat(new Timestamp(ts));
        assertEquals(utcTime, format);
    }

    @Test
    void localFormat() {
        String localDateTime = "2022-03-01 12:00:00";
        LocalDateTime parse = LocalDateTime.parse(localDateTime, NORMAL_DATE_TIME.getFormatter());
        long ts = parse.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        String format = TimestampUtil.localFormat(new Timestamp(ts));
        assertEquals(localDateTime, format);
    }

    @Test
    void formatWithZone() {
        String time = "2022-03-18 11:21:31 +0800";
        long ts = ZonedDateTime
                .parse(time, NORMAL_DATE_TIME_WITH_ZONE.getFormatter())
                .toInstant().toEpochMilli();
        String zoneTime = TimestampUtil.formatWithZone(new Timestamp(ts), ZoneId.systemDefault());
        assertEquals(time, zoneTime);
    }
}
