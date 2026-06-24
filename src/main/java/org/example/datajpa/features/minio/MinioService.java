package org.example.datajpa.features.minio;



import io.minio.GetPresignedObjectUrlArgs;

import io.minio.Http;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {
    private final MinioClient minioClient;

    public String getPreviewUrl(String objectName) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Http.Method.GET)
                            .bucket(bucket)
                            .object(objectName)
                            .expiry(60 * 60)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Value("${minio.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1L)
                            .contentType(file.getContentType())
                            .build()
            );

            return fileName;

        } catch (Exception e) {
            throw new RuntimeException("Upload failed", e);
        }
    }
}
