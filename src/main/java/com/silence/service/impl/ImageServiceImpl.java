package com.silence.service.impl;

import com.silence.service.ImageService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author silence
 * @since 2024/12/11 10:41
 **/
@Service
public class ImageServiceImpl implements ImageService {

    /**
     * 根据目标文件大小调整图片质量
     * 该方法通过调整JPEG图片的质量来减小图片的字节大小，直到达到或低于目标文件大小
     *
     * @param originalImageBytes 原始图片的字节数组
     * @param targetSizeBytes 目标文件大小，单位为字节
     * @return 调整后的图片字节数组
     * @throws IOException 如果图片读写过程中发生错误
     */
    @Override
    public byte[] resizeImageByFileSize(byte[] originalImageBytes, int targetSizeBytes) throws IOException {
        // 将原始图片字节数组转换为BufferedImage对象
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(originalImageBytes));
        // 创建一个字节数组输出流，用于存储调整后的图片字节数据
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 初始化图片质量为最高（1.0）
        double quality = 1.0;
        // 当图片质量大于0.1时，尝试以当前质量压缩图片
        while (quality > 0.1) {
            // 重置输出流，以便存储新的压缩后的图片数据
            outputStream.reset();
            // 使用Thumbnailator库调整图片质量并写入输出流
            Thumbnails.of(originalImage)
                    // 保持原图比例
                    .scale(1.0)
                    // 设置压缩质量
                    .outputQuality(quality)
                    // 设置输出格式为JPEG
                    .outputFormat("jpg")
                    .toOutputStream(outputStream);

            // 如果当前压缩后的图片文件大小小于或等于目标文件大小，则退出循环
            if (outputStream.size() <= targetSizeBytes) {
                break;
            }

            // 调整图片质量，减少0.1
            quality -= 0.1;
        }

        // 返回调整后的图片字节数组
        return outputStream.toByteArray();
    }
}
