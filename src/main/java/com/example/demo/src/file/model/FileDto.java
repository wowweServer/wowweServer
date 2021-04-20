package com.example.demo.src.file.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileDto {
    private Long id;
    private String originFilename;
    private String filename;
    private String filepath;

    public File toEntity() {
        File build = File.builder()
                .id(id)
                .originFilename(originFilename)
                .filename(filename)
                .filepath(filepath)
                .build();
        return build;
    }

    @Builder
    public FileDto(Long id, String originFilename, String filename, String filepath) {
        this.id = id;
        this.originFilename = originFilename;
        this.filename = filename;
        this.filepath = filepath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginFilename() {
        return originFilename;
    }

    public void setOriginFilename(String originFilename) {
        this.originFilename = originFilename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
