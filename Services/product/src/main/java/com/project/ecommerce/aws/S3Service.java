package com.project.ecommerce.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


/*
public class S3Service {

    private final S3Client s3Client;

    @Value("${s3.bucket-name}")
    private String bucketName;
    @Value("${s3.bucket-region}")
    private String bucketRegion;
    @Value("${s3.access-key-id}")
    private String accessKeyId;
    @Value("${s3.secret-access-key}")
    private String secretAccessKey;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }


        public void uploadFiles(List<MultipartFile> files , Integer productId) {
            if (files == null || files.isEmpty()) {
                System.out.println("No files provided for upload.");
                return;
            }

            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();

                if (fileName == null || fileName.isEmpty()) {
                    System.out.println("Skipping file with empty name.");
                    continue;
                }

                // Create a "directory" using the product ID
                String fileKey = productId + "/" + fileName;

                // Build the PutObjectRequest
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileKey)
                        .build();

                // Upload the file directly to S3
                try {
                    PutObjectResponse response = s3Client.putObject(
                            putObjectRequest,
                            RequestBody.fromInputStream(file.getInputStream(), file.getSize())
                    );
                    System.out.println("File uploaded successfully: https://s3." +
                            bucketRegion + ".amazonaws.com/" + bucketName + "/" + fileKey);
                } catch (S3Exception | IOException e) {
                    System.out.println("Failed to upload file: " + fileName + " Error: " + e);
                }
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


    public List<String> generateImageUrls(Integer productId) {
        List<String> imageUrls = new ArrayList<>();

        try {
            // List all images for the product in S3
            AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
            ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .prefix(productId + "/") // List all files in the product "folder"
                    .build();
            ListObjectsV2Response response = s3Client.listObjectsV2(listObjectsRequest);
            S3Presigner s3Presigner = S3Presigner.builder()
                    .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                    .region(Region.of(bucketRegion)) // Replace with your bucket region
                    .build();

            // Generate pre-signed URLs for each file
            for (S3Object s3Object : response.contents()) {
                String objectKey = s3Object.key();

                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(objectKey)
                        .build();

                GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                        .getObjectRequest(getObjectRequest)
                        .signatureDuration(Duration.ofMinutes(30)) // URL valid for 30 minutes
                        .build();

                URL preSignedUrl = s3Presigner.presignGetObject(presignRequest).url();
                imageUrls.add(preSignedUrl.toString());
            }
        } catch (Exception e) {
            System.out.println("Error generating pre-signed URLs: " + e.getMessage());
        }

        return imageUrls;
    }
}
*/
