package com.example.demo.src.videoComment;

import com.example.demo.src.video.model.Video;
import com.example.demo.src.videoComment.model.VideoComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VideoCommentRepository extends JpaRepository<VideoComment, Long> {


    @Query("select vc from VideoComment vc where vc.video.id = :videoId")
    public Page<VideoComment> findByVideoId(@Param("videoId") Long videoId, Pageable pageable);

}
