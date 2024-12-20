package com.silence.controller;

import com.silence.service.ImageService;
import com.silence.validator.ImageSize;
import com.silence.validator.ImageType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * @author silence
 * @since 2024/12/11 10:32
 **/
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@Tag(name = "imageController", description = "图片处理接口")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/resize")
    @Operation(summary = "根据文件大小缩放图片")
    public ResponseEntity<byte[]> resizePhoto(@RequestParam("file")
                                              @ImageSize(max = 10 * 1024 * 1024)
                                              @ImageType({"image/jpeg", "image/png"}) MultipartFile file,
                                              @RequestParam("targetSizeKb") int targetSizeKb) throws Exception {
        byte[] resizedImageBytes = imageService.resizeImageByFileSize(file.getBytes(), targetSizeKb * 1024);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(Objects.requireNonNull(file.getContentType())));
        headers.setContentLength(resizedImageBytes.length);

        return ResponseEntity.ok().headers(headers).body(resizedImageBytes);
    }

    @PostMapping("/resizeByWidthAndHeight")
    @Operation(summary = "根据宽度和高度缩放图片")
    public ResponseEntity<byte[]> resizePhotoByWidthAndHeight(@RequestParam("file")
                                                              @ImageSize(max = 10 * 1024 * 1024)
                                                              @ImageType({"image/jpeg", "image/png"}) MultipartFile file,
                                                              @RequestParam("targetWidth") int targetWidth,
                                                              @RequestParam("targetHeight") int targetHeight) throws Exception {
        byte[] resizedImageBytes = imageService.resizeImageByWidthAndHeight(file.getBytes(), targetWidth, targetHeight);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(Objects.requireNonNull(file.getContentType())));
        headers.setContentLength(resizedImageBytes.length);

        return ResponseEntity.ok().headers(headers).body(resizedImageBytes);
    }
}