package com.silence.enetity;

import cn.hutool.core.lang.UUID;
import lombok.Data;

/**
 * @author silence
 * @since 2025/5/30 16:23
 **/
@Data
public class ThirdAuthInfoReq {

    private String appSecret;

    private String appSecretId;

    private String appKey;

    private String tid = UUID.randomUUID().toString().replaceAll("-", "");

    private String algorithmVersion = "1.0";
}
