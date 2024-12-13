package com.silence.controller;

import com.silence.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author silence
 * @since 2024/12/11 10:32
 **/
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/resize")
    public ResponseEntity<byte[]> resizePhoto(@RequestParam("file") MultipartFile file,
                                              @RequestParam("targetSizeKb") int targetSizeKb) throws Exception{
        byte[] resizedImageBytes = imageService.resizeImageByFileSize(file.getBytes(), targetSizeKb * 1024);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(resizedImageBytes.length);

        return ResponseEntity.ok().headers(headers).body(resizedImageBytes);
    }
}
