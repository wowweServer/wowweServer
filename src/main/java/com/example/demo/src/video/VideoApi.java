package com.example.demo.src.video;

import com.example.demo.config.BaseResponse;
import com.example.demo.src.video.dto.SearchVideoReqDto;
import com.example.demo.src.video.dto.SearchVideoResDto;
import com.example.demo.src.video.dto.TimeVideoResDto;
import com.example.demo.src.video.dto.VideoResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VideoApi {

    @Autowired
    VideoService videoService;

    @PostMapping("/searchVideo/{offset}/{limit}")
    public BaseResponse<Page<SearchVideoResDto>> searchVideoByTitle(@PathVariable("offset") int offset,
                                                                    @PathVariable("limit") int limit,
                                                                    @RequestBody SearchVideoReqDto searchVideoReqDto) {
        Page<SearchVideoResDto> searchVideoPaging = videoService.searchVideoPaging(offset, limit, searchVideoReqDto.getTitle());

        return new BaseResponse<>(searchVideoPaging);
    }
    @PostMapping("/searchVideo")
    public BaseResponse<Page<SearchVideoResDto>> searchVideoByTitle2(@RequestParam int offset,
                                                                    @RequestParam int limit,
                                                                    @RequestBody SearchVideoReqDto searchVideoReqDto) {
        Page<SearchVideoResDto> searchVideoPaging = videoService.searchVideoPaging(offset, limit, searchVideoReqDto.getTitle());

        return new BaseResponse<>(searchVideoPaging);
    }

    @GetMapping("/loadRecentVideo")
    public BaseResponse<Page<VideoResDto>> findByDuration(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        int duration = 8;
        Page<VideoResDto> page = videoService.findByDuration(offset, limit, duration);
        return new BaseResponse<>(page);
    }


    @GetMapping("/video_time/{offset}/{limit}")
    public BaseResponse<Page<TimeVideoResDto>> findAll(@PathVariable("offset") int offset, @PathVariable("limit") int limit) {

        Page<TimeVideoResDto> recentlyVideoPaging = videoService.recentlyVideoPaging(offset, limit);


        return new BaseResponse<>(recentlyVideoPaging);
    }
}


