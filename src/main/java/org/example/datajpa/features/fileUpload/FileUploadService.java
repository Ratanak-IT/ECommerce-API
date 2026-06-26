package org.example.datajpa.features.fileUpload;

import org.example.datajpa.features.fileUpload.dto.FileUploadResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {
    FileUploadResponse uploadFile(MultipartFile file);
    List<FileUploadResponse> uploadMultipleFiles(MultipartFile[] files);
    void deleteFile(String fileName);
    List<FileUploadResponse> uploadMultipleFile(List<MultipartFile> files);
    Page<FileUploadResponse> getAllFile(int page, int size);
    FileUploadResponse findFileByName(String fileName);
}
