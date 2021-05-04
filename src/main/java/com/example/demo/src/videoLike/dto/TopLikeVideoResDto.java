package com.example.demo.src.videoLike.dto;

import com.example.demo.src.video.dto.UserDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopLikeVideoResDto {

    private Long Videoid;
    private String videoImg;
    private String videoTitle;
    private int videoLength;

    private LocalDateTime createdAt;

    private int views;
    private int likes;
    private String tag;

    private String  uesrId;
    private String userName;

    private UserDto userDto;

    public TopLikeVideoResDto(Long videoid, String videoImg, String videoTitle, LocalDateTime createdAt, Long userId, String userName) {

        this.Videoid = videoid;
        this.videoImg = videoImg;
        this.videoTitle = videoTitle;
        this.createdAt = createdAt;

        this.userDto = new UserDto(userId, userName);

    }

    public void userAdd(Long id, String userName) {
        this.userDto.setId(id);
        this.userDto.setUserName(userName);
    }
}
