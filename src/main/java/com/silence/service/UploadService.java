package com.silence.service;

import com.silence.enetity.UploadStringReq;

/**
 * @author silence
 * @since 2024/11/4 09:51
 **/
public interface UploadService {

    boolean uploadString(UploadStringReq uploadStringReq);

    String getUploadString(String key);
}
