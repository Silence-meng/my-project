package com.silence.enetity;

import lombok.Data;

/**
 * @author silence
 * @since 2025/3/27 10:34
 **/
@Data
public class JasyptDecryptReq {
    /**
     * 密码
     */
    private String password;

    /**
     * 加密文本
     */
    private String encryptText;
}
