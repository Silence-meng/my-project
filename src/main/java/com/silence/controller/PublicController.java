package com.silence.controller;

import com.silence.enetity.UploadStringReq;
import com.silence.service.UploadService;
import com.silence.utils.IPUtil;
import com.silence.utils.RequestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author silence
 * @since 2024/9/12 14:57
 **/
@RestController
@RequestMapping("silence/public")
@RequiredArgsConstructor
@Tag(name = "publicController", description = "公共接口")
public class PublicController {

    private final UploadService uploadService;

    @PostMapping("/getPublicIPAddress")
    @Operation(summary = "获取公网IP地址")
    public String hello() {
        return IPUtil.getClientIpAddr(RequestUtil.getRequest());
    }

    @PostMapping("/uploadString")
    @Operation(summary = "上传字符串")
    public String uploadString(@RequestBody UploadStringReq uploadStringReq) {
        boolean result = uploadService.uploadString(uploadStringReq);

        if (result) {
            return "success";
        }

        return "fail";
    }

    @GetMapping("/getUploadString")
    @Operation(summary = "获取上传字符串")
    public String getUploadString(@RequestParam("key") String key) {
        return uploadService.getUploadString(key);
    }
}
