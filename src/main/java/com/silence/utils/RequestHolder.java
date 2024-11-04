package com.silence.utils;

import com.silence.enetity.RequestInfo;
import lombok.experimental.UtilityClass;

/**
 * @author silence
 * @since 2024/9/11 16:32
 **/
@UtilityClass
public class RequestHolder {

    private static final ThreadLocal<RequestInfo> REQUEST_INFO_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(RequestInfo requestInfo) {
        REQUEST_INFO_THREAD_LOCAL.set(requestInfo);
    }

    public static RequestInfo get() {
        return REQUEST_INFO_THREAD_LOCAL.get();
    }

    public static void remove() {
        REQUEST_INFO_THREAD_LOCAL.remove();
    }
}
