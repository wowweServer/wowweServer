package com.example.demo.src.videoComment;

import com.example.demo.src.video.dto.UserDto;
import com.example.demo.src.videoComment.dto.VideoCommentDto;
import com.example.demo.src.videoComment.model.VideoComment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public VideoComment findById(Long commentId) {
        Optional<VideoComment> videoComment = videoCommentRepository.findById(commentId);
        return videoComment.get();
    }

    @Transactional
    public void update(VideoComment videoComment, String text) {
        videoComment.setComment(text);
    }

    @Transactional
    public void delete(Long commentId) {
        videoCommentRepository.deleteById(commentId);
    }

    @Transactional
    public Page<VideoCommentDto> pageByVideoId(Long videoId, int offset, int limit) {

        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.Direction.DESC, "createdTime");
        Page<VideoComment> byVideoId = videoCommentRepository.findByVideoId(videoId, pageRequest);
        Page<VideoCommentDto> apiDto = byVideoId.map(vc -> new VideoCommentDto(vc.getId(), vc.getComment(), vc.getCreatedTime(), new UserDto(vc.getUser().getId(), vc.getUser().getName(), vc.getUser().getProfileImg())));

        return apiDto;
    }
}
