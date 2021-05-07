package com.example.demo.src.videoComment;

import com.example.demo.src.videoComment.model.VideoComment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VideoCommentService {

    @Autowired
    VideoCommentRepository videoCommentRepository;

    @Transactional
    public VideoComment save(VideoComment videoComment) {
        VideoComment saved_videoComment = videoCommentRepository.save(videoComment);
        return saved_videoComment;
    }



}
