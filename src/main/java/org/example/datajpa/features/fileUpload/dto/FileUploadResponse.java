package org.example.datajpa.features.fileUpload.dto;

import lombok.Builder;

@Builder
public record FileUploadResponse(
        String fileName,
        String caption,
        Long size,
        String mimeType,
        String uri

) {
}
