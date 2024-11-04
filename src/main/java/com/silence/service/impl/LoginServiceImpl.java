//package com.silence.service.impl;
//
//import com.silence.service.LoginService;
//import com.silence.utils.JwtUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
///**
// * @author silence
// * @since 2024/9/11 16:52
// **/
//@Slf4j
//@Service
//public class LoginServiceImpl implements LoginService {
//
//    @Override
//    public boolean verify(String token) {
//        return JwtUtil.validateToken(token);
//    }
//
//    @Override
//    public Long getValidUid(String token) {
//        if (!verify(token)) {
//            log.warn("token[{}]验证失败", token);
//
//            return null;
//        }
//
//        return Long.parseLong(JwtUtil.getUserId(token));
//    }
//
//    @Override
//    public boolean login(String username, String password) {
//        return false;
//    }
//
//
//}
