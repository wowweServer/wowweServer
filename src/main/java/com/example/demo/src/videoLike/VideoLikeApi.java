package com.example.demo.src.videoLike;


import com.example.demo.config.BaseResponse;
import com.example.demo.src.videoLike.dto.TopLikeVideoResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VideoLikeApi {

    @Autowired
    VideoLikeService videoLikeService;

    @GetMapping("/video_top/{offset}/{limit}")
    public BaseResponse<Page<TopLikeVideoResDto>> paging(@PathVariable("offset") int offset, @PathVariable("limit") int limit) {
        Page<TopLikeVideoResDto> topLikeVideoPaging = videoLikeService.topLikeVideoPaging(offset, limit);

        return new BaseResponse<>(topLikeVideoPaging);
    }
}
