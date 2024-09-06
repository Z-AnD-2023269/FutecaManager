package com.andersonlopez.futecaManager.confings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfing {

    @Bean
    Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dakswuxsc",
            "api_key", "386879135494191",
            "api_secret", "ubxvJmWQCN813pZgPc92HpqiWns"
        ));
    }

}
