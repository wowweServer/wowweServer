package com.example.demo.src.video;

import com.example.demo.src.video.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("select v from Video v where v.duration < :duration")
    public Page<Video> findByDuration(@Param("duration") int duration, Pageable pageable);


    @Query("select v from Video v")
    public Page<Video> findAll(Pageable pageable);

}