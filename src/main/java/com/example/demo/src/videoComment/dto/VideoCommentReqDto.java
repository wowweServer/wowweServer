package com.example.demo.src.videoComment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VideoCommentReqDto {

    private String comment;
    private String userName;

    public VideoCommentReqDto(String comment, String userName) {
        this.comment = comment;
        this.userName = userName;
    }
}
