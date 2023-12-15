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
        if (base64Str == null) // Data is empty
            return false;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // Base64 decode
            byte[] bytes = decoder.decode(base64Str);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            // Build JPG picture
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Get a random image name by using UUID
    public static String getImageName() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        System.out.println("Generated Unique ID: " + uniqueId);
        return uniqueId;
    }


    public static String getImageAsBase64(String img_path, String img_type) {
        String base64Image = "";
        try {
            // Get bytes array
            byte[] imageBytes = readImageBytes(img_path);
            Base64.Encoder encoder = Base64.getEncoder();

            // Transfer to Base64 String
            base64Image = encoder.encodeToString(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        builder.append("data:image/");
        builder.append(img_type);
        builder.append(";base64,");
        builder.append(base64Image);

        return builder.toString();
    }

    // Get image from path
    public static byte[] readImageBytes(String imagePath) throws Exception {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }
}
