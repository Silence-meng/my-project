package com.silence.controller;

import com.silence.enetity.BaseResp;
import com.silence.enetity.Location;
import com.silence.enetity.MiddleLocationReq;
import com.silence.utils.IPUtil;
import com.silence.utils.RequestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author silence
 * @since 2024/6/14 15:36
 **/
@RestController
@RequestMapping("/silence/user")
@Tag(name = "userController", description = "用户相关接口")
public class UserController {

    @PostMapping("/getPublicIPAddress")
    @Operation(summary = "获取公网IP地址")
    public String hello() {
        return IPUtil.getClientIpAddr(RequestUtil.getRequest());
    }

    @PostMapping("/getMiddleLocation")
    public BaseResp<Location> getMiddleLocation(@RequestBody MiddleLocationReq middleLocationReq) {


        return BaseResp.success();
    }
}
