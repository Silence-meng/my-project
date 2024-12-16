package com.silence.service.impl;

import com.silence.service.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author silence
 * @since 2024/12/15 02:36
 **/
@Slf4j
@Service
public class TimeServiceImpl implements TimeService {

    @Override
    public String timestampToTime(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}