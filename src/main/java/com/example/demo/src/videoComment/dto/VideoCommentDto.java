package com.example.demo.src.videoComment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoCommentDto {

    private Long id;
    private String commentText;
    private LocalDateTime createdAt;

    public VideoCommentDto(Long id, String commentText, LocalDateTime createdAt) {
        this.id = id;
        this.commentText = commentText;
        this.createdAt = createdAt;
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