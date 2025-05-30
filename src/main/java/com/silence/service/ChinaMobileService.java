package com.silence.service;

import com.silence.enetity.ThirdAuthInfoReq;

import java.util.HashMap;

/**
 * @author silence
 * @since 2025/5/30 16:20
 **/
public interface ChinaMobileService {

    HashMap<String, String> generateThirdAuthInfo(ThirdAuthInfoReq req);
}
