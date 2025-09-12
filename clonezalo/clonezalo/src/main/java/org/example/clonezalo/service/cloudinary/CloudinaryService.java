package org.example.clonezalo.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) {
        try {
            // Upload file lên Cloudinary
            Map<String, Object> data = cloudinary.uploader().upload(file.getBytes(), Map.of());

            // Lấy URL từ map trả về
            String url = (String) data.get("secure_url");

            return url;
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }
}



