package com.example.demo.src.videoComment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VideoCommentReqDto {

    private String comment;
    private Long userId;

    public VideoCommentReqDto(String comment, Long userId) {
        this.comment = comment;
        this.userId = userId;
    }
}
