package com.project.ecommerce.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
@Getter
public class S3Properties {

    // Getters and Setters
    @Value("${s3.access-key-id}")
    private String accessKeyId;
    @Value("${s3.secret-access-key}")
    private String secretAccessKey;
    @Value("${s3.bucket-region}")
    private String bucketRegion;
    @Value("${s3.bucket-name}")
    private String bucketName;

}