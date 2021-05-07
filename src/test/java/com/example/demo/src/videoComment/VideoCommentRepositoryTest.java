package com.example.demo.src.videoComment;

import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.model.User;
import com.example.demo.src.video.VideoRepository;
import com.example.demo.src.video.model.Video;
import com.example.demo.src.videoComment.model.VideoComment;
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
class VideoCommentRepositoryTest {

    @Autowired
    VideoCommentRepository videoCommentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VideoRepository videoRepository;

    @Test
    public void 댓글테스트() {


        Optional<User> user1 = userRepository.findById(1l);
        Optional<User> user2 = userRepository.findById(2l);

        Optional<Video> video1 = videoRepository.findById(1l);


//        videoCommentRepository.save(new VideoComment(user1.get(), video1.get(), "hi! helllo"));
        videoCommentRepository.save(new VideoComment(user2.get(), video1.get(), "hi2222! helllo22222"));



    }

}