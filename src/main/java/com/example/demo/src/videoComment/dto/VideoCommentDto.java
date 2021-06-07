package com.example.demo.src.videoComment.dto;

import com.example.demo.src.video.dto.UserDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoCommentDto {

    private Long id;
    private String commentText;
    private LocalDateTime createdAt;
    private UserDto userDto;

    public VideoCommentDto(Long id, String commentText, LocalDateTime createdAt, UserDto userDto) {

        this.id = id;
        this.commentText = commentText;
        this.createdAt = createdAt;
        this.userDto = userDto;
    }
}
//    response
//    {
//        success:true,
//                status:201,
//            data:{
//        videoId:Integer,
//                Comment:[
//        {
//            id:Integer,
//                    commentText:String,
//                createdAt:<Date>,
//                User:{
//            id:Integer,
//                    userName:String
//        }