package com.project.ecommerce.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${s3.bucket-name}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadFile( MultipartFile file) {
        String fileName = file.getOriginalFilename();

        // Build the PutObjectRequest
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        // Upload the file directly to S3
        try {
            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            System.out.println("https://s3." + response.sdkHttpResponse() + ".amazonaws.com/" + bucketName + "/" + fileName);
        } catch (S3Exception | IOException e) {
            System.out.println("Failed to upload file to S3" + e);
        }

    }

    public void downloadFile(String bucketName, String key, Path destination) {
        s3Client.getObject(GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build(),
                destination);
    }

    public void deleteFile(String bucketName, String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build());
    }

    public void listFiles(String bucketName) {
        s3Client.listObjectsV2Paginator(builder -> builder.bucket(bucketName))
                .contents()
                .forEach(s3Object -> System.out.println("File: " + s3Object.key()));
    }
}
