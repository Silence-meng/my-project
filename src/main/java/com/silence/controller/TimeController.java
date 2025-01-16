package com.silence.controller;

import com.silence.enetity.BaseResp;
import com.silence.service.TimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author silence
 * @since 2024/12/15 02:30
 **/
@RestController
@RequestMapping("/time")
@RequiredArgsConstructor
@Tag(name = "TimeController", description = "时间相关接口")
public class TimeController {

    private final TimeService timeService;

    @RequestMapping("/timestampToTime")
    @Operation(summary = "时间戳转换为时间", description = "将时间戳转换为时间字符串")
    public BaseResp<String> timestampToTime(long timestamp) {
        return BaseResp.success(timeService.timestampToTime(timestamp));
    }

    @RequestMapping("/timeToTimestamp")
    @Operation(summary = "时间转换为时间戳", description = "将时间字符串转换为时间戳")
    public BaseResp<Long> timeToTimestamp(String time) {
        return BaseResp.success(timeService.timeToTimestamp(time));
    }
}
