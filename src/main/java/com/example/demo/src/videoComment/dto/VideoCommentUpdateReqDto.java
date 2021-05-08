package com.example.demo.src.videoComment.dto;

import lombok.Data;

@Data
public class VideoCommentUpdateReqDto {
    private Long commentId;
    private String commentText;

}
