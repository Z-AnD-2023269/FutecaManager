package com.andersonlopez.futecaManager.confings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;



@Configuration
public class CloudinaryConfig {
    @Bean
    Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dnd2hb0lg",
                "api_key", "291356244168927",
                "api_secret", "GAiTuo0TDtn14PKwbFlLuuFrdL0"
        ));
    }
}