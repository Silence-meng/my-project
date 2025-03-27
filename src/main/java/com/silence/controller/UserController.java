package com.silence.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.silence.enetity.BaseResp;
import com.silence.enetity.UserInfo;
import com.silence.enetity.UserInfoResp;
import com.silence.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author silence
 * @since 2024/6/14 15:36
 **/
@RestController
@RequestMapping("/user")
@Tag(name = "userController", description = "用户相关接口")
@RequiredArgsConstructor
//@Validated
public class UserController {

    private final UserService userService;

//    private final UserInfoMapper userInfoMapper;

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public BaseResp<String> register(@RequestBody UserInfo userInfo) {
//        List<UserInfoPo> userInfoList = userInfoMapper.selectBatchIds(List.of(1, 2, 3));
//        System.out.println(userInfoList);
        return BaseResp.success();
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public BaseResp<String> login(@RequestBody String loginId) {
        StpUtil.login(loginId);
        return BaseResp.success();
    }

    @PostMapping("/userInfo")
    @Operation(summary = "获取用户信息")
    public BaseResp<UserInfoResp> getUserInfo() {


        return BaseResp.success();
    }
}
