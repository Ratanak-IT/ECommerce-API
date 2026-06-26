package org.example.datajpa.features.fileUpload;

import lombok.RequiredArgsConstructor;

import org.example.datajpa.features.fileUpload.dto.FileUploadResponse;
import org.example.datajpa.features.minio.MinioServiceImpl;
import org.springframework.data.domain.Page;
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
    private final MinioServiceImpl minioServiceImpl;

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


    //minio
    @PostMapping("/upload")
    public String uploadFile(@RequestParam MultipartFile file) {
        return minioServiceImpl.uploadFile(file);
    }

    @GetMapping("/get")
    public Page<FileUploadResponse> findAllFile(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size){
        return fileUploadService.getAllFile(page, size);
    }

    @GetMapping("/getByName/{fileName}")
    public FileUploadResponse findFileByName(@PathVariable String fileName) {
        return fileUploadService.findFileByName(fileName);
    }

    @GetMapping("/all-file")
    public List<String> getAllFileByMinio() {
        return minioServiceImpl.getAllFileByMinio();
    }


}
