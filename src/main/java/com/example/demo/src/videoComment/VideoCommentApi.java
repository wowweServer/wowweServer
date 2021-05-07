package com.example.demo.src.videoComment;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.model.User;
import com.example.demo.src.video.VideoService;
import com.example.demo.src.video.model.Video;
import com.example.demo.src.videoComment.dto.VideoCommentDto;
import com.example.demo.src.videoComment.model.VideoComment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VideoCommentApi {

    @Autowired
    VideoCommentService videoCommentService;
    @Autowired
    UserService userService;
    @Autowired
    VideoService videoService;

    @GetMapping("/comment")
    public BaseResponse<VideoCommentDto> registerComment() throws BaseException {

        Long user_id = 2l;
        Long video_id = 2l;
        String comment = "이게 바로 comment!";

        User user1 = userService.findById(user_id);
        Video video1 = videoService.findById(video_id);


        VideoComment videoComment = videoCommentService.save(new VideoComment(user1, video1, comment));
        VideoCommentDto videoCommentDto = new VideoCommentDto(videoComment.getComment(), videoComment.getUser().getName());

        return new BaseResponse<>(videoCommentDto);

    }
}
