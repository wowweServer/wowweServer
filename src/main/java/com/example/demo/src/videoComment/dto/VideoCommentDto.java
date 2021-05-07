package com.example.demo.src.videoComment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class VideoCommentDto {

    private String comment;
    private String userName;

    public VideoCommentDto(String comment, String userName) {
        this.comment = comment;
        this.userName = userName;
    }
}
