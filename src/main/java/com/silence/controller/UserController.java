package com.silence.controller;

import com.silence.enetity.BaseResp;
import com.silence.enetity.Location;
import com.silence.enetity.UserInfoResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@RequestMapping("/silence/user")
@Tag(name = "userController", description = "用户相关接口")
@RequiredArgsConstructor
//@Validated
public class UserController {

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public BaseResp<String> login(@Valid @RequestBody Location location) {
        System.out.println(location);
        return BaseResp.success();
    }

    @PostMapping("/userInfo")
    @Operation(summary = "获取用户信息")
    public BaseResp<UserInfoResp> getUserInfo() {


        return BaseResp.success();
    }
}
