package com.silence.controller;

import com.silence.enetity.BaseResp;
import com.silence.enetity.Location;
import com.silence.enetity.MiddleLocationReq;
import com.silence.utils.IPUtil;
import com.silence.utils.RequestUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author silence
 * @since 2024/6/14 15:36
 **/
@RestController
@RequestMapping("/silence")
public class MyController {

    @PostMapping("/getPublicIPAddress")
    public String hello() {
        return IPUtil.getClientIpAddr(RequestUtil.getRequest());
    }

    @PostMapping("/getMiddleLocation")
    public BaseResp<Location> getMiddleLocation(@RequestBody MiddleLocationReq middleLocationReq) {


        return BaseResp.success();
    }
}
