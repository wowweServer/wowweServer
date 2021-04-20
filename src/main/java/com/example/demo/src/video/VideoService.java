package com.example.demo.src.video;

import com.example.demo.src.video.model.VideoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VideoService {

    private VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository){
        this.videoRepository=videoRepository;
    }

    @Transactional
    public Long saveVideo(VideoDto videoDto){
        return videoRepository.save(videoDto.toEntity()).getId();
    }


}
