package com.silence.enetity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author silence
 * @since 2025/2/24 15:33
 **/
@Data
public class UserInfo {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户密码")
    private String password;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "用户手机号")
    private String phone;

    @Schema(description = "用户头像地址")
    private String avatarUrl;
}
