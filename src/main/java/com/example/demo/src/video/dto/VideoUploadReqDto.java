package com.example.demo.src.video.dto;

import lombok.Data;

@Data
public class VideoUploadReqDto {
    private String title;
    private String description;
    private double duration;
}
