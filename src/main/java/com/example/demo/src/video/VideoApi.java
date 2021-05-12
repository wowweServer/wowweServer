package com.example.demo.src.video;

import com.example.demo.config.BaseResponse;
import com.example.demo.src.video.dto.*;
import com.example.demo.src.video.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VideoApi {

    @Autowired
    VideoService videoService;


    @GetMapping("/loadRecentVideo")
    public BaseResponse<Page<TimeVideoResDto>> loadRecentVideo(@RequestParam int offset, @RequestParam int limit) {

        Page<TimeVideoResDto> recentlyVideoPaging = videoService.recentlyVideoPaging(offset, limit);

        return new BaseResponse<>(recentlyVideoPaging);
    }

    @PostMapping("/searchContent")
    public BaseResponse<Page<SearchVideoResDto>> searchVideoByTitle(@RequestParam int offset, @RequestParam int limit, @RequestBody SearchVideoReqDto searchVideoReqDto) {
        Page<SearchVideoResDto> searchVideoResDtos = videoService.searchVideoPaging(offset, limit, searchVideoReqDto.getTitle());

        return new BaseResponse<>(searchVideoResDtos);
    }


    @DeleteMapping("/video/{videoId}/removeVideo")
    public BaseResponse<String> deleteVideo(@PathVariable("videoId") Long videoId) {

        Video video = videoService.findById(videoId);
        videoService.delete(video);

        return new BaseResponse<>("");
    }
}


