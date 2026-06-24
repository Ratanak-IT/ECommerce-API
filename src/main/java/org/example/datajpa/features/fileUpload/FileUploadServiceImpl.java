package org.example.datajpa.features.fileUpload;

import ch.qos.logback.core.util.StringUtil;
import org.example.datajpa.features.fileUpload.dto.FileUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class FileUploadServiceImpl implements FileUploadService{

    @Value("${file.storage-location}")
    private String storageLocation;

    @Value("${file.base-uri}")
    private String baseUri;

    @Override
    public FileUploadResponse uploadFile(MultipartFile file) {
       // Prepare file information
        //file name
        String name =UUID.randomUUID().toString();

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        name+=extension;
        //create absolutePath
        try{
            Path absolutePath = Paths.get(storageLocation, name);
            Files.copy(file.getInputStream(),
                    absolutePath,
                    StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload file");
        }

        return FileUploadResponse.builder()
                .fileName(name)
                .size(file.getSize())
                .mimeType(file.getContentType())
                .uri(baseUri + "/" + name)
                .build();
    }

    @Override
    public List<FileUploadResponse> uploadMultipleFiles(MultipartFile[] files) {
        List<FileUploadResponse> responses = new ArrayList<>();
        for (MultipartFile file : files){
            responses.add(uploadFile(file));
        }
        return responses;
    }

    @Override
    public void deleteFile(String fileName) {

        try{
            Path filePath = Paths.get(storageLocation, fileName);
            boolean deleted = Files.deleteIfExists(filePath);
            if (!deleted){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
            }
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete file");
        }
    }
}
