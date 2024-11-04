//package com.silence.config;
//
//import com.silence.utils.JwtUtil;
//import jakarta.annotation.PostConstruct;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author silence
// * @since 2024/9/12 09:22
// **/
//@Data
//@Configuration
//public class UtilFiledConfig {
//
//    @Value("${jwt.secret-key:14848bc7d4a7ab226cb67ebb9d5f27ce71f2168ec434cea17b3d8dd53ac8156b}")
//    private String jwtSecretKey;
//
//    @Value("${jwt.expire-time:604800}")
//    private Integer jwtExpireTime;
//
//    @Value("${jwt.issuer:silence}")
//    private String jwtIssuer;
//
//    @Value("${jwt.subject:Peripherals}")
//    private String jwtSubject;
//
//    @PostConstruct
//    public void init() {
//        JwtUtil.setConfig(this);
//    }
//}
