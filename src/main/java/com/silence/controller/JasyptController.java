package com.silence.controller;

import com.silence.enetity.JasyptDecryptReq;
import com.silence.enetity.JasyptEncryptReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author silence
 * @since 2025/3/27 10:20
 **/
@RestController
@RequestMapping("/jasypt")
@Tag(name = "jasyptController", description = "jasypt加密解密接口")
public class JasyptController {

    @PostMapping("/encrypt")
    @Operation(summary = "加密接口", description = "加密接口")
    public String encrypt(@RequestBody JasyptEncryptReq req) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(req.getPassword());

        return encryptor.encrypt(req.getText());
    }

    @PostMapping("/decrypt")
    @Operation(summary = "解密接口", description = "解密接口")
    public String decrypt(@RequestBody JasyptDecryptReq req) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(req.getPassword());

        return encryptor.decrypt(req.getEncryptText());
    }
}
