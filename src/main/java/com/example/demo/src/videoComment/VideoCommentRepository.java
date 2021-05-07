package com.example.demo.src.videoComment;

import com.example.demo.src.videoComment.model.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCommentRepository extends JpaRepository<VideoComment, Long> {

}
