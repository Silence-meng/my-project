package com.silence.enetity;

import lombok.Data;

/**
 * @author silence
 * @since 2024/9/14 09:12
 **/
@Data
public class LoginReq {

    private String userName;

    private String mobile;

    private String email;

    private String password;

    private String captcha;
}
