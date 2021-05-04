package com.example.demo.src.videoLike;

import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.model.User;
import com.example.demo.src.video.VideoRepository;
import com.example.demo.src.video.model.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class VideoLikeServiceTest {

    @Autowired
    VideoLikeService videoLikeService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    VideoRepository videoRepository;


    @Test
    public void 비디오좋아요누르기() {

        long val = 1;

        videoLikeService.videoLike(val, val);

    }


}