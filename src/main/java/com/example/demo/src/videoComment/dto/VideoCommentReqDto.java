package com.example.demo.src.videoComment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VideoCommentReqDto {

    private String comment;

    public VideoCommentReqDto(String comment) {
        this.comment = comment;
    }
}
