package org.example.datajpa.features.fileUpload;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "file_upload")
@Getter
@Setter
@NoArgsConstructor
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String fileName;
    @Column(nullable = false, length = 15)
    private String extension;

    private String caption;
    @Column(nullable = false)
    private Long size;
    @Column(nullable = false)
    private String mimeType;

}
