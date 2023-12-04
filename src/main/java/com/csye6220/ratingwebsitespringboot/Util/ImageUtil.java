package com.csye6220.ratingwebsitespringboot.Util;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class ImageUtil {
    public static boolean GenerateImage(String base64Str, String imgFilePath) {
        if (base64Str == null) // 图像数据为空
            return false;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decode(base64Str);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getImageName() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        System.out.println("Generated Unique ID: " + uniqueId);
        return uniqueId;
    }


    public static String getImageAsBase64(String img_path, String img_type) {
        String base64Image = "";
        try {
            // 读取图片文件的字节数组
            byte[] imageBytes = readImageBytes(img_path);
            Base64.Encoder encoder = Base64.getEncoder();

            // 将字节数组转换为Base64编码的字符串
            base64Image = encoder.encodeToString(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常情况
        }
        StringBuilder builder = new StringBuilder();
        builder.append("data:image/");
        builder.append(img_type);
        builder.append(";base64,");
        builder.append(base64Image);

        return builder.toString();
    }

    public static byte[] readImageBytes(String imagePath) throws Exception {
        // 如果图片位于 resources 目录下，可以使用 ClassPathResource 获取资源
        // Resource resource = new ClassPathResource(imagePath);
        // InputStream inputStream = resource.getInputStream();

        // 如果图片在文件系统中，可以使用 Paths 获取文件路径
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }
}
