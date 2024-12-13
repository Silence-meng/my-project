package com.silence.service.impl;

import com.silence.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author silence
 * @since 2024/12/11 10:41
 **/
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String getImageType(byte[] bytes) throws IOException {
        Tika tika = new Tika();
        String mimeType = tika.detect(bytes);

        if (mimeType.startsWith("image/")) {
            return mimeType.substring(6);
        } else {
            throw new IOException("Unknown image type.");
        }
    }

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
        // 检查目标文件大小是否有效
        if (targetSizeBytes <= 0) {
            throw new IllegalArgumentException("目标文件大小必须大于0");
        }

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(originalImageBytes);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            // 将原始图片字节数组转换为BufferedImage对象
            BufferedImage originalImage = ImageIO.read(inputStream);
            if (originalImage == null) {
                throw new IOException("无法读取图片");
            }

            // 获取输入图片的格式
            String imageType = getImageType(originalImageBytes);
            if (imageType == null) {
                throw new IOException("无法识别图片格式");
            }

            // 初始化图片质量为最高（1.0）
            double quality = 1.0;

            // 当图片质量大于0.1时，尝试以当前质量压缩图片
            while (quality > 0.1) {
                // 清空输出流，以便存储新的压缩后的图片数据
                outputStream.reset();

                try {
                    // 使用Thumbnailator库调整图片质量并写入输出流
                    Thumbnails.of(originalImage)
                            // 保持原图比例
                            .scale(1.0)
                            // 设置压缩质量
                            .outputQuality(quality)
                            // 设置输出格式为原图格式
                            .outputFormat(imageType)
                            .toOutputStream(outputStream);
                } catch (IOException e) {
                    throw new IOException("图片压缩过程中发生错误", e);
                }

                // 如果当前压缩后的图片文件大小小于或等于目标文件大小，则退出循环
                if (outputStream.size() <= targetSizeBytes) {
                    break;
                }

                // 调整图片质量，减少0.1
                quality -= 0.1;
            }

            // 返回调整后的图片字节数组
            return outputStream.toByteArray();
        } catch (IOException e) {
            // 记录异常信息
            log.error("图片压缩失败", e);
            throw e;
        }
    }

    @Override
    public byte[] resizeImageByWidthAndHeight(byte[] bytes, int targetWidth, int targetHeight) throws IOException {
        // 读取输入字节数组为BufferedImage
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
            BufferedImage originalImage = ImageIO.read(bais);

            if (originalImage == null) {
                throw new IOException("Failed to read the image");
            }

            // 创建一个新的BufferedImage用于存储缩放后的图像
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());

            // 使用Graphics2D进行图像缩放
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
            g2d.dispose();

            // 获取输入图像的格式
            String imageType = getImageType(bytes);

            // 将缩放后的BufferedImage写入ByteArrayOutputStream
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(resizedImage, imageType, baos);

                // 返回字节数组
                return baos.toByteArray();
            }
        }
    }
}
