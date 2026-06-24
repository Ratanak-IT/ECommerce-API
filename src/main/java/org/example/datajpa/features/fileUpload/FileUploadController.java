package org.example.datajpa.features.fileUpload;

import lombok.RequiredArgsConstructor;

import org.example.datajpa.features.fileUpload.dto.FileUploadResponse;
import org.example.datajpa.features.minio.MinioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;
    private final MinioService minioService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileUploadResponse upload(@RequestPart MultipartFile file){
        return fileUploadService.uploadFile(file);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/multiple")
    public List<FileUploadResponse> uploadFileMultiple(@RequestPart MultipartFile[] files){
        return fileUploadService.uploadMultipleFiles(files);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{fileName}")
    public void deleteFile(@PathVariable String fileName){
        fileUploadService.deleteFile(fileName);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam MultipartFile file) {
        return minioService.uploadFile(file);
    }


}
