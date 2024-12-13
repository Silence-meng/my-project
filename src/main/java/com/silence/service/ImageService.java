package com.silence.service;

import java.io.IOException;

/**
 * @author silence
 * @since 2024/12/11 10:40
 **/
public interface ImageService {

    /**
     * 压缩图片到指定大小
     *
     * @param originalImageBytes 原始图片字节数组
     * @param targetSizeBytes 目标图片大小（字节）
     * @return 压缩后的图片字节数组
     * @throws IOException IO异常
     */
    byte[] resizeImageByFileSize(byte[] originalImageBytes, int targetSizeBytes) throws IOException;
}
