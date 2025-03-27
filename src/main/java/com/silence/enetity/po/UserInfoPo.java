package com.silence.enetity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author silence
 * @since 2025/2/26 16:59
 **/
@TableName("user_info")
@Data
public class UserInfoPo {

//    @TableId
    private Integer id;

    private String username;

    private String password;

    private String email;
}
