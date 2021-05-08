package com.example.demo.src.videoComment.dto;

import lombok.Data;

@Data
public class VideoCommentUpdateResDto {
    private Long videoId;
    private Long commentId;
    private String commentText;

    public VideoCommentUpdateResDto(Long videoId, Long commentId, String commentText) {
        this.videoId = videoId;
        this.commentId = commentId;
        this.commentText = commentText;
    }
}
