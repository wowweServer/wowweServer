package com.example.demo.src.video.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoResDto {

    private String title;
    private String desc;
    private double dur;

    public VideoResDto(String title, String desc, double dur) {
        this.title = title;
        this.desc = desc;
        this.dur = dur;
    }
}
