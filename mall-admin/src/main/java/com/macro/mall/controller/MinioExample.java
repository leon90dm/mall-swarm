package com.macro.mall.controller;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinioExample {
    public static void main(String[] args) {
        try {
            String ACCESS_KEY = "neoadmin";
            String SECRET_KEY = "nQm7Zw8u9";
            String bucketName = "mall";
            String ENDPOINT = "http://mallenv.tt.inf:9090";

            MinioClient minioClient = MinioClient.builder()
                    .endpoint(ENDPOINT)
                    .credentials(ACCESS_KEY, SECRET_KEY)
                    .build();

            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

            System.out.println("Bucket exists: " + isExist);
        } catch (MinioException e) {
            System.err.println("MinIO error occurred: " + e);
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            System.err.println("Error occurred: " + e);
        }
    }
}