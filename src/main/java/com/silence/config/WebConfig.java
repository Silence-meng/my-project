//package com.silence.config;
//
//import com.silence.interceptor.CollectorInterceptor;
//import com.silence.interceptor.TokenInterceptor;
//import lombok.RequiredArgsConstructor;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author silence
// * @since 2024/9/12 11:27
// **/
//@Configuration
//@RequiredArgsConstructor
//public class WebConfig implements WebMvcConfigurer {
//
//    private final TokenInterceptor tokenInterceptor;
//
//    private final CollectorInterceptor collectorInterceptor;
//
//    @Override
//    public void addInterceptors(@NotNull InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor).addPathPatterns("/silence/**");
//        registry.addInterceptor(collectorInterceptor).addPathPatterns("/silence/**");
//    }
//}
