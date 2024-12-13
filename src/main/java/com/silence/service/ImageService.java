package com.silence.service;

import java.io.IOException;

/**
 * @author silence
 * @since 2024/12/11 10:40
 **/
public interface ImageService {

    /**
     * 获取图片类型
     *
     * @param bytes 图片字节数组
     * @return 图片类型
     */
    String getImageType(byte[] bytes) throws IOException;

    /**
     * 压缩图片到指定大小
     *
     * @param originalImageBytes 原始图片字节数组
     * @param targetSizeBytes    目标图片大小（字节）
     * @return 压缩后的图片字节数组
     * @throws IOException IO异常
     */
    byte[] resizeImageByFileSize(byte[] originalImageBytes, int targetSizeBytes) throws IOException;

    /**
     * 压缩图片到指定宽度和高度
     *
     * @param bytes 原始图片字节数组
     * @param targetWidth 目标宽度
     * @param targetHeight 目标高度
     * @return 压缩后的图片字节数组
     */
    byte[] resizeImageByWidthAndHeight(byte[] bytes, int targetWidth, int targetHeight) throws IOException;
}
