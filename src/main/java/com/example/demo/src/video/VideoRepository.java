package com.example.demo.src.video;

import com.example.demo.src.user.model.User;
import com.example.demo.src.video.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Long> {

    Video save(Video video);

    @Override
    List<Video> findAll();
}
