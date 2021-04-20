package com.example.demo.src.file.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue
    private Long id;

    private String originFilename;
    private String filename;
    private String filepath;

    @Builder
    public File(Long id, String originFilename, String filename, String filepath) {
        this.id = id;
        this.originFilename = originFilename;
        this.filename = filename;
        this.filepath = filepath;
    }
}
