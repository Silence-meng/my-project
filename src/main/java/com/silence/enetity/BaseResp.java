package com.silence.enetity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author silence
 * @since 2024/6/27 14:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResp<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    public static <T> BaseResp<T> success() {
        return new BaseResp<>(0, "success", null);
    }

    public static <T> BaseResp<T> success(T data) {
        return new BaseResp<>(0, "success", data);
    }


    public static <T> BaseResp<T> fail() {
        return new BaseResp<>(-1, "fail", null);
    }

    public static <T> BaseResp<T> fail(String msg) {
        return new BaseResp<>(-1, msg, null);
    }

    public static <T> BaseResp<T> fail(int code, String msg) {
        return new BaseResp<>(code, msg, null);
    }
}
