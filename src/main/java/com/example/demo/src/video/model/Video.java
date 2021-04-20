package com.example.demo.src.video.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    private Long fileId;

    @Builder
    public Video(Long id, Long fileId) {
        this.id = id;
        this.fileId = fileId;
    }
}