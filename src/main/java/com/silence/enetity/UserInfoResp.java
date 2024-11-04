package com.silence.enetity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author silence
 * @since 2024/9/11 16:13
 **/
@Data
@Schema(description = "用户信息响应")
public class UserInfoResp {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private String id;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String name;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatar;

    /**
     * 用户性别
     */
    @Schema(description = "用户性别 1-男 2-女 0-未知")
    private Integer sex;
}
