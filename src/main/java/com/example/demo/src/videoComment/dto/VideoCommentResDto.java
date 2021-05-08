package com.example.demo.src.videoComment.dto;

import com.example.demo.src.video.dto.UserDto;
import com.example.demo.src.videoComment.model.VideoComment;
import lombok.Data;

@Data
public class VideoCommentResDto {

    private Long videoId;

    private VideoCommentDto videoCommentDto;

    private UserDto userDto;

    public VideoCommentResDto(Long videoId, VideoCommentDto videoCommentDto, UserDto userDto) {
        this.videoId = videoId;
        this.videoCommentDto = videoCommentDto;
        this.userDto = userDto;
    }
}
