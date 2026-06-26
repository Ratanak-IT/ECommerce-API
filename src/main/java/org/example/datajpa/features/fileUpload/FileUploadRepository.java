package org.example.datajpa.features.fileUpload;

import org.example.datajpa.features.fileUpload.dto.FileUploadResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

    Optional<FileUpload> findByFileName(String fileName);
}
