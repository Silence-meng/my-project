package com.silence.service.impl;

import com.silence.enetity.UploadStringReq;
import com.silence.service.UploadService;
import com.silence.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author silence
 * @since 2024/11/4 09:52
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final RedisUtil redisUtil;

    @Override
    public boolean uploadString(UploadStringReq uploadStringReq) {
        boolean result = redisUtil.set(uploadStringReq.getKey(), uploadStringReq.getContent());
        if (result) {
            log.info("上传成功，key:{}, content:{}", uploadStringReq.getKey(), uploadStringReq.getContent());
        } else {
            log.error("上传失败，key:{}, content:{}", uploadStringReq.getKey(), uploadStringReq.getContent());
        }

        return result;
    }

    @Override
    public String getUploadString(String key) {
        return (String) redisUtil.get(key);
    }
}
