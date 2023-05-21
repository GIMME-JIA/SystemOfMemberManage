package com.util;


import java.util.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * 转换java.sql.Timestamp为日期时间的字符串
     *
     * @param timestamp
     * @return： 输出格式formatter
     */
    public static String convertTimestamp2Str(Timestamp timestamp) {
        Instant instant = new Date(timestamp.getTime()).toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return formatter.format(localDateTime);
    }

    /**
     * 符合formatter格式的字符串转为Date
     *
     * @param str
     * @return
     */
    public static Date convertStr2Date(String str) {
        LocalDateTime localDateTime = LocalDateTime.parse(str, formatter);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static String convertDate2Str(Date birthDay) {

        Instant instant = birthDay.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return formatter.format(localDateTime);
    }
}





















