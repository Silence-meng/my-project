package com.silence.service;

/**
 * @author silence
 * @since 2024/12/15 02:35
 **/
public interface TimeService {

    /**
     * 时间戳转时间
     *
     * @param timestamp 时间戳
     * @return 时间字符串
     */
    String timestampToTime(String timestamp);

    /**
     * 时间转时间戳
     *
     * @param time 时间字符串
     * @return 时间戳
     */
    Long timeToTimestamp(String time);
}
