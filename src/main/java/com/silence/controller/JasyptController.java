package com.silence.controller;

import com.silence.enetity.JasyptDecryptReq;
import com.silence.enetity.JasyptEncryptReq;
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
public class JasyptController {

    @PostMapping("/encrypt")
    public String encrypt(@RequestBody JasyptEncryptReq req) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(req.getPassword());

        return encryptor.encrypt(req.getText());
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody JasyptDecryptReq req) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(req.getPassword());

        return encryptor.decrypt(req.getEncryptText());
    }
}
