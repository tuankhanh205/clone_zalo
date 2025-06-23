package org.example.clonezalo.config.cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Map<String,String> config=new HashMap<>();
        config.put("cloud_name","dsmnr5drb");
        config.put("cloud_secret","dywvgfdcKH3YsWeIlAZn-A1uPzU");
        config.put("cloud_key","242619454497932");
        return new Cloudinary(config);
    }

}
