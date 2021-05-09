package com.example.demo.src.video.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchVideoResDto {
    private Long Videoid;
    private String videoImg;
    private String videoTitle;
    private int videoLength;

    private LocalDateTime createdAt;

    private int views;
    private int likes;
    private String tag;


    private UserDto userDto;

    public SearchVideoResDto(Long videoid, String videoTitle, LocalDateTime createdAt) {
        Videoid = videoid;
        this.videoTitle = videoTitle;
        this.createdAt = createdAt;
    }
}
