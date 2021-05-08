package com.example.demo.src.videoComment.dto;

import lombok.Data;

@Data
public class VideoCommentDeleteResDto {
    private Long videoId;
    private Long commentId;

    public VideoCommentDeleteResDto(Long videoId, Long commentId) {
        this.videoId = videoId;
        this.commentId = commentId;
    }
}
