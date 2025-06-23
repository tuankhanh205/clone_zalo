package org.example.clonezalo.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;
    public String uploadFile(MultipartFile file) throws IOException {
        String contentType=file.getContentType();
        String resourceType=contentType!=null&& contentType.startsWith("video")?"video":"image";
        Map uploadResult=cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type",resourceType));
        return String.valueOf(uploadResult.get("secure_url"));
    }


}
