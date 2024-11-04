//package com.silence.interceptor;
//
//import com.silence.enetity.RequestInfo;
//import com.silence.utils.IPUtil;
//import com.silence.utils.RequestHolder;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.util.Optional;
//
///**
// * @author silence
// * @since 2024/9/11 16:36
// **/
//@Order(1)
//@Slf4j
//@Component
//public class CollectorInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(@NotNull HttpServletRequest request,
//                             @NotNull HttpServletResponse response,
//                             @NotNull Object handler) throws Exception {
//        RequestInfo info = new RequestInfo();
//        info.setUid(Optional.ofNullable(request.getAttribute(TokenInterceptor.ATTRIBUTE_UID))
//                .map(Object::toString).map(Long::parseLong).orElse(null));
//        info.setIp(IPUtil.getClientIpAddr(request));
//        log.info("Request Info: {}", info);
//        RequestHolder.set(info);
//
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(@NotNull HttpServletRequest request,
//                                @NotNull HttpServletResponse response,
//                                @NotNull Object handler, Exception ex) throws Exception {
//        RequestHolder.remove();
//    }
//}
