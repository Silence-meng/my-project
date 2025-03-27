package com.silence.enetity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author silence
 * @since 2025/3/27 10:32
 **/
@Data
public class JasyptEncryptReq {
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 待加密的字符串
     */
    @Schema(description = "待加密的字符串")
    private String text;
}
