package org.example.datajpa.features.fileUpload;

import org.example.datajpa.features.fileUpload.dto.FileUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FileUploadMapper {

    @Value("${file.base-uri}")
    private String baseUri;


    public FileUploadResponse mapFileUploadToFileUploadResponse(FileUpload fileUpload){
        return FileUploadResponse.builder()
                .fileName(fileUpload.getFileName())
                .size(fileUpload.getSize())
                .extension(fileUpload.getExtension())
                .caption(fileUpload.getCaption())
                .mimeType(fileUpload.getMimeType())
                .uri(baseUri + "/" + fileUpload.getFileName())
                .extension(fileUpload.getExtension())
                .build();
    }
}
