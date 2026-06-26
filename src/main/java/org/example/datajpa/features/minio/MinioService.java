package org.example.datajpa.features.minio;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MinioService {
    String getPreviewUrl(String objectName);
    String uploadFile(MultipartFile file);
    List<String> getAllFileByMinio();
}
