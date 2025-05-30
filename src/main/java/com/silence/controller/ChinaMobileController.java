package com.silence.controller;

import com.silence.enetity.ThirdAuthInfoReq;
import com.silence.service.ChinaMobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author silence
 * @since 2025/5/30 16:18
 **/
@RestController
@RequestMapping("/chinamobile")
@RequiredArgsConstructor
public class ChinaMobileController {

    private final ChinaMobileService chinaMobileService;

    @PostMapping("/generateThirdAuthInfo")
    public String generateThirdAuthInfo(@RequestBody ThirdAuthInfoReq req) {
        Map<String, String> result = chinaMobileService.generateThirdAuthInfo(req);

        return buildResult(result);
    }

    private String buildResult(Map<String, String> result) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : result.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
        }

        // 去掉最后的换行符
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
