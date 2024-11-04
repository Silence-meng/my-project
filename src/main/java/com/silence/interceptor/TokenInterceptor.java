//package com.silence.interceptor;
//
//import com.silence.constant.MDCKey;
//import com.silence.service.LoginService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.jetbrains.annotations.NotNull;
//import org.slf4j.MDC;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.util.Objects;
//import java.util.Optional;
//
///**
// * @author silence
// * @since 2024/9/11 16:41
// **/
//@Order(-2)
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class TokenInterceptor implements HandlerInterceptor {
//
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//
//    public static final String AUTHORIZATION_SCHEMA = "Bearer ";
//
//    public static final String ATTRIBUTE_UID = "uid";
//
//    private final LoginService loginService;
//
//    @Override
//    public boolean preHandle(@NotNull HttpServletRequest request,
//                             @NotNull HttpServletResponse response,
//                             @NotNull Object handler) throws Exception {
//        //获取用户登录token
//        String token = getToken(request);
//        Long validUid = loginService.getValidUid(token);
//        //有登录态
//        if (Objects.nonNull(validUid)) {
//            request.setAttribute(ATTRIBUTE_UID, validUid);
//        } else {
//            boolean isPublicUri = isPublicUri(request.getRequestURI());
//            //又没有登录态，又不是公开路径，直接401
//            if (!isPublicUri) {
////                HttpErrorEnum.ACCESS_DENIED.sendHttpError(response);
//                return false;
//            }
//        }
//        MDC.put(MDCKey.UID, String.valueOf(validUid));
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(@NotNull HttpServletRequest request,
//                                @NotNull HttpServletResponse response,
//                                @NotNull Object handler, Exception ex) throws Exception {
//        MDC.remove(MDCKey.UID);
//    }
//
//    /**
//     * 判断是不是公共方法，可以未登录访问的
//     *
//     * @param requestUri 请求路径
//     */
//    private boolean isPublicUri(String requestUri) {
//        String[] split = requestUri.split("/");
//        return split.length > 2 && "public".equals(split[3]);
//    }
//
//    private String getToken(HttpServletRequest request) {
//        String header = request.getHeader(AUTHORIZATION_HEADER);
//        return Optional.ofNullable(header)
//                .filter(h -> h.startsWith(AUTHORIZATION_SCHEMA))
//                .map(h -> h.substring(AUTHORIZATION_SCHEMA.length()))
//                .orElse(null);
//    }
//}
