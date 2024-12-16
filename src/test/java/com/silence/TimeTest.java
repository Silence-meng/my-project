package com.silence;

import com.silence.service.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author silence
 * @since 2024/12/15 02:38
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class TimeTest {

    @Autowired
    private TimeService timeService;

    @Test
    public void timestampToTimeTest() {
        String time = timeService.timestampToTime(1562774400000L);
        String expected = "2019-07-11 00:00:00";
        Assert.assertEquals(expected, time);
    }
}