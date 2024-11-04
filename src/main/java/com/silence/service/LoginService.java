package com.silence.service;

/**
 * @author silence
 * @since 2024/9/11 16:49
 **/
public interface LoginService {

    /**
     * 校验token是否有效
     *
     * @param token token
     * @return true or false
     */
    boolean verify(String token);

    /**
     * 获取token对应的用户id
     *
     * @param token token
     * @return 用户id
     */
    Long getValidUid(String token);

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return true or false
     */
    boolean login(String username, String password);
}
