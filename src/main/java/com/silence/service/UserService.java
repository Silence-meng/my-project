package com.silence.service;

import com.silence.enetity.UserInfo;

/**
 * @author silence
 * @since 2025/2/25 14:26
 **/
public interface UserService {

    /**
     * 注册用户
     * @param userInfo 用户信息
     */
    void registerUser(UserInfo userInfo);
}
