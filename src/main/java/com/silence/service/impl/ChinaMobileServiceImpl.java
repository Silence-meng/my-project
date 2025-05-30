package com.silence.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.silence.enetity.ThirdAuthInfoReq;
import com.silence.service.ChinaMobileService;
import com.silence.utils.SignAlgorithmUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author silence
 * @since 2025/5/30 16:26
 **/
@Service
public class ChinaMobileServiceImpl implements ChinaMobileService {

    @Override
    public HashMap<String, String> generateThirdAuthInfo(ThirdAuthInfoReq req) {
        String appKey = req.getAppKey();
        String appSecretId = req.getAppSecretId();
        String appSecret = req.getAppSecret();
        String tid = req.getTid();
        String algorithmVersion = req.getAlgorithmVersion();
        String yunRand = calculateRand();

        HashMap<String, String> headerMap = new HashMap();

        String yunKeyAndSecretId = appKey + "|" + appSecretId;
        String yunTid = String.valueOf(tid);
        String yunAuth = SignAlgorithmUtil.getSign(yunKeyAndSecretId, appSecret, yunRand, yunTid);
        headerMap.put("x-yun-app-key", yunKeyAndSecretId);
        headerMap.put("x-yun-neauth", yunAuth);
        headerMap.put("x-yun-neauth-version", algorithmVersion);
        headerMap.put("x-yun-rand", yunRand);
        headerMap.put("x-yun-tid", yunTid);

        return headerMap;
    }

    private String calculateRand() {
        try {
            int randomInt = RandomUtil.randomInt(100000, 999999);
            return String.valueOf(randomInt) + System.currentTimeMillis();
        } catch (Exception var1) {
            return String.valueOf(899999);
        }
    }
}
